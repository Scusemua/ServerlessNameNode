package org.apache.hadoop.hdfs.server.namenode;

import com.google.protobuf.BlockingService;
import io.hops.leader_election.node.ActiveNode;
import io.hops.leader_election.node.SortedActiveNodeList;
import io.hops.metadata.hdfs.entity.EncodingPolicy;
import io.hops.metadata.hdfs.entity.EncodingStatus;
import io.hops.metadata.hdfs.entity.MetaStatus;
import io.hops.metadata.hdfs.entity.RetryCacheEntry;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CryptoProtocolVersion;
import org.apache.hadoop.fs.BatchedRemoteIterator.BatchedEntries;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.*;
import org.apache.hadoop.hdfs.DFSConfigKeys;
import org.apache.hadoop.hdfs.DFSUtil;
import org.apache.hadoop.hdfs.HDFSPolicyProvider;
import org.apache.hadoop.hdfs.protocol.*;
import org.apache.hadoop.hdfs.protocol.HdfsConstants.DatanodeReportType;
import org.apache.hadoop.hdfs.protocol.HdfsConstants.RollingUpgradeAction;
import org.apache.hadoop.hdfs.protocolPB.*;
import org.apache.hadoop.hdfs.security.token.block.DataEncryptionKey;
import org.apache.hadoop.hdfs.security.token.block.ExportedBlockKeys;
import org.apache.hadoop.hdfs.security.token.delegation.DelegationTokenIdentifier;
import org.apache.hadoop.hdfs.server.blockmanagement.BRLoadBalancingOverloadException;
import org.apache.hadoop.hdfs.server.namenode.metrics.NameNodeMetrics;
import org.apache.hadoop.hdfs.server.namenode.web.resources.NamenodeWebHdfsMethods;
import org.apache.hadoop.hdfs.server.protocol.*;
import org.apache.hadoop.io.EnumSetWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.*;
import org.apache.hadoop.ipc.proto.GenericRefreshProtocolProtos;
import org.apache.hadoop.ipc.proto.RefreshCallQueueProtocolProtos;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolPB;
import org.apache.hadoop.ipc.protocolPB.GenericRefreshProtocolServerSideTranslatorPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolPB;
import org.apache.hadoop.ipc.protocolPB.RefreshCallQueueProtocolServerSideTranslatorPB;
import org.apache.hadoop.net.Node;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.security.authorize.AuthorizationException;
import org.apache.hadoop.security.proto.RefreshAuthorizationPolicyProtocolProtos;
import org.apache.hadoop.security.proto.RefreshUserMappingsProtocolProtos;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshAuthorizationPolicyProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolPB;
import org.apache.hadoop.security.protocolPB.RefreshUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.security.token.SecretManager;
import org.apache.hadoop.security.token.SecretManager.InvalidToken;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.tools.proto.GetUserMappingsProtocolProtos;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolPB;
import org.apache.hadoop.tools.protocolPB.GetUserMappingsProtocolServerSideTranslatorPB;
import org.apache.hadoop.tracing.SpanReceiverInfo;
import org.apache.hadoop.tracing.TraceAdminPB;
import org.apache.hadoop.tracing.TraceAdminProtocolPB;
import org.apache.hadoop.tracing.TraceAdminProtocolServerSideTranslatorPB;
import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

import static org.apache.hadoop.hdfs.DFSConfigKeys.*;
import static org.apache.hadoop.hdfs.protocol.HdfsConstants.MAX_PATH_DEPTH;
import static org.apache.hadoop.hdfs.protocol.HdfsConstants.MAX_PATH_LENGTH;
import static org.apache.hadoop.hdfs.server.namenode.ServerlessNameNode.getRemoteUser;

public class ServerlessNameNodeRpcServer implements NamenodeProtocols {
    // Dependencies from other parts of NN.
    protected final ServerlessNameNode nn;
    protected FSNameSystem namesystem;
    private final NameNodeMetrics metrics;

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

