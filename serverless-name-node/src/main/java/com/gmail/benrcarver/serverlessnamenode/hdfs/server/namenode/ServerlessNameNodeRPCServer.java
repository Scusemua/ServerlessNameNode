package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.hdfs.HDFSPolicyProvider;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.*;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.DatanodeProtocolProtos;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.NamenodeProtocolProtos;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.NamenodeProtocols;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.blockmanagement.BRLoadBalancingOverloadException;
import com.gmail.benrcarver.serverlessnamenode.protocol.ClientNamenodeProtocolProtos;
import com.google.protobuf.BlockingService;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.protocol.*;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.WritableRpcEngine;
import org.apache.hadoop.ipc.proto.GenericRefreshProtocolProtos;
import org.apache.hadoop.ipc.proto.RefreshCallQueueProtocolProtos;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolPB;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolServerSideTranslatorPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolServerSideTranslatorPB;
import org.apache.hadoop.net.Node;
import org.apache.hadoop.security.authorize.AuthorizationException;
import org.apache.hadoop.security.proto.RefreshAuthorizationPolicyProtocolProtos;
import org.apache.hadoop.security.proto.RefreshUserMappingsProtocolProtos;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.tools.proto.GetUserMappingsProtocolProtos;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolPB;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.tracing.TraceAdminPB;
import org.apache.hadoop.tracing.TraceAdminProtocolPB;
import org.apache.hadoop.tracing.TraceAdminProtocolServerSideTranslatorPB;
import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.FileAlreadyExistsException;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.*;
import static com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsConstants.MAX_PATH_DEPTH;
import static com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsConstants.MAX_PATH_LENGTH;

public class ServerlessNameNodeRPCServer implements NamenodeProtocols {
    // Dependencies from other parts of NN.
    protected final ServerlessNameNode nn;
    protected FSNameSystem namesystem;
    //private final NameNodeMetrics metrics;

    /**
     * The RPC server that listens to requests from DataNodes
     */
    private final RPC.Server serviceRpcServer;
    private final InetSocketAddress serviceRPCAddress;

    private final String minimumDataNodeVersion;

    /**
     * The RPC server that listens to requests from clients
     */
    protected final RPC.Server clientRpcServer;
    protected final InetSocketAddress clientRpcAddress;

    private final boolean serviceAuthEnabled;

    private static final Logger LOG = ServerlessNameNode.LOG;
    private static final Logger stateChangeLog = ServerlessNameNode.stateChangeLog;
    private static final Logger blockStateChangeLog = ServerlessNameNode.blockStateChangeLog;

    public ServerlessNameNodeRPCServer(ServerlessNameNode nameNode) {
        this.nn = nameNode;
    }

    public ServerlessNameNodeRPCServer(Configuration conf, ServerlessNameNode nn) throws IOException {
        this.nn = nn;
        this.namesystem = nn.getNamesystem();
        //this.metrics = ServerlessNameNode.getNameNodeMetrics();

        int handlerCount = conf.getInt(DFS_NAMENODE_HANDLER_COUNT_KEY,
                DFS_NAMENODE_HANDLER_COUNT_DEFAULT);

        RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class,
                ProtobufRpcEngine.class);

        ClientNamenodeProtocolServerSideTranslatorPB
                clientProtocolServerTranslator =
                new ClientNamenodeProtocolServerSideTranslatorPB(this);
        BlockingService clientNNPbService = ClientNamenodeProtocolProtos.ClientNamenodeProtocol.
                newReflectiveBlockingService(clientProtocolServerTranslator);

        DatanodeProtocolServerSideTranslatorPB dnProtoPbTranslator =
                new DatanodeProtocolServerSideTranslatorPB(this);
        BlockingService dnProtoPbService = DatanodeProtocolProtos.DatanodeProtocolService
                .newReflectiveBlockingService(dnProtoPbTranslator);

        NamenodeProtocolServerSideTranslatorPB namenodeProtocolXlator =
                new NamenodeProtocolServerSideTranslatorPB(this);
        BlockingService NNPbService = NamenodeProtocolProtos.NamenodeProtocolService
                .newReflectiveBlockingService(namenodeProtocolXlator);

