package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.exceptions.DSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.NSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.QuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.*;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.delegation.DelegationTokenIdentifier;
import com.gmail.benrcarver.serverlessnamenode.protocol.NamenodeProtocols;
import com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.BRLoadBalancingOverloadException;
import com.google.protobuf.BlockingService;
import io.hops.transaction.handler.HDFSOperationType;
import io.hops.transaction.handler.HopsTransactionalRequestHandler;
import io.hops.transaction.lock.TransactionLocks;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RPC;
import org.apache.hadoop.ipc.WritableRpcEngine;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolPB;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolServerSideTranslatorPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolPB;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.tracing.TraceAdminProtocolPB;
import org.apache.hadoop.tracing.TraceAdminProtocolServerSideTranslatorPB;
import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.FileAlreadyExistsException;
import java.security.AccessControlException;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.DFS_NAMENODE_HANDLER_COUNT_DEFAULT;
import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.DFS_NAMENODE_HANDLER_COUNT_KEY;
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

    private static final Logger LOG = ServerlessNameNode.LOG;
    private static final Logger stateChangeLog = ServerlessNameNode.stateChangeLog;
    private static final Logger blockStateChangeLog = ServerlessNameNode.blockStateChangeLog;

    public ServerlessNameNodeRPCServer(ServerlessNameNode nameNode) {
        this.nn = nameNode;
    }

    public NameNodeRpcServer(Configuration conf, ServerlessNameNode nn) throws IOException {
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
        BlockingService clientNNPbService = ClientNamenodeProtocol.
                newReflectiveBlockingService(clientProtocolServerTranslator);

        DatanodeProtocolServerSideTranslatorPB dnProtoPbTranslator =
                new DatanodeProtocolServerSideTranslatorPB(this);
        BlockingService dnProtoPbService = DatanodeProtocolService
                .newReflectiveBlockingService(dnProtoPbTranslator);

        NamenodeProtocolServerSideTranslatorPB namenodeProtocolXlator =
                new NamenodeProtocolServerSideTranslatorPB(this);
        BlockingService NNPbService = NamenodeProtocolService
                .newReflectiveBlockingService(namenodeProtocolXlator);

        RefreshAuthorizationPolicyProtocolServerSideTranslatorPB
                refreshAuthPolicyXlator =
                new RefreshAuthorizationPolicyProtocolServerSideTranslatorPB(this);
        BlockingService refreshAuthService =
                RefreshAuthorizationPolicyProtocolService
                        .newReflectiveBlockingService(refreshAuthPolicyXlator);

        RefreshUserMappingsProtocolServerSideTranslatorPB refreshUserMappingXlator =
                new RefreshUserMappingsProtocolServerSideTranslatorPB(this);
        BlockingService refreshUserMappingService =
                RefreshUserMappingsProtocolService
                        .newReflectiveBlockingService(refreshUserMappingXlator);

        RefreshCallQueueProtocolServerSideTranslatorPB refreshCallQueueXlator =
                new RefreshCallQueueProtocolServerSideTranslatorPB(this);
        BlockingService refreshCallQueueService = RefreshCallQueueProtocolService
                .newReflectiveBlockingService(refreshCallQueueXlator);

        GenericRefreshProtocolServerSideTranslatorPB genericRefreshXlator =
                new GenericRefreshProtocolServerSideTranslatorPB(this);
        BlockingService genericRefreshService = GenericRefreshProtocolService
                .newReflectiveBlockingService(genericRefreshXlator);

        GetUserMappingsProtocolServerSideTranslatorPB getUserMappingXlator =
                new GetUserMappingsProtocolServerSideTranslatorPB(this);
        BlockingService getUserMappingService = GetUserMappingsProtocolService
                .newReflectiveBlockingService(getUserMappingXlator);

        TraceAdminProtocolServerSideTranslatorPB traceAdminXlator =
                new TraceAdminProtocolServerSideTranslatorPB(this);
        BlockingService traceAdminService = TraceAdminService
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
                    org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB.class)
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
                org.apache.hadoop.hdfs.protocolPB.ClientNamenodeProtocolPB.class)
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
     * Check path length does not exceed maximum.  Returns true if
     * length and depth are okay.  Returns false if length is too long
     * or depth is too great.
     */
    private boolean checkPathLength(String src) {
        Path srcPath = new Path(src);
        return (src.length() <= MAX_PATH_LENGTH &&
                srcPath.depth() <= MAX_PATH_DEPTH);
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
}