    public ServerlessNameNodeRpcServer(Configuration conf, ServerlessNameNode nn) throws IOException {
        this.nn = nn;
        this.namesystem = nn.getNamesystem();
        this.metrics = ServerlessNameNode.getNameNodeMetrics();

        int handlerCount = conf.getInt(DFS_NAMENODE_HANDLER_COUNT_KEY,
                DFS_NAMENODE_HANDLER_COUNT_DEFAULT);

        RPC.setProtocolEngine(conf, ClientNamenodeProtocolPB.class,
                ProtobufRpcEngine.class);

        ClientNamenodeProtocolServerSideTranslatorPB
                clientProtocolServerTranslator =
                new ClientNamenodeProtocolServerSideTranslatorPB(this);
        BlockingService clientNNPbService = org.apache.hadoop.hdfs.protocol.proto.ClientNamenodeProtocolProtos.ClientNamenodeProtocol.
                newReflectiveBlockingService(clientProtocolServerTranslator);

        DatanodeProtocolServerSideTranslatorPB dnProtoPbTranslator =
                new DatanodeProtocolServerSideTranslatorPB(this);
        BlockingService dnProtoPbService = org.apache.hadoop.hdfs.protocol.proto.DatanodeProtocolProtos.DatanodeProtocolService
                .newReflectiveBlockingService(dnProtoPbTranslator);

        NamenodeProtocolServerSideTranslatorPB namenodeProtocolXlator =
                new NamenodeProtocolServerSideTranslatorPB(this);
        BlockingService NNPbService = org.apache.hadoop.hdfs.protocol.proto.NamenodeProtocolProtos.NamenodeProtocolService
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

    @Override
    public SortedActiveNodeList getActiveNamenodesForClient() throws IOException {
        return nn.getActiveNameNodes();
    }

    @Override
    public void changeConf(List<String> props, List<String> newVals) throws IOException {

    }

    @Override // ClientProtocol
    public long getPreferredBlockSize(String filename) throws IOException {
        checkNNStartup();
        return namesystem.getPreferredBlockSize(filename);
    }

    @Override // ClientProtocol
    public FsServerDefaults getServerDefaults() throws IOException {
        checkNNStartup();
        return namesystem.getServerDefaults();
    }

    @Override // ClientProtocol
    public HdfsFileStatus create(String src, FsPermission masked,
                                 String clientName, EnumSetWritable<CreateFlag> flag, boolean createParent,
                                 short replication, long blockSize, CryptoProtocolVersion[] supportedVersions, EncodingPolicy policy)
            throws IOException {
        checkNNStartup();
        HdfsFileStatus stat =
                create(src, masked, clientName, flag, createParent, replication,
                        blockSize, supportedVersions);
        if (policy != null) {
            if (!namesystem.isErasureCodingEnabled()) {
                throw new IOException("Requesting encoding although erasure coding" +
                        " was disabled");
            }
            LOG.info("Create file " + src + " with policy " + policy.toString());
            namesystem.addEncodingStatus(src, policy,
                    EncodingStatus.Status.ENCODING_REQUESTED, false);
        }
        return stat;
    }

    @Override // ClientProtocol
    public HdfsFileStatus create(String src, FsPermission masked,
                                 String clientName, EnumSetWritable<CreateFlag> flag, boolean createParent,
                                 short replication, long blockSize,
                                 CryptoProtocolVersion[] supportedVersions) throws IOException {
        checkNNStartup();
        String clientMachine = getClientMachine();
        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug(
                    "*DIR* NameNode.create: file " + src + " for " + clientName + " at " +
                            clientMachine);
        }
        if (!checkPathLength(src)) {
            throw new IOException(
                    "create: Pathname too long.  Limit " + MAX_PATH_LENGTH +
                            " characters, " + MAX_PATH_DEPTH + " levels.");
        }
        HdfsFileStatus stat = namesystem.startFile(src, new PermissionStatus(
                        getRemoteUser().getShortUserName(), null,
                        masked), clientName, clientMachine, flag.get(), createParent,
                replication, blockSize, supportedVersions);
        metrics.incrFilesCreated();
        metrics.incrCreateFileOps();
        return stat;
    }

    @Override
    public LastBlockWithStatus append(String src, String clientName, EnumSetWritable<CreateFlag> flag) throws AccessControlException, DSQuotaExceededException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {
        return null;
    }

    @Override
    public boolean setReplication(String src, short replication) throws AccessControlException, DSQuotaExceededException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public BlockStoragePolicy getStoragePolicy(byte storagePolicyID) throws IOException {
        return null;
    }

    @Override
    public BlockStoragePolicy[] getStoragePolicies() throws IOException {
        return new BlockStoragePolicy[0];
    }

    @Override
    public void setStoragePolicy(String src, String policyName) throws UnresolvedLinkException, FileNotFoundException, QuotaExceededException, IOException {

    }

    @Override
    public void setMetaStatus(String src, MetaStatus metaStatus) throws AccessControlException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {

    }

    @Override
    public void setPermission(String src, FsPermission permission) throws AccessControlException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {

    }

    @Override
    public void setOwner(String src, String username, String groupname) throws AccessControlException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {

    }

    @Override // ClientProtocol
    public void renewLease(String clientName) throws IOException {
        checkNNStartup();
        namesystem.renewLease(clientName);
    }