        RefreshAuthorizationPolicyProtocolServerSideTranslatorPB
                refreshAuthPolicyXlator =
                new RefreshAuthorizationPolicyProtocolServerSideTranslatorPB(this);
        BlockingService refreshAuthService =
                RefreshAuthorizationPolicyProtocolProtos.RefreshAuthorizationPolicyProtocolService
                        .newReflectiveBlockingService(refreshAuthPolicyXlator);

        RefreshUserMappingsProtocolServerSideTranslatorPB refreshUserMappingXlator =
                new RefreshUserMappingsProtocolServerSideTranslatorPB(this);
        BlockingService refreshUserMappingService =
                RefreshUserMappingsProtocolProtos.RefreshUserMappingsProtocolService
                        .newReflectiveBlockingService(refreshUserMappingXlator);

        RefreshCallQueueProtocolServerSideTranslatorPB refreshCallQueueXlator =
                new RefreshCallQueueProtocolServerSideTranslatorPB(this);
        BlockingService refreshCallQueueService = RefreshCallQueueProtocolProtos.RefreshCallQueueProtocolService
                .newReflectiveBlockingService(refreshCallQueueXlator);

        GenericRefreshProtocolServerSideTranslatorPB genericRefreshXlator =
                new GenericRefreshProtocolServerSideTranslatorPB(this);
        BlockingService genericRefreshService = GenericRefreshProtocolProtos.GenericRefreshProtocolService
                .newReflectiveBlockingService(genericRefreshXlator);

        GetUserMappingsProtocolServerSideTranslatorPB getUserMappingXlator =
                new GetUserMappingsProtocolServerSideTranslatorPB(this);
        BlockingService getUserMappingService = GetUserMappingsProtocolProtos.GetUserMappingsProtocolService
                .newReflectiveBlockingService(getUserMappingXlator);

        TraceAdminProtocolServerSideTranslatorPB traceAdminXlator =
                new TraceAdminProtocolServerSideTranslatorPB(this);
        BlockingService traceAdminService = TraceAdminPB.TraceAdminService
                .newReflectiveBlockingService(traceAdminXlator);

        WritableRpcEngine.ensureInitialized();

        InetSocketAddress serviceRpcAddr = nn.getServiceRpcServerAddress(conf);
        if (serviceRpcAddr != null) {
            String bindHost = nn.getServiceRpcServerBindHost(conf);
            if (bindHost == null) {
                bindHost = serviceRpcAddr.getHostName();
            }
            LOG.info("Service RPC server is binding to " + bindHost + ":" +
                    serviceRpcAddr.getPort());

            int serviceHandlerCount =
                    conf.getInt(DFS_NAMENODE_SERVICE_HANDLER_COUNT_KEY,
                            DFS_NAMENODE_SERVICE_HANDLER_COUNT_DEFAULT);
            this.serviceRpcServer = new RPC.Builder(conf).setProtocol(
                    com.gmail.benrcarver.serverlessnamenode.hdfs.protocolPB.ClientNamenodeProtocolPB.class)
                    .setInstance(clientNNPbService)
                    .setBindAddress(bindHost)
                    .setPort(serviceRpcAddr.getPort()).setNumHandlers(serviceHandlerCount)
                    .setVerbose(false)
                    .setSecretManager(namesystem.getDelegationTokenSecretManager())
                    .build();

            // Add all the RPC protocols that the namenode implements
            DFSUtil.addPBProtocol(conf, NamenodeProtocolPB.class, NNPbService,
                    serviceRpcServer);
            DFSUtil.addPBProtocol(conf, DatanodeProtocolPB.class, dnProtoPbService,
                    serviceRpcServer);
            DFSUtil.addPBProtocol(conf, RefreshAuthorizationPolicyProtocolPB.class,
                    refreshAuthService, serviceRpcServer);
            DFSUtil.addPBProtocol(conf, RefreshUserMappingsProtocolPB.class,
                    refreshUserMappingService, serviceRpcServer);
            // We support Refreshing call queue here in case the client RPC queue is full
            DFSUtil.addPBProtocol(conf, RefreshCallQueueProtocolPB.class,
                    refreshCallQueueService, serviceRpcServer);
            DFSUtil.addPBProtocol(conf, GenericRefreshProtocolPB.class,
                    genericRefreshService, serviceRpcServer);
            DFSUtil.addPBProtocol(conf, GetUserMappingsProtocolPB.class,
                    getUserMappingService, serviceRpcServer);
            DFSUtil.addPBProtocol(conf, TraceAdminProtocolPB.class,
                    traceAdminService, serviceRpcServer);

            // Update the address with the correct port
            InetSocketAddress listenAddr = serviceRpcServer.getListenerAddress();
            serviceRPCAddress = new InetSocketAddress(
                    serviceRpcAddr.getHostName(), listenAddr.getPort());
            nn.setRpcServiceServerAddress(conf, serviceRPCAddress);
        } else {
            serviceRpcServer = null;
            serviceRPCAddress = null;
        }
        InetSocketAddress rpcAddr = nn.getRpcServerAddress(conf);
        String bindHost = nn.getRpcServerBindHost(conf);
        if (bindHost == null) {
            bindHost = rpcAddr.getHostName();
        }
        LOG.info("RPC server is binding to " + bindHost + ":" + rpcAddr.getPort());

