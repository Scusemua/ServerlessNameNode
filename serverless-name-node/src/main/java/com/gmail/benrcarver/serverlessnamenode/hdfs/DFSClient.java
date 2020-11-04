package com.gmail.benrcarver.serverlessnamenode.hdfs;

import com.gmail.benrcarver.serverlessnamenode.exceptions.DSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.NSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.client.impl.DfsClientConf;
import com.gmail.benrcarver.serverlessnamenode.hdfs.net.Peer;
import com.gmail.benrcarver.serverlessnamenode.hdfs.net.TcpPeerServer;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.*;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.TrustedChannelResolver;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.sasl.DataEncryptionKeyFactory;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.sasl.DataTransferSaslUtil;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.sasl.SaslDataTransferClient;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.client.HdfsClientConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.protocol.CorruptFileBlocks;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.block.DataEncryptionKey;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.ServerlessNameNode;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import io.hops.leader_election.node.ActiveNode;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.RemoteException;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.Token;
import org.apache.htrace.core.TraceScope;
import org.apache.htrace.core.Tracer;

import javax.net.SocketFactory;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.*;

public class DFSClient implements java.io.Closeable, RemotePeerFactory,
        DataEncryptionKeyFactory {
    public static final Log LOG = LogFactory.getLog(DFSClient.class);
    public static final long SERVER_DEFAULTS_VALIDITY_PERIOD = 60 * 60 * 1000L; // 1 hour
    static final int TCP_WINDOW_SIZE = 128 * 1024; // 128 KB

    private final Configuration conf;
    private final Tracer tracer;
    private final DfsClientConf dfsClientConf;
    final ClientProtocol namenode;
    ClientProtocol leaderNN;
    final List<ClientProtocol> allNNs = new ArrayList<ClientProtocol>();
    /* The service used for delegation tokens */
    private Text dtService;

    final UserGroupInformation ugi;
    volatile boolean clientRunning = true;
    volatile long lastLeaseRenewal;
    private volatile FsServerDefaults serverDefaults;
    private volatile long serverDefaultsLastUpdate;
    final String clientName;
    private final SocketFactory socketFactory;
    final HdfsClientConfigKeys.BlockWrite.ReplaceDatanodeOnFailure dtpReplaceDatanodeOnFailure;
    final FileSystem.Statistics stats;
    private final String authority;
    private Random r = new Random();
    private SocketAddress[] localInterfaceAddrs;
    private DataEncryptionKey encryptionKey;
    final SaslDataTransferClient saslClient;
    private final CachingStrategy defaultReadCachingStrategy;
    private final CachingStrategy defaultWriteCachingStrategy;
    private final ClientContext clientContext;

    private static final DFSHedgedReadMetrics HEDGED_READ_METRIC =
            new DFSHedgedReadMetrics();
    private static ThreadPoolExecutor HEDGED_READ_THREAD_POOL;

    public DfsClientConf getConf() {
        return dfsClientConf;
    }

    Configuration getConfiguration() {
        return conf;
    }

    /**
     * A map from file names to {@link DFSOutputStream} objects
     * that are currently being written by this client.
     * Note that a file can only be written by a single client.
     */
    private final Map<Long, DFSOutputStream> filesBeingWritten
            = new HashMap<Long, DFSOutputStream>();

    /**
     * Same as this(NameNode.getAddress(conf), conf);
     * @see #DFSClient(InetSocketAddress, Configuration)
     * @deprecated Deprecated at 0.21
     */
    @Deprecated
    public DFSClient(Configuration conf) throws IOException {
        this(ServerlessNameNode.getAddress(conf), conf);
    }

    public DFSClient(InetSocketAddress address, Configuration conf) throws IOException {
        this(ServerlessNameNode.getUri(address), conf);
    }

    /**
     * Same as this(nameNodeUri, conf, null);
     * @see #DFSClient(URI, Configuration, FileSystem.Statistics)
     */
    public DFSClient(URI nameNodeUri, Configuration conf
    ) throws IOException {
        this(nameNodeUri, conf, null);
    }

    /**
     * Same as this(nameNodeUri, null, conf, stats);
     * @see #DFSClient(URI, ClientProtocol, Configuration, FileSystem.Statistics)
     */
    public DFSClient(URI nameNodeUri, Configuration conf,
                     FileSystem.Statistics stats)
            throws IOException {
        this(nameNodeUri, null, conf, stats);
    }

    /**
     * Create a new DFSClient connected to the given nameNodeUri or rpcNamenode.
     * If HA is enabled and a positive value is set for
     * {@link DFSConfigKeys#DFS_CLIENT_TEST_DROP_NAMENODE_RESPONSE_NUM_KEY} in the
     * configuration, the DFSClient will use {@link LossyRetryInvocationHandler}
     * as its RetryInvocationHandler. Otherwise one of nameNodeUri or rpcNamenode
     * must be null.
     */
    @VisibleForTesting
    public DFSClient(URI nameNodeUri, ClientProtocol rpcNamenode,
                     Configuration conf, FileSystem.Statistics stats)
            throws IOException {
        // Copy only the required DFSClient configuration
        this.tracer = FsTracer.get(conf);
        this.dfsClientConf = new DfsClientConf(conf);
        this.conf = conf;
        this.stats = stats;
        this.socketFactory = NetUtils.getSocketFactoryFromProperty(conf,
                conf.get(DFSConfigKeys.DFS_CLIENT_XCEIVER_SOCKET_FACTORY_CLASS_KEY,
                        DFSConfigKeys.DEFAULT_DFS_CLIENT_XCEIVER_FACTORY_CLASS));
        this.dtpReplaceDatanodeOnFailure = ReplaceDatanodeOnFailure.get(conf);

        this.ugi = UserGroupInformation.getCurrentUser();

        this.authority = nameNodeUri == null? "null": nameNodeUri.getAuthority();

        String clientNamePrefix = "";
        if(dfsClientConf.getForceClientToWriteSFToDisk()){
            clientNamePrefix = "DFSClient";
        }else{
            clientNamePrefix = "HopsFS_DFSClient";
        }
        this.clientName = clientNamePrefix+ "_" + dfsClientConf.getTaskId() + "_" +
                DFSUtil.getRandom().nextInt() + "_" + Thread.currentThread().getId();
        int numResponseToDrop = conf.getInt(
                DFSConfigKeys.DFS_CLIENT_TEST_DROP_NAMENODE_RESPONSE_NUM_KEY,
                DFSConfigKeys.DFS_CLIENT_TEST_DROP_NAMENODE_RESPONSE_NUM_DEFAULT);
        NameNodeProxies.ProxyAndInfo<ClientProtocol> proxyInfo = null;
        AtomicBoolean nnFallbackToSimpleAuth = new AtomicBoolean(false);
        if (numResponseToDrop > 0) {
            // This case is used for testing.
            LOG.warn(DFSConfigKeys.DFS_CLIENT_TEST_DROP_NAMENODE_RESPONSE_NUM_KEY
                    + " is set to " + numResponseToDrop
                    + ", this hacked client will proactively drop responses");
            proxyInfo = NameNodeProxies.createProxyWithLossyRetryHandler(conf,
                    nameNodeUri, ClientProtocol.class, numResponseToDrop,
                    nnFallbackToSimpleAuth);
        }

        if (proxyInfo != null) {
            this.dtService = proxyInfo.getDelegationTokenService();
            this.namenode = proxyInfo.getProxy();
            this.leaderNN = namenode; // only for testing
        } else if (rpcNamenode != null) {
            // This case is used for testing.
            Preconditions.checkArgument(nameNodeUri == null);
            this.namenode = rpcNamenode;
            this.leaderNN = rpcNamenode;
            dtService = null;
        } else {
            Preconditions.checkArgument(nameNodeUri != null,
                    "null URI");
            proxyInfo = NameNodeProxies.createHopsRandomStickyProxy(conf, nameNodeUri,
                    ClientProtocol.class, nnFallbackToSimpleAuth);
            this.dtService = proxyInfo.getDelegationTokenService();
            this.namenode = proxyInfo.getProxy();

            if(namenode != null) {
                try {
                    List<ActiveNode> anns = namenode.getActiveNamenodesForClient().getSortedActiveNodes();
                    leaderNN = NameNodeProxies.createHopsLeaderProxy(conf, nameNodeUri,
                            ClientProtocol.class, nnFallbackToSimpleAuth).getProxy();

                    for (ActiveNode an : anns) {
                        allNNs.add(NameNodeProxies.createNonHAProxy(conf, an.getRpcServerAddressForClients(),
                                ClientProtocol.class, ugi, false, nnFallbackToSimpleAuth).getProxy());
                    }
                } catch (ConnectException e){
                    LOG.warn("Namenode proxy is null");
                    leaderNN = null;
                    allNNs.clear();
                }
            }
        }

        // set epoch
        setClientEpoch();

        String localInterfaces[] =
                conf.getTrimmedStrings(DFSConfigKeys.DFS_CLIENT_LOCAL_INTERFACES);
        localInterfaceAddrs = getLocalInterfaceAddrs(localInterfaces);
        if (LOG.isDebugEnabled() && 0 != localInterfaces.length) {
            LOG.debug("Using local interfaces [" +
                    Joiner.on(',').join(localInterfaces)+ "] with addresses [" +
                    Joiner.on(',').join(localInterfaceAddrs) + "]");
        }

        Boolean readDropBehind = (conf.get(DFS_CLIENT_CACHE_DROP_BEHIND_READS) == null) ?
                null : conf.getBoolean(DFS_CLIENT_CACHE_DROP_BEHIND_READS, false);
        Long readahead = (conf.get(DFS_CLIENT_CACHE_READAHEAD) == null) ?
                null : conf.getLong(DFS_CLIENT_CACHE_READAHEAD, 0);
        Boolean writeDropBehind = (conf.get(DFS_CLIENT_CACHE_DROP_BEHIND_WRITES) == null) ?
                null : conf.getBoolean(DFS_CLIENT_CACHE_DROP_BEHIND_WRITES, false);
        this.defaultReadCachingStrategy =
                new CachingStrategy(readDropBehind, readahead);
        this.defaultWriteCachingStrategy =
                new CachingStrategy(writeDropBehind, readahead);
        this.clientContext = ClientContext.get(
                conf.get(DFS_CLIENT_CONTEXT, DFS_CLIENT_CONTEXT_DEFAULT),
                dfsClientConf);

        if (dfsClientConf.getHedgedReadThreadpoolSize() > 0) {
            this.initThreadsNumForHedgedReads(dfsClientConf.getHedgedReadThreadpoolSize());
        }
        this.saslClient = new SaslDataTransferClient(
                conf, DataTransferSaslUtil.getSaslPropertiesResolver(conf),
                TrustedChannelResolver.getInstance(conf), nnFallbackToSimpleAuth);
    }

    /**
     * Select one of the configured local interfaces at random. We use a random
     * interface because other policies like round-robin are less effective
     * given that we cache connections to datanodes.
     *
     * @return one of the local interface addresses at random, or null if no
     *    local interfaces are configured
     */
    SocketAddress getRandomLocalInterfaceAddr() {
        if (localInterfaceAddrs.length == 0) {
            return null;
        }
        final int idx = r.nextInt(localInterfaceAddrs.length);
        final SocketAddress addr = localInterfaceAddrs[idx];
        if (LOG.isDebugEnabled()) {
            LOG.debug("Using local interface " + addr);
        }
        return addr;
    }

    /**
     * @return a list in which each entry describes a corrupt file/block
     * @throws IOException
     */
    public CorruptFileBlocks listCorruptFileBlocks(String path,
                                                   String cookie)
            throws IOException {
        checkOpen();
        try (TraceScope ignored
                     = newPathTraceScope("listCorruptFileBlocks", path)) {
            return namenode.listCorruptFileBlocks(path, cookie);
        }
    }

    TraceScope newPathTraceScope(String description, String path) {
        TraceScope scope = tracer.newScope(description);
        if (path != null) {
            scope.addKVAnnotation("path", path);
        }
        return scope;
    }

    @Override // RemotePeerFactory
    public Peer newConnectedPeer(InetSocketAddress addr,
                                 Token<BlockTokenIdentifier> blockToken, DatanodeID datanodeId)
            throws IOException {
        Peer peer = null;
        boolean success = false;
        Socket sock = null;
        final int socketTimeout = dfsClientConf.getSocketTimeout();
        try {
            sock = socketFactory.createSocket();
            NetUtils.connect(sock, addr, getRandomLocalInterfaceAddr(), socketTimeout);
            peer = TcpPeerServer.peerFromSocketAndKey(saslClient, sock, this,
                    blockToken, datanodeId);
            peer.setReadTimeout(socketTimeout);
            success = true;
            return peer;
        } finally {
            if (!success) {
                IOUtils.cleanup(LOG, peer);
                IOUtils.closeSocket(sock);
            }
        }
    }

    void checkOpen() throws IOException {
        if (!clientRunning) {
            IOException result = new IOException("Filesystem closed");
            throw result;
        }
    }

    TraceScope newSrcDstTraceScope(String description, String src, String dst) {
        TraceScope scope = tracer.newScope(description);
        if (src != null) {
            scope.addKVAnnotation("src", src);
        }
        if (dst != null) {
            scope.addKVAnnotation("dst", dst);
        }
        return scope;
    }

    /**
     * Rename file or directory.
     * @see ClientProtocol#rename2(String, String, Options.Rename...)
     */
    public void rename(String src, String dst, Options.Rename... options)
            throws IOException {
        checkOpen();
        try (TraceScope ignored = newSrcDstTraceScope("rename2", src, dst)) {
            namenode.rename2(src, dst, options);
        } catch(RemoteException re) {
            throw re.unwrapRemoteException(AccessControlException.class,
                    DSQuotaExceededException.class,
                    QuotaByStorageTypeExceededException.class,
                    FileAlreadyExistsException.class,
                    FileNotFoundException.class,
                    ParentNotDirectoryException.class,
                    SafeModeException.class,
                    NSQuotaExceededException.class,
                    UnresolvedPathException.class);
        }
    }

    /**
     * Rename file or directory.
     * @see ClientProtocol#rename(String, String)
     * @deprecated Use {@link #rename(String, String, Options.Rename...)} instead.
     */
    @Deprecated
    public boolean rename(String src, String dst) throws IOException {
        checkOpen();
        try (TraceScope ignored = newSrcDstTraceScope("rename", src, dst)) {
            return namenode.rename(src, dst);
        } catch(RemoteException re) {
            throw re.unwrapRemoteException(AccessControlException.class,
                    NSQuotaExceededException.class,
                    DSQuotaExceededException.class,
                    QuotaByStorageTypeExceededException.class,
                    UnresolvedPathException.class);
        }
    }

    @Override
    public DataEncryptionKey newDataEncryptionKey() throws IOException {
        return null;
    }

    @Override
    public void close() throws IOException {

    }
}