    @Override
    public boolean recoverLease(String src, String clientName) throws IOException {
        return false;
    }

    @Override
    public long[] getStats() throws IOException {
        return new long[0];
    }

    @Override
    public DatanodeInfo[] getDatanodeReport(DatanodeReportType type) throws IOException {
        return new DatanodeInfo[0];
    }

    @Override
    public DatanodeStorageReport[] getDatanodeStorageReport(DatanodeReportType type) throws IOException {
        return new DatanodeStorageReport[0];
    }

    /**
     * Start client and service RPC servers.
     */
    void start() {
        clientRpcServer.start();
        if (serviceRpcServer != null) {
            serviceRpcServer.start();
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
    public long getBlockChecksum(String src, int blockIndex) throws IOException {
        checkNNStartup();
        return namesystem.getBlockChecksum(src, blockIndex);
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
    public Token<DelegationTokenIdentifier> getDelegationToken(Text renewer)
            throws IOException {
        checkNNStartup();
        return namesystem.getDelegationToken(renewer);
    }

    @Override // ClientProtocol
    public long renewDelegationToken(Token<DelegationTokenIdentifier> token)
            throws InvalidToken, IOException {
        checkNNStartup();
        return namesystem.renewDelegationToken(token);
    }

    @Override // ClientProtocol
    public void cancelDelegationToken(Token<DelegationTokenIdentifier> token)
            throws IOException {
        checkNNStartup();
        namesystem.cancelDelegationToken(token);
    }

    @Override
    public DataEncryptionKey getDataEncryptionKey() throws IOException {
        return null;
    }

    @Override
    public void ping() throws IOException {

    }

    @Override // ClientProtocol
    public HdfsFileStatus getFileInfo(String src) throws IOException {
        checkNNStartup();
        metrics.incrFileInfoOps();
        return namesystem.getFileInfo(src, true);
    }

    @Override
    public boolean isFileClosed(String src) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public HdfsFileStatus getFileLinkInfo(String src) throws AccessControlException, UnresolvedLinkException, IOException {
        return null;
    }

    @Override
    public ContentSummary getContentSummary(String path) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {
        return null;
    }

    private static String getClientMachine() {
        String clientMachine = NamenodeWebHdfsMethods.getRemoteAddress();
        if (clientMachine == null) { //not a web client
            clientMachine = Server.getRemoteAddress();
        }
        if (clientMachine == null) { //not a RPC client
            clientMachine = "";
        }
        return clientMachine;
    }

    @Override // ClientProtocol
    public LocatedBlocks getBlockLocations(String src, long offset, long length)
            throws IOException {
        checkNNStartup();
        metrics.incrGetBlockLocations();
        return namesystem
                .getBlockLocations(getClientMachine(), src, offset, length);
    }

    @Override
    public LocatedBlocks getMissingBlockLocations(String filePath) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {
        return null;
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
    public boolean complete(String src, String clientName, ExtendedBlock last, long fileId, byte[] data) throws AccessControlException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public DatanodeRegistration registerDatanode(DatanodeRegistration registration) throws IOException {
        return null;
    }

    @Override
    public HeartbeatResponse sendHeartbeat(DatanodeRegistration registration, StorageReport[] reports, long dnCacheCapacity, long dnCacheUsed, int xmitsInProgress, int xceiverCount, int failedVolumes, VolumeFailureSummary volumeFailureSummary) throws IOException {
        return null;
    }

    @Override
    public DatanodeCommand reportHashes(DatanodeRegistration registration, String poolId, StorageBlockReport[] reports) throws IOException {
        return null;
    }

    @Override
    public DatanodeCommand blockReport(DatanodeRegistration registration, String poolId, StorageBlockReport[] reports, BlockReportContext context) throws IOException {
        return null;
    }

    @Override
    public DatanodeCommand cacheReport(DatanodeRegistration registration, String poolId, List<Long> blockIds, long cacheCapacity, long cacheUsed) throws IOException {
        return null;
    }

    @Override
    public void blockReceivedAndDeleted(DatanodeRegistration registration, String poolId, StorageReceivedDeletedBlocks[] rcvdAndDeletedBlocks) throws IOException {

    }

    @Override
    public void errorReport(DatanodeRegistration registration, int errorCode, String msg) throws IOException {

    }

    @Override
    public BlocksWithLocations getBlocks(DatanodeInfo datanode, long size) throws IOException {
        return null;
    }

    @Override
    public ExportedBlockKeys getBlockKeys() throws IOException {
        return null;
    }

    @Override
    public NamespaceInfo versionRequest() throws IOException {
        return null;
    }

    @Override
    public void reportBadBlocks(LocatedBlock[] blocks) throws IOException {

    }

    @Override
    public void commitBlockSynchronization(ExtendedBlock block, long newgenerationstamp, long newlength, boolean closeFile, boolean deleteblock, DatanodeID[] newtargets, String[] newtargetstorages) throws IOException {

    }

    @Override
    public SortedActiveNodeList getActiveNamenodes() throws IOException {
        return null;
    }

    @Override
    public ActiveNode getNextNamenodeToSendBlockReport(long noOfBlks, DatanodeRegistration nodeReg) throws IOException {
        return null;
    }

    @Override
    public void blockReportCompleted(DatanodeRegistration nodeReg, DatanodeStorage[] storages, boolean success) throws IOException {

    }

    @Override
    public byte[] getSmallFileData(int id) throws IOException {
        return new byte[0];
    }

    @Override // ClientProtocol
    public boolean delete(String src, boolean recursive) throws IOException {
        checkNNStartup();
        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug(
                    "*DIR* Namenode.delete: src=" + src + ", recursive=" + recursive);
        }

        boolean ret;
        ret = namesystem.delete(src, recursive);

        if (ret) {
            metrics.incrDeleteFileOps();
        }
        return ret;
    }

    @Override
    public boolean mkdirs(String src, FsPermission masked, boolean createParent) throws AccessControlException, FileAlreadyExistsException, FileNotFoundException, NSQuotaExceededException, ParentNotDirectoryException, SafeModeException, UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public DirectoryListing getListing(String src, byte[] startAfter, boolean needLocation) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {
        return null;
    }

    @Override // ClientProtocol
    public boolean setSafeMode(HdfsConstants.SafeModeAction action, boolean isChecked)
            throws IOException {
        checkNNStartup();
        return namesystem.setSafeMode(action);
    }

    @Override
    public void refreshNodes() throws IOException {

    }

    @Override
    public RollingUpgradeInfo rollingUpgrade(RollingUpgradeAction action) throws IOException {
        return null;
    }

    @Override // ClientProtocol
    public void setQuota(String path, long namespaceQuota, long storagespaceQuota,
                         StorageType type)
            throws IOException {
        checkNNStartup();
        namesystem.setQuota(path, namespaceQuota, storagespaceQuota, type);
    }

    @Override
    public void fsync(String src, long inodeId, String client, long lastBlockLength) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {

    }

    @Override
    public void setTimes(String src, long mtime, long atime) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {

    }

    @Override
    public void createSymlink(String target, String link, FsPermission dirPerm, boolean createParent) throws AccessControlException, FileAlreadyExistsException, FileNotFoundException, ParentNotDirectoryException, SafeModeException, UnresolvedLinkException, IOException {

    }

    @Override
    public String getLinkTarget(String path) throws AccessControlException, FileNotFoundException, IOException {
        return null;
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

    @Override
    public void setBalancerBandwidth(long bandwidth) throws IOException {

    }

    @Override // ClientProtocol
    public LocatedBlock updateBlockForPipeline(ExtendedBlock block,
                                               String clientName) throws IOException {
        checkNNStartup();
        return namesystem.updateBlockForPipeline(block, clientName);
    }

    @Deprecated
    @Override // ClientProtocol
    public boolean rename(String src, String dst) throws IOException {
        checkNNStartup();
        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug("*DIR* NameNode.rename: " + src + " to " + dst);
        }
        if (!checkPathLength(dst)) {
            throw new IOException(
                    "rename: Pathname too long.  Limit " + MAX_PATH_LENGTH +
                            " characters, " + MAX_PATH_DEPTH + " levels.");
        }

        RetryCacheEntry cacheEntry = LightWeightCacheDistributed.getTransactional();
        if (cacheEntry != null && cacheEntry.isSuccess()) {
            return true; // Return previous response
        }

        boolean ret = false;
        try{
            ret = namesystem.renameTo(src, dst);
        } finally {
            LightWeightCacheDistributed.putTransactional(ret);
        }
        if (ret) {
            metrics.incrFilesRenamed();
        }
        return ret;
    }

    @Override
    public void concat(String trg, String[] srcs) throws IOException, UnresolvedLinkException {

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

    @Override
    public boolean truncate(String src, long newLength, String clientName) throws AccessControlException, FileNotFoundException, SafeModeException, UnresolvedLinkException, IOException {
        return false;
    }

    @Override // ClientProtocol
    public EncodingStatus getEncodingStatus(String filePath) throws IOException {
        checkNNStartup();
        EncodingStatus status = namesystem.getEncodingStatus(filePath);

        if (status.getStatus() == EncodingStatus.Status.DELETED) {
            throw new IOException("Trying to read encoding status of a deleted file");
        }

        return status;
    }

    @Override
    public void encodeFile(String filePath, EncodingPolicy policy) throws IOException {

    }

    @Override
    public void revokeEncoding(String filePath, short replication) throws IOException {

    }

    @Override
    public LocatedBlock getRepairedBlockLocations(String sourcePath, String parityPath, LocatedBlock block, boolean isParity) throws IOException {
        return null;
    }

    @Override
    public void checkAccess(String path, FsAction mode) throws IOException {

    }

    @Override
    public LastUpdatedContentSummary getLastUpdatedContentSummary(String path) throws AccessControlException, FileNotFoundException, UnresolvedLinkException, IOException {
        return null;
    }

    @Override
    public void modifyAclEntries(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public void removeAclEntries(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public void removeDefaultAcl(String src) throws IOException {

    }

    @Override
    public void removeAcl(String src) throws IOException {

    }

    @Override
    public void setAcl(String src, List<AclEntry> aclSpec) throws IOException {

    }

    @Override
    public AclStatus getAclStatus(String src) throws IOException {
        return null;
    }

    @Override
    public void createEncryptionZone(String src, String keyName) throws IOException {

    }

    @Override
    public EncryptionZone getEZForPath(String src) throws IOException {
        return null;
    }

    @Override
    public BatchedEntries<EncryptionZone> listEncryptionZones(long prevId) throws IOException {
        return null;
    }

    @Override
    public void setXAttr(String src, XAttr xAttr, EnumSet<XAttrSetFlag> flag) throws IOException {

    }

    @Override
    public List<XAttr> getXAttrs(String src, List<XAttr> xAttrs) throws IOException {
        return null;
    }

    @Override
    public List<XAttr> listXAttrs(String src) throws IOException {
        return null;
    }

    @Override
    public void removeXAttr(String src, XAttr xAttr) throws IOException {

    }

    @Override
    public long addCacheDirective(CacheDirectiveInfo directive, EnumSet<CacheFlag> flags) throws IOException {
        return 0;
    }

    @Override
    public void modifyCacheDirective(CacheDirectiveInfo directive, EnumSet<CacheFlag> flags) throws IOException {

    }

    @Override
    public void removeCacheDirective(long id) throws IOException {

    }

    @Override
    public BatchedEntries<CacheDirectiveEntry> listCacheDirectives(long prevId, CacheDirectiveInfo filter) throws IOException {
        return null;
    }

    @Override
    public void addCachePool(CachePoolInfo info) throws IOException {

    }

    @Override
    public void modifyCachePool(CachePoolInfo req) throws IOException {

    }

    @Override
    public void removeCachePool(String pool) throws IOException {

    }

    @Override
    public BatchedEntries<CachePoolEntry> listCachePools(String prevPool) throws IOException {
        return null;
    }

    @Override
    public void addUser(String userName) throws IOException {

    }

    @Override
    public void addGroup(String groupName) throws IOException {

    }

    @Override
    public void addUserToGroup(String userName, String groupName) throws IOException {

    }

    @Override
    public void removeUser(String userName) throws IOException {

    }

    @Override
    public void removeGroup(String groupName) throws IOException {

    }

    @Override
    public void removeUserFromGroup(String userName, String groupName) throws IOException {

    }

    @Override
    public void invCachesUserRemoved(String userName) throws IOException {

    }

    @Override
    public void invCachesGroupRemoved(String groupName) throws IOException {

    }

    @Override
    public void invCachesUserRemovedFromGroup(String userName, String groupName) throws IOException {

    }

    @Override
    public void invCachesUserAddedToGroup(String userName, String groupName) throws IOException {

    }

    @Override
    public long getEpochMS() throws IOException {
        return 0;
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

    @Override
    public SpanReceiverInfo[] listSpanReceivers() throws IOException {
        return new SpanReceiverInfo[0];
    }

    @Override
    public long addSpanReceiver(SpanReceiverInfo desc) throws IOException {
        return 0;
    }

    @Override
    public void removeSpanReceiver(long spanReceiverId) throws IOException {

    }

    @Override
    public Collection<RefreshResponse> refresh(String identifier, String[] args) throws IOException {
        return null;
    }

    @Override
    public void refreshCallQueue() throws IOException {

    }

    @Override
    public void refreshUserToGroupsMappings() throws IOException {

    }

    @Override
    public void refreshSuperUserGroupsConfiguration() throws IOException {

    }

    @Override
    public String[] getGroupsForUser(String user) throws IOException {
        return new String[0];
    }
}