        this.clientRpcServer = new RPC.Builder(conf).setProtocol(
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocolPB.ClientNamenodeProtocolPB.class)
                .setInstance(clientNNPbService).setBindAddress(bindHost)
                .setPort(rpcAddr.getPort()).setNumHandlers(handlerCount)
                .setVerbose(false)
                .setSecretManager(namesystem.getDelegationTokenSecretManager()).build();

        // Add all the RPC protocols that the namenode implements
        DFSUtil.addPBProtocol(conf, NamenodeProtocolPB.class, NNPbService,
                clientRpcServer);
        DFSUtil.addPBProtocol(conf, DatanodeProtocolPB.class, dnProtoPbService,
                clientRpcServer);
        DFSUtil.addPBProtocol(conf, RefreshAuthorizationPolicyProtocolPB.class,
                refreshAuthService, clientRpcServer);
        DFSUtil.addPBProtocol(conf, RefreshUserMappingsProtocolPB.class,
                refreshUserMappingService, clientRpcServer);
        DFSUtil.addPBProtocol(conf, RefreshCallQueueProtocolPB.class,
                refreshCallQueueService, clientRpcServer);
        DFSUtil.addPBProtocol(conf, GenericRefreshProtocolPB.class,
                genericRefreshService, clientRpcServer);
        DFSUtil.addPBProtocol(conf, GetUserMappingsProtocolPB.class,
                getUserMappingService, clientRpcServer);
        DFSUtil.addPBProtocol(conf, TraceAdminProtocolPB.class,
                traceAdminService, clientRpcServer);

        // set service-level authorization security policy
        if (serviceAuthEnabled =
                conf.getBoolean(CommonConfigurationKeys.HADOOP_SECURITY_AUTHORIZATION,
                        false)) {
            clientRpcServer.refreshServiceAcl(conf, new HDFSPolicyProvider());
            if (serviceRpcServer != null) {
                serviceRpcServer.refreshServiceAcl(conf, new HDFSPolicyProvider());
            }
        }

        // The rpc-server port can be ephemeral... ensure we have the correct info
        InetSocketAddress listenAddr = clientRpcServer.getListenerAddress();
        clientRpcAddress = new InetSocketAddress(
                rpcAddr.getHostName(), listenAddr.getPort());
        nn.setRpcServerAddress(conf, clientRpcAddress);

        minimumDataNodeVersion =
                conf.get(DFSConfigKeys.DFS_NAMENODE_MIN_SUPPORTED_DATANODE_VERSION_KEY,
                        DFSConfigKeys.DFS_NAMENODE_MIN_SUPPORTED_DATANODE_VERSION_DEFAULT);

        // Set terse exception whose stack trace won't be logged
        clientRpcServer.addTerseExceptions(SafeModeException.class,
                FileNotFoundException.class,
                HadoopIllegalArgumentException.class,
                FileAlreadyExistsException.class,
                InvalidPathException.class,
                ParentNotDirectoryException.class,
                UnresolvedLinkException.class,
                AlreadyBeingCreatedException.class,
                QuotaExceededException.class,
                RecoveryInProgressException.class,
                AccessControlException.class,
                SecretManager.InvalidToken.class,
                LeaseExpiredException.class,
                NSQuotaExceededException.class,
                DSQuotaExceededException.class,
                QuotaByStorageTypeExceededException.class,
                AclException.class,
                FSLimitException.PathComponentTooLongException.class,
                FSLimitException.MaxDirectoryItemsExceededException.class,
                UnresolvedPathException.class,
                BRLoadBalancingOverloadException.class);
    }

    private void checkNNStartup() throws IOException {
        if (!this.nn.isStarted()) {
            throw new IOException(this.nn.getRole() + " still not started");
        }
    }

    InetSocketAddress getServiceRpcAddress() {
        return serviceRPCAddress;
    }

    InetSocketAddress getRpcAddress() {
        return clientRpcAddress;
    }

    /**
     * Wait until the RPC servers have shutdown.
     */
    void join() throws InterruptedException {
        clientRpcServer.join();
        if (serviceRpcServer != null) {
            serviceRpcServer.join();
        }
    }

    /**
     * Stop client and service RPC servers.
     */
    void stop() {
        if (clientRpcServer != null) {
            clientRpcServer.stop();
        }
        if (serviceRpcServer != null) {
            serviceRpcServer.stop();
        }
    }

    /**
     * Check path length does not exceed maximum.  Returns true if
     * length and depth are okay.  Returns false if length is too long
     * or depth is too great.
     */
    private boolean checkPathLength(String src) {
        Path srcPath = new Path(src);
        return (src.length() <= MAX_PATH_LENGTH &&
                srcPath.depth() <= MAX_PATH_DEPTH);
    }

    /**
     * The client needs to give up on the block.
     */
    @Override // ClientProtocol
    public void abandonBlock(ExtendedBlock b, long fileId, String src, String holder)
            throws IOException {
        checkNNStartup();
        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog
                    .debug("*BLOCK* NameNode.abandonBlock: " + b + " of file " + src);
        }
        if (!namesystem.abandonBlock(b, fileId, src, holder)) {
            throw new IOException("Cannot abandon block during write to " + src);
        }
    }

    @Override // ClientProtocol
    public void updatePipeline(String clientName, ExtendedBlock oldBlock,
                               ExtendedBlock newBlock, DatanodeID[] newNodes, String[] newStorageIDs)
            throws IOException {
        checkNNStartup();
        namesystem.updatePipeline(clientName, oldBlock, newBlock, newNodes, newStorageIDs);
    }

    @Override // ClientProtocol
    public LocatedBlock addBlock(String src, String clientName,
                                 ExtendedBlock previous, DatanodeInfo[] excludedNodes, long fileId, String[] favoredNodes) throws IOException {
        checkNNStartup();
        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug(
                    "*BLOCK* NameNode.addBlock: file " + src + " fileId=" + fileId + " for " + clientName);
        }
        HashSet<Node> excludedNodesSet = null;

        if (excludedNodes != null) {
            excludedNodesSet = new HashSet<>(excludedNodes.length);
            for (Node node : excludedNodes) {
                excludedNodesSet.add(node);
            }
        }
        List<String> favoredNodesList = (favoredNodes == null) ? null
                : Arrays.asList(favoredNodes);
        LocatedBlock locatedBlock = namesystem
                .getAdditionalBlock(src, fileId, clientName, previous, excludedNodesSet, favoredNodesList);
        /*if (locatedBlock != null) {
            metrics.incrAddBlockOps();
        }*/
        return locatedBlock;
    }

    @Override // ClientProtocol
    public void addBlockChecksum(String src, int blockIndex, long checksum)
            throws IOException {
        checkNNStartup();
        namesystem.addBlockChecksum(src, blockIndex, checksum);
    }

    @Override // ClientProtocol
    public LocatedBlock getAdditionalDatanode(final String src,
                                              final long fileId, final ExtendedBlock blk,
                                              final DatanodeInfo[] existings,
                                              final String[] existingStorageIDs,
                                              final DatanodeInfo[] excludes,
                                              final int numAdditionalNodes,
                                              final String clientName) throws IOException {
        checkNNStartup();
        if (LOG.isDebugEnabled()) {
            LOG.debug("getAdditionalDatanode: src=" + src
                    + ", fileId=" + fileId
                    + ", blk=" + blk
                    + ", existings=" + Arrays.asList(existings)
                    + ", excludes=" + Arrays.asList(excludes)
                    + ", numAdditionalNodes=" + numAdditionalNodes
                    + ", clientName=" + clientName);
        }

        //metrics.incrGetAdditionalDatanodeOps();

        HashSet<Node> excludeSet = null;
        if (excludes != null) {
            excludeSet = new HashSet<>(excludes.length);
            for (Node node : excludes) {
                excludeSet.add(node);
            }
        }
        return namesystem.getAdditionalDatanode(src, fileId, blk, existings,
                existingStorageIDs, excludeSet, numAdditionalNodes, clientName);
    }

    @Override
    public boolean setSafeMode(HdfsConstants.SafeModeAction action, boolean isChecked) throws IOException {
        return false;
    }

    @Override // ClientProtocol
    public void setQuota(String path, long namespaceQuota, long storagespaceQuota,
                         StorageType type)
            throws IOException {
        checkNNStartup();
        namesystem.setQuota(path, namespaceQuota, storagespaceQuota, type);
    }

    @Override // ClientProtocol
    public CorruptFileBlocks listCorruptFileBlocks(String path, String cookie)
            throws IOException {
        checkNNStartup();
        String[] cookieTab = new String[]{cookie};
        Collection<FSNameSystem.CorruptFileBlockInfo> fbs =
                namesystem.listCorruptFileBlocks(path, cookieTab);

        String[] files = new String[fbs.size()];
        int i = 0;
        for (FSNameSystem.CorruptFileBlockInfo fb : fbs) {
            files[i++] = fb.path;
        }
        return new CorruptFileBlocks(files, cookieTab[0]);
    }

    @Override // ClientProtocol
    public LocatedBlock updateBlockForPipeline(ExtendedBlock block,
                                               String clientName) throws IOException {
        checkNNStartup();
        return namesystem.updateBlockForPipeline(block, clientName);
    }

    @Override
    public boolean rename(String src, String dst) throws UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public void rename2(String src, String dst, Options.Rename... options) throws AccessControlException,
            DSQuotaExceededException, FileAlreadyExistsException, FileNotFoundException, NSQuotaExceededException,
            ParentNotDirectoryException, SafeModeException, UnresolvedLinkException, IOException {
        // Check that we've started up.
        checkNNStartup();

        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug("*DIR* NameNode.rename: " + src + " to " + dst);
        }

        // Check that the path is not too long now.
        if (!checkPathLength(dst)) {
            throw new IOException("rename: Pathname too long.  Limit " + MAX_PATH_LENGTH + " characters, " + MAX_PATH_DEPTH + " levels.");
        }

        LOG.debug("Not checking for duplicate request in retry cache bc caching has not been implemented yet.");
        /*RetryCacheEntry cacheEntry = LightWeightCacheDistributed.getTransactional();
        if (cacheEntry != null && cacheEntry.isSuccess()) {
            return; // Return previous response
        }*/

        boolean success = false;
        try {
            namesystem.renameTo(src, dst, options);
            success = true;
        } finally {
            LOG.debug("Not adding result to retry cache bc caching has not been implemented yet.");
            /*LightWeightCacheDistributed.putTransactional(success);*/
        }
        //metrics.incrFilesRenamed();
    }

    @Override // RefreshAuthorizationPolicyProtocol
    public void refreshServiceAcl() throws IOException {
        checkNNStartup();
        if (!serviceAuthEnabled) {
            throw new AuthorizationException(
                    "Service Level Authorization not enabled!");
        }

        this.clientRpcServer
                .refreshServiceAcl(new Configuration(), new HDFSPolicyProvider());
        if (this.serviceRpcServer != null) {
            this.serviceRpcServer
                    .refreshServiceAcl(new Configuration(), new HDFSPolicyProvider());
        }
    }
}
