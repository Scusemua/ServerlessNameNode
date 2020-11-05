package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.hdfs.XAttrHelper;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.client.HdfsClientConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsConstants;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.fs.XAttr;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.protocol.FsPermissionExtension;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.delegation.DelegationTokenIdentifier;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.*;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.blockmanagement.*;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.HdfsServerConstants;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.StorageInfo;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.rpc.Status;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import io.hops.HdfsStorageFactory;
import io.hops.HdfsVariables;
import io.hops.common.CountersQueue;
import io.hops.common.INodeUtil;
import io.hops.erasure_coding.ErasureCodingManager;
import io.hops.exception.LockUpgradeException;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.exception.TransientStorageException;
import io.hops.leader_election.node.ActiveNode;
import io.hops.metadata.hdfs.dal.*;
import io.hops.metadata.hdfs.entity.*;
import io.hops.transaction.EntityManager;
import io.hops.transaction.handler.EncodingStatusOperationType;
import io.hops.transaction.handler.HDFSOperationType;
import io.hops.transaction.handler.HopsTransactionalRequestHandler;
import io.hops.transaction.handler.LightWeightRequestHandler;
import io.hops.transaction.lock.INodeLock;
import io.hops.transaction.lock.LockFactory;
import io.hops.transaction.lock.TransactionLockTypes;
import io.hops.transaction.lock.TransactionLockTypes.INodeLockType;
import io.hops.transaction.lock.TransactionLockTypes.INodeResolveType;
import io.hops.transaction.lock.TransactionLockTypes.LockType;
import io.hops.transaction.lock.TransactionLocks;
import io.hops.util.ByteArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.HadoopIllegalArgumentException;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.*;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.ipc.NotALeaderException;
import org.apache.hadoop.ipc.ProtobufRpcEngine;
import org.apache.hadoop.ipc.RetriableException;
import org.apache.hadoop.metrics2.annotation.Metric;
import org.apache.hadoop.metrics2.util.MBeans;
import org.apache.hadoop.net.NetworkTopology;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.UserGroupInformation.AuthenticationMethod;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.security.token.delegation.DelegationKey;
import org.apache.hadoop.security.token.delegation.web.DelegationTokenManager;
import org.apache.hadoop.util.Daemon;
import org.apache.hadoop.util.DataChecksum;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.management.ObjectName;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.*;
import static com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.HdfsServerConstants.SECURITY_XATTR_UNREADABLE_BY_SUPERUSER;
import static com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode.INodeDirectory.getRootDir;
import static io.hops.transaction.lock.LockFactory.getInstance;
import static org.apache.hadoop.ipc.Server.getRemoteIp;
import static org.apache.hadoop.ipc.Server.getRemoteUser;
import static org.apache.hadoop.util.Time.now;

public class FSNameSystem implements NameSystem {
    public static final Log LOG = LogFactory.getLog(FSNameSystem.class);

    // Block pool ID used by this namenode
    // HOP made it final and now its value is read from the config file. all
    // namenodes should have same block pool id
    private final String blockPoolId;

    private volatile boolean hasResourcesAvailable = true;
    private volatile boolean fsRunning = true;

    private ServerlessNameNode nameNode;

    // Tracks whether the default audit logger is the only configured audit
    // logger; this allows isAuditEnabled() to return false in case the
    // underlying logger is disabled, and avoid some unnecessary work.
    private final boolean isDefaultAuditLogger;
    private final List<AuditLogger> auditLoggers;

    /**
     * Logger for audit events, noting successful FSNameSystem operations. Emits
     * to FSNameSystem.audit at INFO. Each event causes a set of tab-separated
     * <code>key=value</code> pairs to be written for the following properties:
     * <code>
     * ugi=&lt;ugi in RPC&gt;
     * ip=&lt;remote IP&gt;
     * cmd=&lt;command&gt;
     * src=&lt;src path&gt;
     * dst=&lt;dst path (optional)&gt;
     * perm=&lt;permissions (optional)&gt;
     * </code>
     */
    public static final Log auditLog =
            LogFactory.getLog(FSNameSystem.class.getName() + ".audit");

    static final int DEFAULT_MAX_CORRUPT_FILEBLOCKS_RETURNED = 100;
    static int BLOCK_DELETION_INCREMENT = 1000;

    private final boolean isPermissionEnabled;
    private final UserGroupInformation fsOwner;
    private final String superGroup;

    // Scan interval is not configurable.
    private static final long DELEGATION_TOKEN_REMOVER_SCAN_INTERVAL =
            TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
    private final DelegationTokenManager.DelegationTokenSecretManager dtSecretManager;
    private final boolean alwaysUseDelegationTokensForTests;

    private static final XPath.Step STEP_AWAITING_REPORTED_BLOCKS =
            new XPath.Step(StepType.AWAITING_REPORTED_BLOCKS);

    // Tracks whether the default audit logger is the only configured audit
    // logger; this allows isAuditEnabled() to return false in case the
    // underlying logger is disabled, and avoid some unnecessary work.
    private final boolean isDefaultAuditLogger;
    private final List<AuditLogger> auditLoggers;

    /**
     * The namespace tree.
     */
    FSDirectory dir;
    private final BlockManager blockManager;
    private final CacheManager cacheManager;
    private final DatanodeStatistics datanodeStatistics;

    // Block pool ID used by this namenode
    //HOP made it final and now its value is read from the config file. all
    // namenodes should have same block pool id
    private final String blockPoolId;

    final LeaseManager leaseManager = new LeaseManager(this);

    volatile private Daemon smmthread = null;  // SafeModeMonitor thread

    private Daemon nnrmthread = null; // NamenodeResourceMonitor thread

    private Daemon retryCacheCleanerThread = null;

    /**
     * The start time of the namesystem.
     */
    // private final long startTime = now();

    /**
     * The interval of namenode checking for the disk space availability
     */
    private final long resourceRecheckInterval;

    private ObjectName mbeanName;
    private ObjectName mxbeanName;

    // The actual resource checker instance.
    NameNodeResourceChecker nnResourceChecker;

    private final int maxDBTries;

    private final FsServerDefaults serverDefaults;
    private final boolean supportAppends;
    private final HdfsClientConfigKeys.BlockWrite.ReplaceDatanodeOnFailure dtpReplaceDatanodeOnFailure;

    private AtomicBoolean inSafeMode = new AtomicBoolean(false); // safe mode information

    private final long maxFsObjects;          // maximum number of fs objects

    private final long minBlockSize;         // minimum block size
    private final long maxBlocksPerFile;     // maximum # of blocks per file

    // precision of access times.
    private final long accessTimePrecision;

    private final Configuration conf;
    private final QuotaUpdateManager quotaUpdateManager;

    private final ExecutorService fsOperationsExecutor;
    private final boolean erasureCodingEnabled;
    private final ErasureCodingManager erasureCodingManager;

    static int DB_IN_MEMORY_BUCKET_SIZE;
    static int DB_ON_DISK_SMALL_BUCKET_SIZE;
    static int DB_ON_DISK_MEDIUM_BUCKET_SIZE;
    static int DB_ON_DISK_LARGE_BUCKET_SIZE;
    static int DB_MAX_SMALL_FILE_SIZE = 0;

    /** flag indicating whether replication queues have been initialized */
    boolean initializedReplQueues = false;

    boolean shouldPopulateReplicationQueue = false;

    /**
     * Whether the namenode is in the middle of starting the active service
     */
    private volatile boolean startingActiveService = false;

    //private KeyProviderCryptoExtension provider = null;

    private volatile boolean imageLoaded = false;

    private final boolean isRetryCacheEnabled;

    //Add delay for file system operations. Used only for testing
    private boolean isTestingSTO = false;
    private ThreadLocal<Times> delays = new ThreadLocal<Times>();
    long delayBeforeSTOFlag = 0; //This parameter can not be more than TxInactiveTimeout: 1.2 sec
    long delayAfterBuildingTree=0;

    int slicerBatchSize;
    int slicerNbThreads;

    private volatile AtomicBoolean forceReadTheSafeModeFromDB = new AtomicBoolean(true);

    @VisibleForTesting
    public final EncryptionZoneManager ezManager;

    /**
     * Caches frequently used file names used in {@link INode} to reuse
     * byte[] objects and reduce heap usage.
     */
    private final NameCache<ByteArray> nameCache;

    //private final TopConf topConf;
    //private TopMetrics topMetrics;

    private final int leaseCreationLockRows;

    /**
     * Create an FSNameSystem.
     *
     * @param conf
     *     configurationappendFileHopFS
     * @param namenode
     *     the namenode
     * @param ignoreRetryCache Whether or not should ignore the retry cache setup
     *                         step. For Secondary NN this should be set to true.
     * @throws IOException
     *      on bad configuration
     */
    FSNameSystem(Configuration conf, ServerlessNameNode namenode) throws IOException {
        try {
            provider = DFSUtil.createKeyProviderCryptoExtension(conf);
            if (provider == null) {
                LOG.info("No KeyProvider found.");
            } else {
                LOG.info("Found KeyProvider: " + provider.toString());
            }
            if (conf.getBoolean(DFS_NAMENODE_AUDIT_LOG_ASYNC_KEY,
                    DFS_NAMENODE_AUDIT_LOG_ASYNC_DEFAULT)) {
                LOG.info("Enabling async auditlog");
                enableAsyncAuditLog();
            }
            this.conf = conf;
            this.nameNode = namenode;
            resourceRecheckInterval =
                    conf.getLong(DFS_NAMENODE_RESOURCE_CHECK_INTERVAL_KEY,
                            DFS_NAMENODE_RESOURCE_CHECK_INTERVAL_DEFAULT);

            this.blockManager = new BlockManager(this, conf);
            this.erasureCodingEnabled =
                    ErasureCodingManager.isErasureCodingEnabled(conf);
            this.erasureCodingManager = new ErasureCodingManager(this, conf);

            DB_IN_MEMORY_BUCKET_SIZE = getDBFileInMemBucketSize();
            DB_ON_DISK_SMALL_BUCKET_SIZE = getDBFileSmallBucketSize();
            DB_ON_DISK_MEDIUM_BUCKET_SIZE = getDBFileMediumBucketSize();
            DB_ON_DISK_LARGE_BUCKET_SIZE = getDBFileLargeBucketSize();

            DB_MAX_SMALL_FILE_SIZE = conf.getInt(DFS_DB_FILE_MAX_SIZE_KEY,
                    DFS_DB_FILE_MAX_SIZE_DEFAULT);

            if (!(DB_IN_MEMORY_BUCKET_SIZE < DB_ON_DISK_SMALL_BUCKET_SIZE &&
                    DB_ON_DISK_SMALL_BUCKET_SIZE < DB_ON_DISK_MEDIUM_BUCKET_SIZE &&
                    DB_ON_DISK_MEDIUM_BUCKET_SIZE < DB_ON_DISK_LARGE_BUCKET_SIZE)){
                throw new IllegalArgumentException("The size for the database files is not correctly set");
            }

            this.datanodeStatistics =
                    blockManager.getDatanodeManager().getDatanodeStatistics();

            this.fsOwner = UserGroupInformation.getCurrentUser();
            this.superGroup = conf.get(DFS_PERMISSIONS_SUPERUSERGROUP_KEY,
                    DFS_PERMISSIONS_SUPERUSERGROUP_DEFAULT);
            this.isPermissionEnabled = conf.getBoolean(DFS_PERMISSIONS_ENABLED_KEY,
                    DFS_PERMISSIONS_ENABLED_DEFAULT);

            blockPoolId = StorageInfo.getStorageInfoFromDB().getBlockPoolId();
            blockManager.setBlockPoolId(blockPoolId);
            hopSpecificInitialization(conf);
            this.quotaUpdateManager = new QuotaUpdateManager(this, conf);
            fsOperationsExecutor = Executors.newFixedThreadPool(
                    conf.getInt(DFS_SUBTREE_EXECUTOR_LIMIT_KEY,
                            DFS_SUBTREE_EXECUTOR_LIMIT_DEFAULT));
            FSDirDeleteOp.BIGGEST_DELETABLE_DIR = conf.getLong(DFS_DIR_DELETE_BATCH_SIZE,
                    DFS_DIR_DELETE_BATCH_SIZE_DEFAULT);

            LOG.info("fsOwner             = " + fsOwner);
            LOG.info("superGroup          = " + superGroup);
            LOG.info("isPermissionEnabled = " + isPermissionEnabled);

            // Get the checksum type from config
            String checksumTypeStr =
                    conf.get(DFS_CHECKSUM_TYPE_KEY, DFS_CHECKSUM_TYPE_DEFAULT);
            DataChecksum.Type checksumType;
            try {
                checksumType = DataChecksum.Type.valueOf(checksumTypeStr);
            } catch (IllegalArgumentException iae) {
                throw new IOException(
                        "Invalid checksum type in " + DFS_CHECKSUM_TYPE_KEY + ": " +
                                checksumTypeStr);
            }

            this.serverDefaults = new FsServerDefaults(
                    conf.getLongBytes(DFS_BLOCK_SIZE_KEY, DFS_BLOCK_SIZE_DEFAULT),
                    conf.getInt(DFS_BYTES_PER_CHECKSUM_KEY,
                            DFS_BYTES_PER_CHECKSUM_DEFAULT),
                    conf.getInt(DFS_CLIENT_WRITE_PACKET_SIZE_KEY,
                            DFS_CLIENT_WRITE_PACKET_SIZE_DEFAULT),
                    (short) conf.getInt(DFS_REPLICATION_KEY, DFS_REPLICATION_DEFAULT),
                    conf.getInt(IO_FILE_BUFFER_SIZE_KEY, IO_FILE_BUFFER_SIZE_DEFAULT),
                    conf.getBoolean(DFS_ENCRYPT_DATA_TRANSFER_KEY,
                            DFS_ENCRYPT_DATA_TRANSFER_DEFAULT),
                    conf.getLong(FS_TRASH_INTERVAL_KEY, FS_TRASH_INTERVAL_DEFAULT),
                    checksumType, conf.getBoolean(DFS_NAMENODE_QUOTA_ENABLED_KEY,
                    DFS_NAMENODE_QUOTA_ENABLED_DEFAULT));

            this.maxFsObjects = conf.getLong(DFS_NAMENODE_MAX_OBJECTS_KEY,
                    DFS_NAMENODE_MAX_OBJECTS_DEFAULT);

            this.minBlockSize = conf.getLong(DFSConfigKeys.DFS_NAMENODE_MIN_BLOCK_SIZE_KEY,
                    DFSConfigKeys.DFS_NAMENODE_MIN_BLOCK_SIZE_DEFAULT);
            this.maxBlocksPerFile = conf.getLong(DFSConfigKeys.DFS_NAMENODE_MAX_BLOCKS_PER_FILE_KEY,
                    DFSConfigKeys.DFS_NAMENODE_MAX_BLOCKS_PER_FILE_DEFAULT);
            this.accessTimePrecision =
                    conf.getLong(DFS_NAMENODE_ACCESSTIME_PRECISION_KEY, DFS_NAMENODE_ACCESSTIME_PRECISION_DEFAULT);
            this.supportAppends =
                    conf.getBoolean(DFS_SUPPORT_APPEND_KEY, DFS_SUPPORT_APPEND_DEFAULT);
            LOG.info("Append Enabled: " + supportAppends);

            this.dtpReplaceDatanodeOnFailure = HdfsClientConfigKeys.BlockWrite.ReplaceDatanodeOnFailure.get(conf);


            // For testing purposes, allow the DT secret manager to be started regardless
            // of whether security is enabled.
            alwaysUseDelegationTokensForTests =
                    conf.getBoolean(DFS_NAMENODE_DELEGATION_TOKEN_ALWAYS_USE_KEY,
                            DFS_NAMENODE_DELEGATION_TOKEN_ALWAYS_USE_DEFAULT);

            this.dtSecretManager = createDelegationTokenSecretManager(conf);
            this.dir = new FSDirectory(this, conf);
            this.cacheManager = new CacheManager(this, conf, blockManager);
            this.topConf = new TopConf(conf);
            this.auditLoggers = initAuditLoggers(conf);
            this.isDefaultAuditLogger = auditLoggers.size() == 1 &&
                    auditLoggers.get(0) instanceof DefaultAuditLogger;
            this.isRetryCacheEnabled = conf.getBoolean(DFS_NAMENODE_ENABLE_RETRY_CACHE_KEY,
                    DFS_NAMENODE_ENABLE_RETRY_CACHE_DEFAULT);
            LightWeightCacheDistributed.enable = isRetryCacheEnabled;
            this.slicerBatchSize = conf.getInt(DFSConfigKeys.DFS_NAMENODE_SLICER_BATCH_SIZE,
                    DFSConfigKeys.DFS_NAMENODE_SLICER_BATCH_SIZE_DEFAULT);

            this.slicerNbThreads = conf.getInt(
                    DFSConfigKeys.DFS_NAMENODE_SLICER_NB_OF_THREADS,
                    DFSConfigKeys.DFS_NAMENODE_SLICER_NB_OF_THREADS_DEFAULT);

            this.maxDBTries = conf.getInt(DFSConfigKeys.DFS_NAMENODE_DB_CHECK_MAX_TRIES,
                    DFSConfigKeys.DFS_NAMENODE_DB_CHECK_MAX_TRIES_DEFAULT);
            DatanodeStorageInfo.BLOCKITERATOR_BATCH_SIZE = slicerBatchSize;
            leaseCreationLockRows = conf.getInt(DFS_LEASE_CREATION_LOCKS_COUNT_KEY,
                    DFS_LEASE_CREATION_LOCKS_COUNT_DEFAULT);
        } catch (IOException | RuntimeException e) {
            LOG.error(getClass().getSimpleName() + " initialization failed.", e);
            close();
            throw e;
        }
    }

    /**
     * Unlock a subtree in the filesystem tree.
     *
     * @param path
     *    the root of the subtree
     * @throws IOException
     */
    @VisibleForTesting
    void  unlockSubtreeInternal(final String path, final long ignoreStoInodeId) throws IOException {
        new HopsTransactionalRequestHandler(HDFSOperationType.RESET_SUBTREE_LOCK) {
            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = LockFactory.getInstance();
                INodeLock il = (INodeLock)lf.getINodeLock( TransactionLockTypes.INodeLockType.WRITE, TransactionLockTypes.INodeResolveType.PATH, path)
                        .setNameNodeID(nameNode.getId())
                        .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes())
                        .skipReadingQuotaAttr(!dir.isQuotaEnabled())
                        .setIgnoredSTOInodes(ignoreStoInodeId)
                        .enableHierarchicalLocking(conf.getBoolean(DFSConfigKeys.DFS_SUBTREE_HIERARCHICAL_LOCKING_KEY,
                                DFSConfigKeys.DFS_SUBTREE_HIERARCHICAL_LOCKING_KEY_DEFAULT));
                locks.add(il);
                locks.add(lf.getSubTreeOpsLock(TransactionLockTypes.LockType.WRITE, getSubTreeLockPathPrefix(path), false));
            }

            @Override
            public Object performTask() throws IOException {
                INodesInPath inodesInPath = dir.getINodesInPath(path, false);
                INode inode = inodesInPath.getLastINode();
                if (inode != null && inode.isSTOLocked()) {
                    inode.setSubtreeLocked(false);
                    EntityManager.update(inode);
                }
                SubTreeOperation subTreeOp = EntityManager.find(SubTreeOperation.Finder.ByPath, getSubTreeLockPathPrefix(
                        path));
                EntityManager.remove(subTreeOp);
                return null;
            }
        }.handle(this);
    }

    /**
     * @return true if delegation token operation is allowed
     */
    private boolean isAllowedDelegationTokenOp() throws IOException {
        AuthenticationMethod authMethod = getConnectionAuthenticationMethod();
        if (UserGroupInformation.isSecurityEnabled() &&
                (authMethod != AuthenticationMethod.KERBEROS) &&
                (authMethod != AuthenticationMethod.KERBEROS_SSL) &&
                (authMethod != AuthenticationMethod.CERTIFICATE)) {
            return false;
        }
        return true;
    }

    /**
     * Stop services common
     *
     */
    private void stopCommonServices() {
        if (blockManager != null) {
            blockManager.close();
        }
        if (quotaUpdateManager != null) {
            quotaUpdateManager.close();
        }
        RootINodeCache.stop();
    }

    private void stopSecretManager() {
        if (dtSecretManager != null) {
            dtSecretManager.stopThreads();
        }
    }

    /**
     * Stop services required in active state
     *
     * @throws InterruptedException
     */
    void stopActiveServices() {
        LOG.info("Stopping services started for active state");
        stopSecretManager();
        leaseManager.stopMonitor();
        if (nnrmthread != null) {
            ((NameNodeResourceMonitor) nnrmthread.getRunnable()).stopMonitor();
            nnrmthread.interrupt();
        }

        if(retryCacheCleanerThread !=null){
            ((RetryCacheCleaner)retryCacheCleanerThread.getRunnable()).stopMonitor();
            retryCacheCleanerThread.interrupt();
        }

        if (erasureCodingManager != null) {
            erasureCodingManager.close();
        }

        if (cacheManager != null) {
            cacheManager.stopMonitorThread();
            //HOPS as we are distributed we may stop one NN without stopping all of them. In this case we should not clear
            //the information used by the other NN.
            //TODO: check if there is some case where we really need to do the clear.
//      if (blockManager != null) {
//        blockManager.getDatanodeManager().clearPendingCachingCommands();
//        blockManager.getDatanodeManager().setShouldSendCachingCommands(false);
//        // Don't want to keep replication queues when not in Active.
//        blockManager.clearQueues();
//      }
//      initializedReplQueues = false;
        }
    }

    /**
     * Close down this file system manager.
     * Causes heartbeat and lease daemons to stop; waits briefly for
     * them to finish, but a short timeout returns control back to caller.
     */
    void close() {
        fsRunning = false;
        try {
            stopCommonServices();
            if (smmthread != null) {
                smmthread.interrupt();
            }
            fsOperationsExecutor.shutdownNow();
        } finally {
            // using finally to ensure we also wait for lease daemon
            try {
                stopActiveServices();
                if (dir != null) {
                    dir.close();
                }
            } catch (IOException ie) {
                LOG.error("Error closing FSDirectory", ie);
                IOUtils.cleanup(LOG, dir);
            }
        }
    }

    /**
     * shutdown FSNamesystem
     */
    void shutdown() {
        if (mbeanName != null) {
            MBeans.unregister(mbeanName);
            mbeanName = null;
        }
        if (mxbeanName != null) {
            MBeans.unregister(mxbeanName);
            mxbeanName = null;
        }
        if (blockManager != null) {
            blockManager.shutdown();
        }
    }

    /**
     * Returns authentication method used to establish the connection
     *
     * @return AuthenticationMethod used to establish connection
     * @throws IOException
     */
    private AuthenticationMethod getConnectionAuthenticationMethod()
            throws IOException {
        UserGroupInformation ugi = getRemoteUser();
        AuthenticationMethod authMethod = ugi.getAuthenticationMethod();
        if (authMethod == AuthenticationMethod.PROXY) {
            authMethod = ugi.getRealUser().getAuthenticationMethod();
        }
        return authMethod;
    }

    /**
     * @param renewer
     * @return Token<DelegationTokenIdentifier>
     * @throws IOException
     */
    Token<DelegationTokenIdentifier> getDelegationToken(final Text renewer)
            throws IOException {
        //FIXME This does not seem to be persisted
        HopsTransactionalRequestHandler getDelegationTokenHandler =
                new HopsTransactionalRequestHandler(
                        HDFSOperationType.GET_DELEGATION_TOKEN) {
                    @Override
                    public void acquireLock(TransactionLocks locks) throws IOException {

                    }

                    @Override
                    public Object performTask() throws IOException {
                        Token<DelegationTokenIdentifier> token;
                        checkNameNodeSafeMode("Cannot issue delegation token");
                        if (!isAllowedDelegationTokenOp()) {
                            throw new IOException(
                                    "Delegation Token can be issued only with kerberos or web authentication");
                        }
                        if (dtSecretManager == null || !dtSecretManager.isRunning()) {
                            LOG.warn("trying to get DT with no secret manager running");
                            return null;
                        }

                        UserGroupInformation ugi = getRemoteUser();
                        String user = ugi.getUserName();
                        Text owner = new Text(user);
                        Text realUser = null;
                        if (ugi.getRealUser() != null) {
                            realUser = new Text(ugi.getRealUser().getUserName());
                        }
                        DelegationTokenIdentifier dtId =
                                new DelegationTokenIdentifier(owner, renewer, realUser);
                        token = new Token<>(dtId, dtSecretManager);
                        long expiryTime = dtSecretManager.getTokenExpiryTime(dtId);
                        return token;
                    }
                };
        return (Token<DelegationTokenIdentifier>) getDelegationTokenHandler
                .handle(this);
    }

    /**
     * @return Whether the namenode is transitioning to active state and is in the
     *         middle of the {@link #startActiveServices()}
     */
    public boolean inTransitionToActive() {
        return startingActiveService;
    }

    INode getInode(final long id) throws IOException {
        return EntityManager.find(INode.Finder.ByINodeIdFTIS, id);
    }

    static INode resolveLastINode(INodesInPath iip) throws FileNotFoundException {
        INode inode = iip.getLastINode();
        if (inode == null) {
            throw new FileNotFoundException("cannot find " + iip.getPath());
        }
        return inode;
    }

    INodesInPath getExistingPathINodes(byte[][] components)
            throws UnresolvedLinkException, StorageException, TransactionContextException {
        return INodesInPath.resolve(getRootDir(), components, false);
    }

    /**
     * Get {@link INode} associated with the file / directory.
     */
    public INodesInPath getINodesInPath4Write(String src)
            throws UnresolvedLinkException, StorageException, TransactionContextException {
        return getINodesInPath4Write(src, true);
    }

    /**
     * Get {@link INode} associated with the file / directory.
     */
    INodesInPath getINodesInPath4Write(String src, boolean resolveLink)
            throws UnresolvedLinkException, StorageException, TransactionContextException {
        final byte[][] components = INode.getPathComponents(src);
        INodesInPath inodesInPath = INodesInPath.resolve(getRootDir(), components,
                resolveLink);
        return inodesInPath;
    }

    /**
     * We kept the name from apache hadoop for meging simplification but the only purpose of this
     * function is to remove the encryptionZones as the InodeMap is the DB.
     */
    public final void removeFromInodeMap(List<? extends INode> inodes) throws IOException {
        if (inodes != null) {
            for (INode inode : inodes) {
                if (inode != null) {
                    inode.remove();
                    if (inode instanceof INodeWithAdditionalFields) {
                        ezManager.removeEncryptionZone(inode.getId());
                    }
                }
            }
        }
    }

    INode getInodeTX(final long id) throws IOException {
        HopsTransactionalRequestHandler getInodeHandler =
                new HopsTransactionalRequestHandler(
                        HDFSOperationType.GET_INODE) {
                    INodeIdentifier inodeIdentifier;

                    @Override
                    public void setUp() throws StorageException {
                        inodeIdentifier = new INodeIdentifier(id);
                    }

                    @Override
                    public void acquireLock(TransactionLocks locks) throws IOException {
                        LockFactory lf = LockFactory.getInstance();
                        locks.add(lf.
                                getIndividualINodeLock(TransactionLockTypes.INodeLockType.READ_COMMITTED, inodeIdentifier, true));
                    }

                    @Override
                    public Object performTask() throws IOException {
                        return getInode(id);

                    }
                };
        return (INode) getInodeHandler.handle();
    }

    /**
     * Get {@link INode} associated with the file / directory.
     * @throws SnapshotAccessControlException if path is in RO snapshot
     */
    public INode getINode4Write(String src) throws UnresolvedLinkException, StorageException, TransactionContextException {
        return getINodesInPath4Write(src, true).getLastINode();
    }

    /** @return the {@link INodesInPath} containing all inodes in the path. */
    public INodesInPath getINodesInPath(String path, boolean resolveLink) throws UnresolvedLinkException, StorageException,
            TransactionContextException {
        final byte[][] components = INode.getPathComponents(path);
        return INodesInPath.resolve(getRootDir(), components, resolveLink);
    }
    /** @return the last inode in the path. */
    INode getINode(String path, boolean resolveLink)
            throws UnresolvedLinkException, StorageException, TransactionContextException {
        return getINodesInPath(path, resolveLink).getLastINode();
    }

    /**
     *  Get {@link INode} associated with the file / directory.
     */
    public INode getINode(String src) throws UnresolvedLinkException, StorageException, TransactionContextException {
        return getINode(src, true);
    }

    void setAsyncLockRemoval(final String path) throws IOException {
        LightWeightRequestHandler handler = new LightWeightRequestHandler(
                HDFSOperationType.SET_ASYNC_SUBTREE_RECOVERY_FLAG) {
            @Override
            public Object performTask() throws IOException {
                OngoingSubTreeOpsDataAccess<SubTreeOperation> dataAccess =
                        (OngoingSubTreeOpsDataAccess) HdfsStorageFactory.getDataAccess(OngoingSubTreeOpsDataAccess.class);
                SubTreeOperation op = dataAccess.findByPath(getSubTreeLockPathPrefix(path));
                if (op != null && op.getAsyncLockRecoveryTime() == 0) { // set the flag if not already set
                    op.setAsyncLockRecoveryTime(System.currentTimeMillis());
                    List<SubTreeOperation> modified = new ArrayList<SubTreeOperation>();
                    modified.add(op);
                    dataAccess.prepare(Collections.EMPTY_LIST, Collections.EMPTY_LIST, modified);
                }
                return null;
            }
        };
        handler.handle();
    }

    @VisibleForTesting
    void unlockSubtree(final String path, final long ignoreStoInodeId) throws IOException {
        try{
            unlockSubtreeInternal(path, ignoreStoInodeId);
        }catch(Exception e ){
            //Unable to remove the lock. setting the async removal flag
            setAsyncLockRemoval(path);
            throw e;
        }
    }

    /**
     * @see com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientProtocol#getEncodingStatus
     */
    public EncodingStatus getEncodingStatus(final String filePathArg)
            throws IOException {
        final FSPermissionChecker pc = getPermissionChecker();
        byte[][] pathComponents = FSDirectory.getPathComponentsForReservedPath(filePathArg);
        final String filePath = dir.resolvePath(pc, filePathArg, pathComponents);
        HopsTransactionalRequestHandler findReq =
                new HopsTransactionalRequestHandler(
                        HDFSOperationType.FIND_ENCODING_STATUS) {
                    @Override
                    public void acquireLock(TransactionLocks locks) throws IOException {
                        LockFactory lf = LockFactory.getInstance();
                        INodeLock il = lf.getINodeLock(INodeLockType.READ_COMMITTED, INodeResolveType.PATH, filePath)
                                .setNameNodeID(nameNode.getId())
                                .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes());
                        locks.add(il).add(lf.getEncodingStatusLock(LockType.READ_COMMITTED, filePath));
                    }

                    @Override
                    public Object performTask() throws IOException {

                        INodesInPath iip = dir.getINodesInPath(filePath, true);
                        try {
                            if (isPermissionEnabled) {
                                dir.checkPathAccess(pc, iip, FsAction.READ);
                            }
                        } catch (AccessControlException e){
                            logAuditEvent(false, "getEncodingStatus", filePath);
                            throw e;
                        }
                        INode targetNode = iip.getLastINode();
                        if (targetNode == null) {
                            throw new FileNotFoundException();
                        }
                        return EntityManager
                                .find(EncodingStatus.Finder.ByInodeId, targetNode.getId());
                    }
                };
        Object result = findReq.handle();
        if (result == null) {
            return new EncodingStatus(EncodingStatus.Status.NOT_ENCODED);
        }
        return (EncodingStatus) result;
    }

    public void delayAfterBbuildingTree(String message) {
        //Only for testing
        if (isTestingSTO) {
            Times time = delays.get();
            if (time == null){
                time = saveTimes();
            }
            try {
                LOG.debug("Testing STO. "+message+" Sleeping for " + time.delayAfterBuildingTree);
                Thread.sleep(time.delayAfterBuildingTree);
                LOG.debug("Testing STO. "+message+" Waking up from sleep of " + time.delayAfterBuildingTree);
            } catch (InterruptedException e) {
                LOG.warn(e);
            }
        }
    }

    @Override
    public boolean isRunning() {
        return fsRunning;
    }

    @Override
    public void checkSuperuserPrivilege() throws AccessControlException {
        if (isPermissionEnabled) {
            FSPermissionChecker pc = getPermissionChecker();
            pc.checkSuperuserPrivilege();
        }
    }

    @Override
    public String getBlockPoolId() {
        return blockPoolId;
    }

    @Override
    public boolean isLeader() {
        return false;
    }

    @Override
    public long getNamenodeId() {
        return nameNode.getLeCurrentId();
    }

    @Override
    public ServerlessNameNode getNameNode() {
        return nameNode;
    }

    @Override
    public void adjustSafeModeBlocks(Set<Long> safeBlocks) throws IOException {
//        // safeMode is volatile, and may be set to null at any time
//        SafeModeInfo safeMode = this.safeMode();
//        if (safeMode == null) {
//            return;
//        }
//        safeMode.adjustSafeBlocks(safeBlocks);
        throw new NotImplementedException();
    }

    private SafeModeInfo safeMode() throws IOException{
        if(!forceReadTheSafeModeFromDB.get()){
            return null;
        }

        List<Object> vals = HdfsVariables.getSafeModeFromDB();
        if(vals==null || vals.isEmpty()){
            return null;
        }
        return new FSNameSystem.SafeModeInfo((double) vals.get(0),
                (int) vals.get(1), (int) vals.get(2),
                (int) vals.get(3), (double) vals.get(4),
                (int) vals.get(5) == 0 ? true : false);
    }

    /**
     * Client invoked methods are invoked over RPC and will be in
     * RPC call context even if the client exits.
     */
    boolean isExternalInvocation() {
        return ProtobufRpcEngine.Server.isRpcInvocation() ||
                NamenodeWebHdfsMethods.isWebHdfsInvocation();
    }

    /**
     * Set the status of an erasure-coded file.
     *
     * @param sourceFile
     *    the file path
     * @param status
     *    the file status
     * @throws IOException
     */
    public void updateEncodingStatus(String sourceFile,
                                     EncodingStatus.Status status) throws IOException {
        updateEncodingStatus(sourceFile, status, null, null);
    }

    /**
     * Set the parity status of an erasure-coded file.
     *
     * @param sourceFile
     *    the file path
     * @param parityStatus
     *    the parity file status
     * @throws IOException
     */
    public void updateEncodingStatus(String sourceFile,
                                     EncodingStatus.ParityStatus parityStatus) throws IOException {
        updateEncodingStatus(sourceFile, null, parityStatus, null);
    }

    /**
     * Set the status of an erasure-coded file.
     *
     * @param sourceFile
     *    the file path
     * @param status
     *    the file status
     * @param parityFile
     *    the parity file name
     * @throws IOException
     */
    public void updateEncodingStatus(String sourceFile,
                                     EncodingStatus.Status status, String parityFile) throws IOException {
        updateEncodingStatus(sourceFile, status, null, parityFile);
    }

    /**
     * Set the status of an erasure-coded file and its parity file.
     *
     * @param sourceFile
     *    the file path
     * @param status
     *    the file status
     * @param parityStatus
     *    the parity status
     * @param parityFile
     *    the parity file name
     * @throws IOException
     */
    public void updateEncodingStatus(final String sourceFileArg,
                                     final EncodingStatus.Status status,
                                     final EncodingStatus.ParityStatus parityStatus, final String parityFile)
            throws IOException {
        byte[][] pathComponents = FSDirectory.getPathComponentsForReservedPath(sourceFileArg);
        final String sourceFile = dir.resolvePath(getPermissionChecker(), sourceFileArg, pathComponents);
        new HopsTransactionalRequestHandler(
                HDFSOperationType.UPDATE_ENCODING_STATUS) {

            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = LockFactory.getInstance();
                INodeLock il = lf.getINodeLock(INodeLockType.WRITE, INodeResolveType.PATH, sourceFile)
                        .setNameNodeID(nameNode.getId())
                        .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes());
                locks.add(il).add(lf.getEncodingStatusLock(LockType.WRITE, sourceFile));
            }

            @Override
            public Object performTask() throws IOException {
                INode targetNode = getINode(sourceFile);
                EncodingStatus encodingStatus = EntityManager
                        .find(EncodingStatus.Finder.ByInodeId, targetNode.getId());
                if (status != null) {
                    encodingStatus.setStatus(status);
                    encodingStatus.setStatusModificationTime(System.currentTimeMillis());
                }
                if (parityFile != null) {
                    encodingStatus.setParityFileName(parityFile);
                    // Should be updated together with the status so the modification time is already set
                }
                if (parityStatus != null) {
                    encodingStatus.setParityStatus(parityStatus);
                    encodingStatus.setStatusModificationTime(System.currentTimeMillis());
                }
                EntityManager.update(encodingStatus);
                return null;
            }
        }.handle();
    }

    /**
     * Move a file that is being written to be immutable.
     *
     * @param src
     *     The filename
     * @param lease
     *     The lease for the client creating the file
     * @param recoveryLeaseHolder
     *     reassign lease to this holder if the last block
     *     needs recovery; keep current holder if null.
     * @return true  if file has been successfully finalized and closed or
     * false if block recovery has been initiated. Since the lease owner
     * has been changed and logged, caller should call logSync().
     * @throws AlreadyBeingCreatedException
     *     if file is waiting to achieve minimal
     *     replication;<br>
     *     RecoveryInProgressException if lease recovery is in progress.<br>
     *     IOException in case of an error.
     */
    boolean internalReleaseLease(Lease lease, String src, INodesInPath iip,
                                 String recoveryLeaseHolder)
            throws IOException {
        LOG.info("Recovering " + lease + ", src=" + src);
        assert !isInSafeMode();

        final INodeFile pendingFile = iip.getLastINode().asFile();
        int nrBlocks = pendingFile.numBlocks();
        BlockInfoContiguous[] blocks = pendingFile.getBlocks();

        int nrCompleteBlocks;
        BlockInfoContiguous curBlock = null;
        for (nrCompleteBlocks = 0; nrCompleteBlocks < nrBlocks; nrCompleteBlocks++) {
            curBlock = blocks[nrCompleteBlocks];
            if (!curBlock.isComplete()) {
                break;
            }
            assert blockManager.checkMinReplication(curBlock) :
                    "A COMPLETE block is not minimally replicated in " + src;
        }

        // If there are no incomplete blocks associated with this file,
        // then reap lease immediately and close the file.
        if (nrCompleteBlocks == nrBlocks) {
            finalizeINodeFileUnderConstruction(src, pendingFile);
            ServerlessNameNode.stateChangeLog.warn("BLOCK*" +
                    " internalReleaseLease: All existing blocks are COMPLETE," +
                    " lease removed, file closed.");
            return true;  // closed!
        }

        // Only the last and the penultimate blocks may be in non COMPLETE state.
        // If the penultimate block is not COMPLETE, then it must be COMMITTED.
        if (nrCompleteBlocks < nrBlocks - 2 || nrCompleteBlocks == nrBlocks - 2 &&
                curBlock != null &&
                curBlock.getBlockUCState() != HdfsServerConstants.BlockUCState.COMMITTED) {
            final String message = "DIR* NameSystem.internalReleaseLease: " +
                    "attempt to release a create lock on " + src +
                    " but file is already closed.";
            ServerlessNameNode.stateChangeLog.warn(message);
            throw new IOException(message);
        }

        // The last block is not COMPLETE, and
        // that the penultimate block if exists is either COMPLETE or COMMITTED
        final BlockInfoContiguous lastBlock = pendingFile.getLastBlock();
        HdfsServerConstants.BlockUCState lastBlockState = lastBlock.getBlockUCState();
        BlockInfoContiguous penultimateBlock = pendingFile.getPenultimateBlock();
        // If penultimate block doesn't exist then its minReplication is met
        boolean penultimateBlockMinReplication = penultimateBlock == null ? true :
                blockManager.checkMinReplication(penultimateBlock);

        switch (lastBlockState) {
            case COMPLETE:
                assert false : "Already checked that the last block is incomplete";
                break;
            case COMMITTED:
                // Close file if committed blocks are minimally replicated
                if (penultimateBlockMinReplication &&
                        blockManager.checkMinReplication(lastBlock)) {
                    finalizeINodeFileUnderConstruction(src, pendingFile);
                    ServerlessNameNode.stateChangeLog.warn("BLOCK*" +
                            " internalReleaseLease: Committed blocks are minimally replicated," +
                            " lease removed, file closed.");
                    return true;  // closed!
                }
                // Cannot close file right now, since some blocks
                // are not yet minimally replicated.
                // This may potentially cause infinite loop in lease recovery
                // if there are no valid replicas on data-nodes.
                String message = "DIR* NameSystem.internalReleaseLease: " +
                        "Failed to release lease for file " + src +
                        ". Committed blocks are waiting to be minimally replicated." +
                        " Try again later.";
                ServerlessNameNode.stateChangeLog.warn(message);
                throw new AlreadyBeingCreatedException(message);
            case UNDER_CONSTRUCTION:
            case UNDER_RECOVERY:
                final BlockInfoContiguousUnderConstruction uc =
                        (BlockInfoContiguousUnderConstruction) lastBlock;
                // determine if last block was intended to be truncated
                Block recoveryBlock = uc.getTruncateBlock();
                boolean truncateRecovery = recoveryBlock != null;
                boolean copyOnTruncate = truncateRecovery &&
                        recoveryBlock.getBlockId() != uc.getBlockId();
                assert !copyOnTruncate ||
                        recoveryBlock.getBlockId() < uc.getBlockId() &&
                                recoveryBlock.getGenerationStamp() < uc.getGenerationStamp() &&
                                recoveryBlock.getNumBytes() > uc.getNumBytes() :
                        "wrong recoveryBlock";
                // setup the last block locations from the blockManager if not known
                if (uc.getNumExpectedLocations() == 0) {
                    uc.setExpectedLocations(blockManager.getStorages(lastBlock));
                }

                if (uc.getNumExpectedLocations() == 0 && uc.getNumBytes() == 0) {
                    // There is no datanode reported to this block.
                    // may be client have crashed before writing data to pipeline.
                    // This blocks doesn't need any recovery.
                    // We can remove this block and close the file.
                    pendingFile.removeLastBlock(lastBlock);
                    finalizeINodeFileUnderConstruction(src, pendingFile);
                    ServerlessNameNode.stateChangeLog.warn("BLOCK* internalReleaseLease: "
                            + "Removed empty last block and closed file.");
                    return true;
                }
                // start recovery of the last block for this file
                long blockRecoveryId = pendingFile.nextGenerationStamp();
                lease = reassignLease(lease, src, recoveryLeaseHolder, pendingFile);
                if(copyOnTruncate) {
                    uc.setGenerationStamp(blockRecoveryId);
                } else if(truncateRecovery) {
                    recoveryBlock.setGenerationStampNoPersistance(blockRecoveryId);
                }
                uc.initializeBlockRecovery(blockRecoveryId, getBlockManager().getDatanodeManager());
                leaseManager.renewLease(lease);
                // Cannot close file right now, since the last block requires recovery.
                // This may potentially cause infinite loop in lease recovery
                // if there are no valid replicas on data-nodes.
                ServerlessNameNode.stateChangeLog.warn("DIR* NameSystem.internalReleaseLease: " +
                        "File " + src + " has not been closed." +
                        " Lease recovery is in progress. " +
                        "RecoveryId = " + blockRecoveryId + " for block " + lastBlock);
                break;
        }
        return false;
    }

    private Lease reassignLease(Lease lease, String src, String newHolder,
                                INodeFile pendingFile)
            throws StorageException, TransactionContextException {
        if (newHolder == null) {
            return lease;
        }
        return reassignLeaseInternal(lease, src, newHolder, pendingFile);
    }

    private Lease reassignLeaseInternal(Lease lease, String src, String newHolder,
                                        INodeFile pendingFile)
            throws StorageException, TransactionContextException {
        pendingFile.getFileUnderConstructionFeature().setClientName(newHolder);
        return leaseManager.reassignLease(lease, src, newHolder);
    }

    private void finalizeINodeFileUnderConstruction(String src,
                                                    INodeFile pendingFile)
            throws IOException {
        boolean skipReplicationCheck = false;
        if (pendingFile.getStoragePolicyID() == HdfsConstants.DB_STORAGE_POLICY_ID){
            skipReplicationCheck = true;
        }

        finalizeINodeFileUnderConstructionInternal(src, pendingFile, skipReplicationCheck);
    }

    private void finalizeINodeFileUnderConstructionInternal(String src,
                                                            INodeFile pendingFile, boolean skipReplicationChecks)
            throws IOException {
        FileUnderConstructionFeature uc = pendingFile.getFileUnderConstructionFeature();
        Preconditions.checkArgument(uc != null);
        leaseManager.removeLease(uc.getClientName(), src);

        // close file and persist block allocations for this file
        pendingFile.toCompleteFile(now());
        closeFile(src, pendingFile);
        pendingFile.logProvenanceEvent(getNamenodeId(), FileProvenanceEntry.Operation.append());

        if (!skipReplicationChecks) {
            blockManager.checkReplication(pendingFile);
        }
    }

    /**
     * Close file.
     * @param path
     * @param file
     */
    private void closeFile(String path, INodeFile file) throws IOException {
        // file is closed
        if (ServerlessNameNode.stateChangeLog.isDebugEnabled()) {
            ServerlessNameNode.stateChangeLog.debug("closeFile: "
                    +path+" with "+ file.getBlocks().length
                    +" blocks is persisted to the file system");
        }
        file.logMetadataEvent(INodeMetadataLogEntry.Operation.Add);
    }

    boolean isAuditEnabled() {
        return !isDefaultAuditLogger || auditLog.isInfoEnabled();
    }

    private void logAuditEvent(boolean succeeded, String cmd, String src)
            throws IOException {
        logAuditEvent(succeeded, cmd, src, null, null);
    }

    private void logAuditEvent(boolean succeeded, String cmd, String src,
                               String dst, HdfsFileStatus stat) throws IOException {
        if (isAuditEnabled() && isExternalInvocation()) {
            logAuditEvent(succeeded, getRemoteUser(), getRemoteIp(), cmd, src, dst,
                    stat);
        }
    }

    private void logAuditEvent(boolean succeeded, UserGroupInformation ugi,
                               InetAddress addr, String cmd, String src, String dst,
                               HdfsFileStatus stat) throws IOException {
        FileStatus status = null;
        if (stat != null) {
            Path symlink = stat.isSymlink() ? new Path(stat.getSymlink()) : null;
            Path path = dst != null ? new Path(dst) : new Path(src);
            status =
                    new FileStatus(stat.getLen(), stat.isDir(), stat.getReplication(),
                            stat.getBlockSize(), stat.getModificationTime(),
                            stat.getAccessTime(), stat.getPermission(), stat.getOwner(),
                            stat.getGroup(), symlink, path);
        }
        for (AuditLogger logger : auditLoggers) {
            if (logger instanceof HdfsAuditLogger) {
                HdfsAuditLogger hdfsLogger = (HdfsAuditLogger) logger;
                hdfsLogger.logAuditEvent(succeeded, ugi.toString(), addr, cmd, src, dst,
                        status, ugi, dtSecretManager);
            } else {
                logger.logAuditEvent(succeeded, ugi.toString(), addr,
                        cmd, src, dst, status);
            }
        }
    }

    @Override
    public boolean isInSafeMode() throws IOException {
        // safeMode is volatile, and may be set to null at any time
        SafeModeInfo safeMode = safeMode();
        if (safeMode == null) {
            if(inSafeMode.get()){
                new SafeModeInfo().leave();
            }
            forceReadTheSafeModeFromDB.set(false);
            return false;
        }
        if(safeMode.isOn() && !isLeader()){
            safeMode.tryToHelpToGetOut();
        }
        inSafeMode.set(safeMode.isOn());
        return safeMode.isOn();
    }

    /**
     * Log the updateMasterKey operation to edit logs
     *
     * @param key
     *     new delegation key.
     */
    public void logUpdateMasterKey(DelegationKey key) throws IOException {

        assert !isInSafeMode() :
                "this should never be called while in safe mode, since we stop " +
                        "the DT manager before entering safe mode!";
        // No need to hold FSN lock since we don't access any internal
        // structures, and this is stopped before the FSN shuts itself
        // down, etc.
    }

    /**
     * We already know that the safemode is on. We will throw a RetriableException
     * if the safemode is not manual or caused by low resource.
     */
    private boolean shouldRetrySafeMode(SafeModeInfo safeMode) {
        if (safeMode == null) {
            return false;
        } else {
            return !safeMode.isManual() && !safeMode.areResourcesLow();
        }
    }

    /**
     * @throws RetriableException
     *           If 1) The ServerlessNameNode is in SafeMode, 2) HA is enabled, and 3)
     *           ServerlessNameNode is in active state
     * @throws SafeModeException
     *           Otherwise if ServerlessNameNode is in SafeMode.
     */
    private void checkNameNodeSafeMode(String errorMsg)
            throws RetriableException, SafeModeException, IOException {
        if (isInSafeMode()) {
            SafeModeInfo safeMode = safeMode();
            SafeModeException se = new SafeModeException(errorMsg, safeMode);
            if (shouldRetrySafeMode(safeMode)) {
                throw new RetriableException(se);
            } else {
                throw se;
            }
        }
    }

    public void performPendingSafeModeOperation() throws IOException {
        // safeMode is volatile, and may be set to null at any time
        SafeModeInfo safeMode = this.safeMode();
        if (safeMode != null) {
            safeMode.performSafeModePendingOperation();
        }
    }

    /**
     * Check if replication queues are to be populated
     * @return true when node is HAState.Active and not in the very first safemode
     */
    @Override
    public boolean isPopulatingReplQueues() throws IOException {
        if (!shouldPopulateReplicationQueues()) {
            return false;
        }
        return initializedReplQueues;
    }

    private boolean shouldPopulateReplicationQueues() {
        return shouldPopulateReplicationQueue;
    }

    /**
     * Delete all safe blocks
     * @throws IOException
     */
    private void clearSafeBlocks() throws IOException {
        LOG.warn("cealring the safe blocks tabl, this may take some time.");
        new LightWeightRequestHandler(HDFSOperationType.CLEAR_SAFE_BLOCKS) {
            @Override
            public Object performTask() throws IOException {
                SafeBlocksDataAccess da = (SafeBlocksDataAccess) HdfsStorageFactory
                        .getDataAccess(SafeBlocksDataAccess.class);
                da.removeAll();
                return null;
            }
        }.handle();
    }

    public void processIncrementalBlockReport(DatanodeRegistration nodeReg, StorageReceivedDeletedBlocks r)
            throws IOException {
        blockManager.processIncrementalBlockReport(nodeReg, r);
    }

    PermissionStatus createFsOwnerPermissions(FsPermission permission) {
        return new PermissionStatus(fsOwner.getShortUserName(), superGroup,
                permission);
    }

    @Override
    public void checkSuperuserPrivilege() throws AccessControlException {
        if (isPermissionEnabled) {
            FSPermissionChecker pc = getPermissionChecker();
            pc.checkSuperuserPrivilege();
        }
    }

    /**
     * Lock a subtree of the filesystem tree.
     * Locking a subtree prevents it from any concurrent write operations.
     *
     * @param path
     *    the root of the subtree to be locked
     * @return
     *  the inode representing the root of the subtree
     * @throws IOException
     */
    public INodeIdentifier lockSubtree(final String path, SubTreeOperation.Type stoType) throws
            IOException {
        return lockSubtreeAndCheckOwnerAndParentPermission(path, false, null, stoType);
    }

    /**
     * Lock a subtree of the filesystem tree and ensure that the client has
     * sufficient permissions. Locking a subtree prevents it from any concurrent
     * write operations.
     *
     * @param path
     *    the root of the subtree to be locked
     * @param doCheckOwner
     *    whether or not to check the owner
     * @param ancestorAccess
     *    the requested ancestor access
     * @param parentAccess
     *    the requested parent access
     * @param access
     *    the requested access
     * @param subAccess
     *    the requested sub access
     * @return
     *  the inode representing the root of the subtree
     * @throws IOException
     */
    @VisibleForTesting
    INodeIdentifier lockSubtreeAndCheckOwnerAndParentPermission(final String path,
                                                                final boolean doCheckOwner,
                                                                final FsAction parentAccess,
                                                                final SubTreeOperation.Type stoType) throws IOException {

        if(path.compareTo("/")==0){
            return null;
        }

        return (INodeIdentifier) new HopsTransactionalRequestHandler(
                HDFSOperationType.SET_SUBTREE_LOCK) {

            @Override
            public void setUp() throws IOException {
                super.setUp();
                if(LOG.isDebugEnabled()) {
                    LOG.debug("About to lock \"" + path + "\"");
                }
            }

            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = LockFactory.getInstance();
                INodeLock il = lf.getINodeLock( TransactionLockTypes.INodeLockType.WRITE, TransactionLockTypes.INodeResolveType.PATH, path);
                il.setNameNodeID(nameNode.getId()).setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes())
                        .skipReadingQuotaAttr(!dir.isQuotaEnabled())
                        .enableHierarchicalLocking(conf.getBoolean(DFSConfigKeys.DFS_SUBTREE_HIERARCHICAL_LOCKING_KEY,
                                DFSConfigKeys.DFS_SUBTREE_HIERARCHICAL_LOCKING_KEY_DEFAULT));
                locks.add(il).
                        //READ_COMMITTED because it is index scan and hierarchical locking for inodes is sufficient
                                add(lf.getSubTreeOpsLock(TransactionLockTypes.LockType.READ_COMMITTED,
                                getSubTreeLockPathPrefix(path), true)); // it is
                locks.add(lf.getAcesLock());
            }

            @Override
            public Object performTask() throws IOException {
                FSPermissionChecker pc = getPermissionChecker();
                INodesInPath iip = dir.getINodesInPath(path, false);
                if (isPermissionEnabled && !pc.isSuperUser()) {
                    dir.checkPermission(pc, iip, doCheckOwner, null, parentAccess, null, null,
                            true);
                }

                INode inode = iip.getLastINode();

                if (inode != null && inode.isDirectory() &&
                        !inode.isRoot()) { // never lock the fs root
                    checkSubTreeLocks(getSubTreeLockPathPrefix(path));
                    inode.setSubtreeLocked(true);
                    inode.setSubtreeLockOwner(getNamenodeId());
                    EntityManager.update(inode);
                    if(LOG.isDebugEnabled()) {
                        LOG.debug("Lock the INode with sub tree lock flag. Path: \"" + path + "\" "
                                + " id: " + inode.getId()
                                + " pid: " + inode.getParentId() + " name: " + inode.getLocalName());
                    }

                    EntityManager.update(new SubTreeOperation(getSubTreeLockPathPrefix(path),
                            inode.getId() ,nameNode.getId(), stoType,
                            System.currentTimeMillis(), pc.getUser()));
                    INodeIdentifier iNodeIdentifier =  new INodeIdentifier(inode.getId(),
                            inode.getParentId(),
                            inode.getLocalName(), inode.getPartitionId());
                    iNodeIdentifier.setDepth(inode.myDepth());
                    iNodeIdentifier.setStoragePolicy(inode.getStoragePolicyID());
                    //Wait before commit. Only for testing
                    delayBeforeSTOFlag(stoType.toString());
                    return  iNodeIdentifier;
                }else{
                    if(LOG.isInfoEnabled()) {
                        LOG.info("No component was locked in the path using sub tree flag. "
                                + "Path: \"" + path + "\"");
                    }
                    return null;
                }
            }
        }.handle(this);
    }

    /**
     * check for sub tree locks in the descendant tree
     * @return number of active operations in the descendant tree
     */
    private void checkSubTreeLocks(String path) throws TransactionContextException,
            StorageException, RetriableException{
        List<SubTreeOperation> ops = (List<SubTreeOperation>)
                EntityManager.findList(SubTreeOperation.Finder.ByPathPrefix,
                        path);  // THIS RETURNS ONLY ONE SUBTREE OP IN THE CHILD TREE. INCREASE THE LIMIT IN IMPL LAYER IF NEEDED
        Set<Long> activeNameNodeIds = new HashSet<>();
        for(ActiveNode node:nameNode.getActiveNameNodes().getActiveNodes()){
            activeNameNodeIds.add(node.getId());
        }

        for(SubTreeOperation op : ops){
            if(activeNameNodeIds.contains(op.getNameNodeId())){
                throw new RetriableException("At least one ongoing " +
                        "subtree operation on the descendants of this subtree, e.g., Path: "+op.getPath()
                        +" Operation: "+op.getOpType()+" NameNodeId: "+ op.getNameNodeId());
            }else{ // operation started by a dead namenode.
                //TODO: what if the activeNameNodeIds does not contain all new namenode ids
                //An operation belonging to new namenode might be considered dead
                //handle this my maintaining a list of dead namenodes.
                EntityManager.remove(op);
            }
        }
    }

    /**
     * adds / at the end of the path
     * suppose /aa/bb is locked and we want to lock an other folder /a.
     * when we search for all prefixes "/a" it will return subtree ops in other
     * folders i.e /aa*. By adding / in the end of the path solves the problem
     * @param path
     * @return /path + "/"
     */
    String getSubTreeLockPathPrefix(String path){
        String subTreeLockPrefix = path;
        if(!subTreeLockPrefix.endsWith("/")){
            subTreeLockPrefix+="/";
        }
        return subTreeLockPrefix;
    }

    public void setDelayBeforeSTOFlag(long delay){
        if(isTestingSTO)
            this.delayBeforeSTOFlag = delay;
    }

    public void setDelayAfterBuildingTree(long delay){
        if (isTestingSTO)
            this.delayAfterBuildingTree = delay;
    }

    public Times saveTimes() {
        if (isTestingSTO) {
            delays.remove();
            Times times = new Times(delayBeforeSTOFlag, delayAfterBuildingTree);
            delays.set(times);
            return times;
        } else {
            return null;
        }
    }


    public void delayBeforeSTOFlag(String message) {
        if (isTestingSTO) {
            //Only for testing
            Times time = delays.get();
            if (time == null){
                time = saveTimes();
            }
            try {
                LOG.debug("Testing STO. "+message+" Sleeping for " + time.delayBeforeSTOFlag);
                Thread.sleep(time.delayBeforeSTOFlag);
                LOG.debug("Testing STO. "+message+" Waking up from sleep of " + time.delayBeforeSTOFlag);
            } catch (InterruptedException e) {
                LOG.warn(e);
            }
        }
    }

    /**
     * Check to see if we have exceeded the limit on the number
     * of inodes.
     */
    void checkFsObjectLimit() throws IOException {
        if (maxFsObjects != 0 &&
                maxFsObjects <= dir.totalInodes() + getBlocksTotal()) {
            throw new IOException("Exceeded the configured number of objects " +
                    maxFsObjects + " in the filesystem.");
        }
    }

    FSPermissionChecker getPermissionChecker()
            throws AccessControlException {
        return dir.getPermissionChecker();
    }

    /**
     * Start services required in active state
     *
     * @throws IOException
     */
    void startActiveServices() throws IOException {
        startingActiveService = true;
        LOG.info("Starting services required for active state");
        LOG.info("Catching up to latest edits from old active before " + "taking over writer role in edits logs");
        try {
            blockManager.getDatanodeManager().markAllDatanodesStale();

            // Only need to re-process the queue, If not in SafeMode.
            if (!isInSafeMode()) {
                LOG.info("Reprocessing replication and invalidation queues");
                initializeReplQueues();
            } else {
                if (isLeader()) {
                    // the node is starting and directly leader, this means that no NN was alive before
                    HdfsVariables.resetMisReplicatedIndex();
                }
            }

            leaseManager.startMonitor();
            startSecretManagerIfNecessary();

            //ResourceMonitor required only at ActiveNN. See HDFS-2914
            this.nnrmthread = new Daemon(new NameNodeResourceMonitor());
            nnrmthread.start();

            if(isRetryCacheEnabled) {
                this.retryCacheCleanerThread = new Daemon(new RetryCacheCleaner());
                this.retryCacheCleanerThread.setName("Retry Cache Cleaner");
                retryCacheCleanerThread.start();
            }

            if (erasureCodingEnabled) {
                erasureCodingManager.activate();
            }

            if (cacheManager != null) {
                cacheManager.startMonitorThread();
            }
//      blockManager.getDatanodeManager().setShouldSendCachingCommands(true);
        } finally {
            startingActiveService = false;
            checkSafeMode();
        }
    }

    /** Get the file info for a specific file.
     * @param fsd FSDirectory
     * @param src The string representation of the path to the file
     * @param includeStoragePolicy whether to include storage policy
     * @return object containing information regarding the file
     *         or null if file not found
     */
    static HdfsFileStatus getFileInfo(
            FSDirectory fsd, INodesInPath src, boolean isRawPath,
            boolean includeStoragePolicy)
            throws IOException {

        final INode i = src.getLastINode();
        byte policyId = includeStoragePolicy && i != null && !i.isSymlink() ? i.getStoragePolicyID()
                : HdfsConstantsClient.BLOCK_STORAGE_POLICY_ID_UNSPECIFIED;
        return i == null ? null : createFileStatus(
                fsd, HdfsFileStatus.EMPTY_NAME, i, policyId, isRawPath,
                src);
    }

    /**
     * Create FileStatus by file INode
     */
    static HdfsFileStatus createFileStatus(
            FSDirectory fsd, byte[] path, INode node, byte storagePolicy, boolean isRawPath, INodesInPath iip) throws
            IOException {
        long size = 0;     // length is zero for directories
        short replication = 0;
        long blocksize = 0;
        boolean isStoredInDB = false;
        final boolean isEncrypted;

        final FileEncryptionInfo feInfo = isRawPath ? null :
                fsd.getFileEncryptionInfo(node, iip);

        if (node.isFile()) {
            final INodeFile fileNode = node.asFile();
            size = fileNode.getSize();
            replication = fileNode.getBlockReplication();
            blocksize = fileNode.getPreferredBlockSize();
            isEncrypted = (feInfo != null) ||
                    (isRawPath && fsd.isInAnEZ(INodesInPath.fromINode(node)));
        } else {
            isEncrypted = fsd.isInAnEZ(INodesInPath.fromINode(node));
        }

        int childrenNum = node.isDirectory() ?
                node.asDirectory().getChildrenNum() : 0;

        return new HdfsFileStatus(
                size,
                node.isDirectory(),
                replication,
                blocksize,
                node.getModificationTime(),
                node.getAccessTime(),
                getPermissionForFileStatus(node, isEncrypted),
                node.getUserName(),
                node.getGroupName(),
                node.isSymlink() ? node.asSymlink().getSymlink() : null,
                path,
                node.getId(),
                childrenNum,
                feInfo,
                storagePolicy);
    }

    /**
     * Returns an inode's FsPermission for use in an outbound FileStatus.  If the
     * inode has an ACL or is for an encrypted file/dir, then this method will
     * return an FsPermissionExtension.
     *
     * @param node INode to check
     * @param isEncrypted boolean true if the file/dir is encrypted
     * @return FsPermission from inode, with ACL bit on if the inode has an ACL
     * and encrypted bit on if it represents an encrypted file/dir.
     */
    private static FsPermission getPermissionForFileStatus(
            INode node, boolean isEncrypted) throws IOException {
        FsPermission perm = node.getFsPermission();
        boolean hasAcl = node.getAclFeature() != null;
        if (hasAcl || isEncrypted) {
            perm = new FsPermissionExtension(perm, hasAcl, isEncrypted);
        }
        return perm;
    }

    /**
     * Get block locations within the specified range.
     *
     * @see ClientProtocol#getBlockLocations(String, long, long)
     */
    public LocatedBlocks getBlockLocations(final String clientMachine, final String srcArg,
                                           final long offset, final long length) throws IOException {

        // First try the operation using shared lock.
        // Upgrade the lock to exclusive lock if LockUpgradeException is encountered.
        // This operation tries to update the inode access time once every hr.
        // The lock upgrade exception is thrown when the inode access time stamp is
        // updated while holding shared lock on the inode. In this case retry the operation
        // using an exclusive lock.
        LocatedBlocks result = null;
        try {
            try {
                result = getBlockLocationsWithLock(clientMachine, srcArg, offset, length, INodeLockType.READ);
            } catch (LockUpgradeException e) {
                LOG.debug("Encountered LockUpgradeException while reading " + srcArg
                        + ". Retrying the operation using exclusive locks");
                result = getBlockLocationsWithLock(clientMachine, srcArg, offset, length, INodeLockType.WRITE);
            }
        } catch (AccessControlException e) {
            logAuditEvent(false, "open", srcArg);
            throw e;
        }
        logAuditEvent(true, "open", srcArg);
        return result;
    }

    LocatedBlocks getBlockLocationsWithLock(final String clientMachine, final String srcArg,
                                            final long offset, final long length, final INodeLockType lockType) throws IOException {
        byte[][] pathComponents = FSDirectory.getPathComponentsForReservedPath(srcArg);
        final String src = dir.resolvePath(getPermissionChecker(), srcArg, pathComponents);
        final boolean isSuperUser =  dir.getPermissionChecker().isSuperUser();
        HopsTransactionalRequestHandler getBlockLocationsHandler = new HopsTransactionalRequestHandler(
                HDFSOperationType.GET_BLOCK_LOCATIONS, src) {
            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = getInstance();
                INodeLock il = lf.getINodeLock(lockType, INodeResolveType.PATH, src)
                        .setNameNodeID(nameNode.getId())
                        .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes())
                        .skipReadingQuotaAttr(!dir.isQuotaEnabled());
                locks.add(il).add(lf.getBlockLock())
                        .add(lf.getBlockRelated(BLK.RE, BLK.ER, BLK.CR, BLK.UC, BLK.CA));
                locks.add(lf.getAcesLock());
                locks.add(lf.getEZLock());
                if(isSuperUser) {
                    locks.add(lf.getXAttrLock());
                }else {
                    locks.add(lf.getXAttrLock(FSDirXAttrOp.XATTR_FILE_ENCRYPTION_INFO));
                }
            }

            @Override
            public Object performTask() throws IOException {
                GetBlockLocationsResult res = null;

                res = getBlockLocationsInt(srcArg, src, offset, length, true, true);

                if (res.updateAccessTime()) {
                    final long now = now();
                    try {
                        final INodesInPath iip = dir.getINodesInPath(src, true);
                        INode inode = iip.getLastINode();
                        boolean updateAccessTime = inode != null &&
                                now > inode.getAccessTime() + getAccessTimePrecision();
                        if (!isInSafeMode() && updateAccessTime) {
                            boolean changed = FSDirAttrOp.setTimes(dir,
                                    inode, -1, now, false);
                        }
                    } catch (Throwable e) {
                        LOG.warn("Failed to update the access time of " + src, e);
                    }
                }

                LocatedBlocks blocks = res.blocks;

                if (blocks != null && !blocks
                        .hasPhantomBlock()) { // no need to sort phantom datanodes
                    blockManager.getDatanodeManager()
                            .sortLocatedBlocks(clientMachine, blocks.getLocatedBlocks());

                    // lastBlock is not part of getLocatedBlocks(), might need to sort it too
                    LocatedBlock lastBlock = blocks.getLastLocatedBlock();
                    if (lastBlock != null) {
                        ArrayList<LocatedBlock> lastBlockList = Lists.newArrayList(lastBlock);
                        blockManager.getDatanodeManager().sortLocatedBlocks(
                                clientMachine, lastBlockList);
                    }
                }
                return blocks;
            }
        };

        return (LocatedBlocks) getBlockLocationsHandler.handle(this);

    }

    /**
     * Get block locations within the specified range.
     *
     * @throws IOException
     * @see ClientProtocol#getBlockLocations(String, long, long)
     */
    public GetBlockLocationsResult getBlockLocations(final String srcArg, final long offset,
                                                     final long length, final boolean needBlockToken, final boolean checkSafeMode)
            throws IOException {
        final FSPermissionChecker pc = getPermissionChecker();
        byte[][] pathComponents = FSDirectory.getPathComponentsForReservedPath(srcArg);
        final String src = dir.resolvePath(pc, srcArg, pathComponents);
        final boolean isSuperUser =  dir.getPermissionChecker().isSuperUser();
        HopsTransactionalRequestHandler getBlockLocationsHandler = new HopsTransactionalRequestHandler(
                HDFSOperationType.GET_BLOCK_LOCATIONS, src) {
            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = getInstance();
                INodeLock il = lf.getINodeLock(INodeLockType.READ, INodeResolveType.PATH, src)
                        .setNameNodeID(nameNode.getId())
                        .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes());
                locks.add(il).add(lf.getBlockLock())
                        .add(lf.getBlockRelated(BLK.RE, BLK.ER, BLK.CR, BLK.UC, BLK.CA));
                locks.add(lf.getEZLock());
                if (isSuperUser) {
                    locks.add(lf.getXAttrLock());
                } else {
                    locks.add(lf.getXAttrLock(FSDirXAttrOp.XATTR_FILE_ENCRYPTION_INFO));
                }
            }

            @Override
            public Object performTask() throws IOException {
                return getBlockLocationsInt(srcArg, src, offset, length, needBlockToken, checkSafeMode);
            }
        };
        return (GetBlockLocationsResult) getBlockLocationsHandler.handle(this);
    }

    /*
     * Get block locations within the specified range, updating the
     * access times if necessary.
     */
    private GetBlockLocationsResult getBlockLocationsInt(String srcArg, String src, long offset,
                                                         long length, boolean needBlockToken)
            throws IOException {
        FSPermissionChecker pc = getPermissionChecker();

        final INodesInPath iip = dir.getINodesInPath(src, true);
        final INodeFile inode = INodeFile.valueOf(iip.getLastINode(), src);

        if (isPermissionEnabled) {
            dir.checkPathAccess(pc, iip, FsAction.READ);
            checkUnreadableBySuperuser(pc, inode);
        }

        final long fileSize = inode.computeFileSizeNotIncludingLastUcBlock();
        boolean isUc = inode.isUnderConstruction();

        final FileEncryptionInfo feInfo =
                FSDirectory.isReservedRawName(srcArg) ?
                        null : dir.getFileEncryptionInfo(inode, iip);

        final LocatedBlocks blocks = blockManager.createLocatedBlocks(
                inode.getBlocks(), fileSize,
                isUc, offset, length, needBlockToken, feInfo);

        // Set caching information for the located blocks.
        for (LocatedBlock lb : blocks.getLocatedBlocks()) {
            cacheManager.setCachedLocations(lb, inode.getId());
        }

        final long now = now();
        boolean updateAccessTime = isAccessTimeSupported() && !isInSafeMode()
                && now > inode.getAccessTime() + getAccessTimePrecision();
        return new GetBlockLocationsResult(updateAccessTime, blocks);
    }

    /**
     * Remove the status of an erasure-coded file.
     *
     * @param encodingStatus
     *    the status of the file
     * @throws IOException
     */
    public void removeEncodingStatus(final EncodingStatus encodingStatus)
            throws IOException {
        // All referring inodes are already deleted. No more lock necessary.
        LightWeightRequestHandler removeHandler =
                new LightWeightRequestHandler(EncodingStatusOperationType.DELETE) {
                    @Override
                    public Object performTask() throws IOException {
                        BlockChecksumDataAccess blockChecksumDataAccess =
                                (BlockChecksumDataAccess) io.hops.metadata.HdfsStorageFactory
                                        .getDataAccess(BlockChecksumDataAccess.class);
                        EncodingStatusDataAccess encodingStatusDataAccess =
                                (EncodingStatusDataAccess) io.hops.metadata.HdfsStorageFactory
                                        .getDataAccess(EncodingStatusDataAccess.class);
                        blockChecksumDataAccess.deleteAll(encodingStatus.getInodeId());
                        blockChecksumDataAccess
                                .deleteAll(encodingStatus.getParityInodeId());
                        encodingStatusDataAccess.delete(encodingStatus);
                        return null;
                    }
                };
        removeHandler.handle();
    }

    /**
     * Remove the status of an erasure-coded file.
     *
     * @param path
     *    the path of the file
     * @param encodingStatus
     *    the status of the file
     * @throws IOException
     */
    public void removeEncodingStatus(final String path,
                                     final EncodingStatus encodingStatus) throws IOException {
        new HopsTransactionalRequestHandler(
                HDFSOperationType.DELETE_ENCODING_STATUS) {

            @Override
            public void acquireLock(TransactionLocks locks) throws IOException {
                LockFactory lf = LockFactory.getInstance();
                INodeLock il = lf.getINodeLock(INodeLockType.WRITE, INodeResolveType.PATH, path)
                        .setNameNodeID(nameNode.getId())
                        .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes());
                locks.add(il)
                        .add(lf.getEncodingStatusLock(LockType.WRITE, path));
            }

            @Override
            public Object performTask() throws IOException {
                EntityManager.remove(encodingStatus);
                return null;
            }
        }.handle();
    }

    // rename was successful. If any part of the renamed subtree had
    // files that were being written to, update with new filename.
    void unprotectedChangeLease(String src, String dst)
            throws StorageException, TransactionContextException {
        leaseManager.changeLease(src, dst);
    }

    public ExecutorService getFSOperationsExecutor() {
        return fsOperationsExecutor;
    }

    /**
     * Remove the indicated file from namespace.
     *
     * @see ClientProtocol#delete(String, boolean) for detailed description and
     * description of exceptions
     */
    public boolean delete(String src, boolean recursive)
            throws IOException {
        //only for testing
        saveTimes();

        if(!nameNode.isLeader() && dir.isQuotaEnabled()){
            throw new NotALeaderException("Quota enabled. Delete operation can only be performed on a " +
                    "leader namenode");
        }

        boolean ret = false;
        try {
            checkNameNodeSafeMode("Cannot delete " + src);
            ret = FSDirDeleteOp.delete(
                    this, src, recursive);
        } catch (AccessControlException e) {
            logAuditEvent(false, "delete", src);
            throw e;
        }
        logAuditEvent(true, "delete", src);
        return ret;
    }

    QuotaUpdateManager getQuotaUpdateManager() {
        return quotaUpdateManager;
    }

    /** @return the FSDirectory. */
    public FSDirectory getFSDirectory() {
        return dir;
    }

    public List<AclEntry> calculateNearestDefaultAclForSubtree(final PathInformation pathInfo) throws IOException {
        for (int i = pathInfo.getPathInodeAcls().length-1; i > -1 ; i--){
            List<AclEntry> aclEntries = pathInfo.getPathInodeAcls()[i];
            if (aclEntries == null){
                continue;
            }

            List<AclEntry> onlyDefaults = new ArrayList<>();
            for (AclEntry aclEntry : aclEntries) {
                if (aclEntry.getScope().equals(AclEntryScope.DEFAULT)){
                    onlyDefaults.add(aclEntry);
                }
            }

            if (!onlyDefaults.isEmpty()){
                return onlyDefaults;
            }
        }
        return new ArrayList<>();
    }

    public byte calculateNearestinheritedStoragePolicy(final PathInformation pathInfo) throws IOException {
        for (int i = pathInfo.getINodesInPath().length() - 1; i > -1; i--) {
            byte storagePolicy = pathInfo.getINodesInPath().getINode(i).getLocalStoragePolicyID();
            if (storagePolicy != HdfsConstantsClient.BLOCK_STORAGE_POLICY_ID_UNSPECIFIED) {
                return storagePolicy;
            }
        }
        return HdfsConstantsClient.BLOCK_STORAGE_POLICY_ID_UNSPECIFIED;
    }

    /**
     * Get the file info for a specific file.
     *
     * @param src
     *     The string representation of the path to the file
     * @param resolveLink
     *     whether to throw UnresolvedLinkException
     *     if src refers to a symlink
     * @return object containing information regarding the file
     * or null if file not found
     * @throws AccessControlException
     *     if access is denied
     * @throws UnresolvedLinkException
     *     if a symlink is encountered.
     */
    public HdfsFileStatus getFileInfo(final String src, final boolean resolveLink)
            throws IOException {
        HdfsFileStatus stat = null;
        try {
            stat = FSDirStatAndListingOp.getFileInfo(dir, src, resolveLink);
        } catch (AccessControlException e) {
            logAuditEvent(false, "getfileinfo", src);
            throw e;
        }
        logAuditEvent(true, "getfileinfo", src);
        return stat;
    }

    private GetBlockLocationsResult getBlockLocationsInt(final String srcArg, final String src, final long offset,
                                                         final long length, final boolean needBlockToken, final boolean checkSafeMode)
            throws IOException {

        if (offset < 0) {
            throw new HadoopIllegalArgumentException(
                    "Negative offset is not supported. File: " + src);
        }
        if (length < 0) {
            throw new HadoopIllegalArgumentException(
                    "Negative length is not supported. File: " + src);
        }

        GetBlockLocationsResult ret;
        final INodeFile inodeFile = INodeFile.valueOf(dir.getINode(src), src);
        if (inodeFile.isFileStoredInDB()) {
            LOG.debug("SMALL_FILE The file is stored in the database. Returning Phantom Blocks");
            ret = getPhantomBlockLocationsUpdateTimes(srcArg, src, needBlockToken);
        } else {
            ret = getBlockLocationsInt(srcArg, src, offset, length, needBlockToken);
        }

        if (checkSafeMode && isInSafeMode()) {
            for (LocatedBlock b : ret.blocks.getLocatedBlocks()) {
                // if safe mode & no block locations yet then throw SafeModeException
                if ((b.getLocations() == null) || (b.getLocations().length == 0)) {
                    SafeModeException se = new SafeModeException(
                            "Zero blocklocations for " + src, safeMode());
                    throw new RetriableException(se);
                }
            }
        }
        inodeFile.logProvenanceEvent(getNamenodeId(), FileProvenanceEntry.Operation.getBlockLocations());
        return ret;
    }

    public static class GetBlockLocationsResult {
        final boolean updateAccessTime;
        public final LocatedBlocks blocks;
        boolean updateAccessTime() {
            return updateAccessTime;
        }
        private GetBlockLocationsResult(
                boolean updateAccessTime, LocatedBlocks blocks) {
            this.updateAccessTime = updateAccessTime;
            this.blocks = blocks;
        }
    }

    private void checkUnreadableBySuperuser(FSPermissionChecker pc,
                                            INode inode)
            throws IOException {
        if(pc.isSuperUser()) {
            for (XAttr xattr : FSDirXAttrOp.getXAttrs(inode)) {
                if (XAttrHelper.getPrefixName(xattr).
                        equals(SECURITY_XATTR_UNREADABLE_BY_SUPERUSER)) {
                    if (pc.isSuperUser()) {
                        throw new AccessControlException("Access is denied for " +
                                pc.getUser() + " since the superuser is not allowed to " +
                                "perform this operation.");
                    }
                }
            }
        }
    }

    long getAccessTimePrecision() {
        return accessTimePrecision;
    }

    private boolean isAccessTimeSupported() {
        return accessTimePrecision > 0;
    }

    /**
     * Get phantom block location for the file stored in the database and
     * access times if necessary.
     */

    private GetBlockLocationsResult getPhantomBlockLocationsUpdateTimes(String srcArg, String src, boolean needBlockToken)
            throws IOException {

        FSPermissionChecker pc = getPermissionChecker();

        final INodesInPath iip = dir.getINodesInPath(src, true);
        final INodeFile inode = INodeFile.valueOf(iip.getLastINode(), src);

        if (isPermissionEnabled) {
            dir.checkPathAccess(pc, iip, FsAction.READ);
            checkUnreadableBySuperuser(pc, inode);
        }

        final FileEncryptionInfo feInfo =
                FSDirectory.isReservedRawName(srcArg) ?
                        null : dir.getFileEncryptionInfo(inode, iip);

        final LocatedBlocks blocks = blockManager
                .createPhantomLocatedBlocks(inode, inode.getFileDataInDB(),
                        inode.isUnderConstruction(), needBlockToken, feInfo);

        final long now = now();
        boolean updateAccessTime = isAccessTimeSupported() && !isInSafeMode()
                && now > inode.getAccessTime() + getAccessTimePrecision();
        return new GetBlockLocationsResult(updateAccessTime, blocks);

    }

    static HdfsFileStatus getFileInfo(
            FSDirectory fsd, String src, boolean resolveLink, boolean isRawPath,
            boolean includeStoragePolicy)
            throws IOException {
        String srcs = FSDirectory.normalizePath(src);
        final INodesInPath iip = fsd.getINodesInPath(srcs, resolveLink);
        return getFileInfo(fsd, iip, isRawPath, includeStoragePolicy);
    }

    /**
     * Get the path of a file with the given inode id.
     *
     * @param id
     *    the inode id of the file
     * @return
     *    the path
     * @throws IOException
     */
    public String getPath(long id, boolean inTree) throws IOException {
        LinkedList<INode> resolvedInodes = new LinkedList<>();
        boolean resolved[] = new boolean[1];
        INodeUtil.findPathINodesById(id, inTree, resolvedInodes, resolved);

        if (!resolved[0]) {
            throw new IOException(
                    "Path could not be resolved for inode with id " + id);
        }

        return INodeUtil.constructPath(resolvedInodes);
    }

    /**
     * Get the inode with the given id.
     *
     * @param id
     *    the inode id
     * @return
     *    the inode
     * @throws IOException
     */
    public INode findInode(final long id) throws IOException {
        LightWeightRequestHandler findHandler =
                new LightWeightRequestHandler(HDFSOperationType.GET_INODE) {
                    @Override
                    public Object performTask() throws IOException {
                        INodeDataAccess<INode> dataAccess =
                                (INodeDataAccess) io.hops.metadata.HdfsStorageFactory
                                        .getDataAccess(INodeDataAccess.class);
                        return dataAccess.findInodeByIdFTIS(id);
                    }
                };
        return (INode) findHandler.handle();
    }

    /**
     * Remove leases and inodes related to a given path
     * @param src The given path
     * @param removedINodes Containing the list of inodes to be removed from
     *                      inodesMap
     * @param acquireINodeMapLock Whether to acquire the lock for inode removal
     */
    void removeLeasesAndINodes(String src, List<INode> removedINodes)
            throws IOException {
        leaseManager.removeLeaseWithPrefixPath(src);
        // remove inodes from inodesMap
        if (removedINodes != null) {
            dir.removeFromInodeMap(removedINodes);
            removedINodes.clear();
        }
    }

    /**
     * From the given list, incrementally remove the blocks from blockManager.
     * Write lock is dropped and reacquired every BLOCK_DELETION_INCREMENT to
     * ensure that other waiters on the lock can get in. See HDFS-2938
     *
     * @param blocks
     *          An instance of {@link BlocksMapUpdateInfo} which contains a list
     *          of blocks that need to be removed from blocksMap
     */
    public void removeBlocks(INode.BlocksMapUpdateInfo blocks)
            throws StorageException, TransactionContextException, IOException {
        List<Block> toDeleteList = blocks.getToDeleteList();
        Iterator<Block> iter = toDeleteList.iterator();
        while (iter.hasNext()) {
            for (int i = 0; i < BLOCK_DELETION_INCREMENT && iter.hasNext(); i++) {
                blockManager.removeBlock(iter.next());
            }
        }
    }

    public int getLeaseCreationLockRows(){
        return leaseCreationLockRows;
    }

    public boolean isErasureCodingEnabled() {
        return erasureCodingEnabled;
    }

    //This is for testing purposes only
    @VisibleForTesting
    boolean isImageLoaded() {
        return imageLoaded;
    }
    // exposed for unit tests
    protected void setImageLoaded(boolean flag) {
        imageLoaded = flag;
    }

    void renameTo(final String src, final String dst,
                  Options.Rename... options)
            throws IOException {
        Map.Entry<INode.BlocksMapUpdateInfo, HdfsFileStatus> res = null;
        try {
            checkNameNodeSafeMode("Cannot rename " + src);
            res = FSDirRenameOp.renameToInt(dir, src, dst, options);
        } catch (AccessControlException e) {
            logAuditEvent(false, "rename (options=" + Arrays.toString(options) +
                    ")", src, dst, null);
            throw e;
        }

        HdfsFileStatus auditStat = res.getValue();

        logAuditEvent(true, "rename (options=" + Arrays.toString(options) +
                ")", src, dst, auditStat);
    }

    /**
     * @return the block manager.
     */
    public BlockManager getBlockManager() {
        return blockManager;
    }

    public PathInformation getPathExistingINodesFromDB(final String path,
                                                       final boolean doCheckOwner, final FsAction ancestorAccess,
                                                       final FsAction parentAccess, final FsAction access,
                                                       final FsAction subAccess) throws IOException{
        HopsTransactionalRequestHandler handler =
                new HopsTransactionalRequestHandler(HDFSOperationType.SUBTREE_PATH_INFO) {
                    @Override
                    public void acquireLock(TransactionLocks locks) throws IOException {
                        LockFactory lf = LockFactory.getInstance();
                        INodeLock il = lf.getINodeLock(TransactionLockTypes.INodeLockType.READ_COMMITTED,
                                TransactionLockTypes.INodeResolveType.PATH, path)
                                .setNameNodeID(nameNode.getId())
                                .setActiveNameNodes(nameNode.getActiveNameNodes().getActiveNodes())
                                .skipReadingQuotaAttr(!dir.isQuotaEnabled());
                        locks.add(il).add(lf.getBlockLock()); // blk lock only if file
                        locks.add(lf.getAcesLock());
                    }

                    @Override
                    public Object performTask() throws IOException {
                        FSPermissionChecker pc = getPermissionChecker();
                        byte[][] pathComponents = INode.getPathComponents(path);
                        INodesInPath iip = dir.getExistingPathINodes(pathComponents);
                        if (isPermissionEnabled && !pc.isSuperUser()) {
                            dir.checkPermission(pc, iip, doCheckOwner, ancestorAccess, parentAccess, access, subAccess, false);
                        }

                        boolean isDir = false;

                        QuotaCounts usage = new QuotaCounts.Builder().build();
                        QuotaCounts quota = new QuotaCounts.Builder().build();
                        INode leafInode = iip.getLastINode();
                        if(leafInode != null){  // complete path resolved
                            if(leafInode instanceof INodeFile ||  leafInode instanceof INodeSymlink){
                                isDir = false;
                                //do ns and ds counts for file only
                                leafInode.computeQuotaUsage(getBlockManager().getStoragePolicySuite(), usage);
                            } else{
                                isDir =true;
                                if(leafInode instanceof INodeDirectory && dir.isQuotaEnabled()){
                                    DirectoryWithQuotaFeature q = ((INodeDirectory) leafInode).getDirectoryWithQuotaFeature();
                                    if (q != null) {
                                        quota = q.getQuota();
                                    } else {
                                        quota = leafInode.getQuotaCounts();
                                    }
                                }
                            }
                        }

                        //Get acls
                        List[] acls = new List[iip.length()];
                        for (int i = 0; i < iip.length() ; i++){
                            if (iip.getINode(i) != null){
                                AclFeature aclFeature = INodeAclHelper.getAclFeature(iip.getINode(i));
                                acls[i] = aclFeature != null ? aclFeature.getEntries() : null;
                            }
                        }

                        return new PathInformation(path, pathComponents,
                                iip,isDir, quota, usage, acls);
                    }
                };
        return (PathInformation)handler.handle(this);
    }

    @Override // FSNamesystemMBean
    @Metric({"LiveDataNodes",
            "Number of datanodes marked as live"})
    public int getNumLiveDataNodes() {
        return getBlockManager().getDatanodeManager().getNumLiveDataNodes();
    }

    /**
     * SafeModeInfo contains information related to the safe mode.
     * <p/>
     * An instance of {@link SafeModeInfo} is created when the name node
     * enters safe mode.
     * <p/>
     * During name node startup {@link SafeModeInfo} counts the number of
     * <em>safe blocks</em>, those that have at least the minimal number of
     * replicas, and calculates the ratio of safe blocks to the total number
     * of blocks in the system, which is the size of blocks in
     * {@link FSNameSystem#blockManager}. When the ratio reaches the
     * {@link #threshold} it starts the SafeModeMonitor daemon in order
     * to monitor whether the safe mode {@link #extension} is passed.
     * Then it leaves safe mode and destroys itself.
     * <p/>
     * If safe mode is turned on manually then the number of safe blocks is
     * not tracked because the name node is not intended to leave safe mode
     * automatically in the case.
     *
     * @see ClientProtocol#setSafeMode(HdfsConstants.SafeModeAction, boolean)
     */
    public class SafeModeInfo {

        // configuration fields
        /**
         * Safe mode threshold condition %.
         */
        private double threshold;
        /**
         * Safe mode minimum number of datanodes alive
         */
        private int datanodeThreshold;
        /**
         * Safe mode extension after the threshold.
         */
        private int extension;
        /**
         * Min replication required by safe mode.
         */
        private int safeReplication;
        /**
         * threshold for populating needed replication queues
         */
        private double replicationQueueThreshold;

        // internal fields
        /**
         * Time when threshold was reached.
         * <p/>
         * <br> -1 safe mode is off
         * <br> 0 safe mode is on, and threshold is not reached yet
         * <br> >0 safe mode is on, but we are in extension period
         */
        private long reached() throws IOException{
            return HdfsVariables.getSafeModeReached();
        }
        /**
         * time of the last status printout
         */
        private long lastStatusReport = 0;
        /**
         * Was safe mode entered automatically because available resources were low.
         */
        private boolean resourcesLow = false;
        /** counter for tracking startup progress of reported blocks */
        private CountersQueue.Counter awaitingReportedBlocksCounter;

        public ThreadLocal<Boolean> safeModePendingOperation =
                new ThreadLocal<>();

        /**
         * Creates SafeModeInfo when the name node enters
         * automatic safe mode at startup.
         *
         * @param conf
         *     configuration
         */
        private SafeModeInfo(Configuration conf) throws IOException {
            this.threshold = conf.getFloat(DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_KEY,
                    DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_DEFAULT);
            if (threshold > 1.0) {
                LOG.warn("The threshold value should't be greater than 1, threshold: " +
                        threshold);
            }
            this.datanodeThreshold =
                    conf.getInt(DFS_NAMENODE_SAFEMODE_MIN_DATANODES_KEY,
                            DFS_NAMENODE_SAFEMODE_MIN_DATANODES_DEFAULT);
            this.extension = conf.getInt(DFS_NAMENODE_SAFEMODE_EXTENSION_KEY, 0);
            this.safeReplication = conf.getInt(DFS_NAMENODE_REPLICATION_MIN_KEY,
                    DFS_NAMENODE_REPLICATION_MIN_DEFAULT);
            if (this.safeReplication > 1) {
                LOG.warn("Only safe replication 1 is supported");
                this.safeReplication = 1;
            }

            LOG.info(DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_KEY + " = " + threshold);
            LOG.info(
                    DFS_NAMENODE_SAFEMODE_MIN_DATANODES_KEY + " = " + datanodeThreshold);
            LOG.info(DFS_NAMENODE_SAFEMODE_EXTENSION_KEY + "     = " + extension);

            // default to safe mode threshold (i.e., don't populate queues before leaving safe mode)
            this.replicationQueueThreshold =
                    conf.getFloat(DFS_NAMENODE_REPL_QUEUE_THRESHOLD_PCT_KEY,
                            (float) threshold);
            HdfsVariables.setBlockTotal(0);
        }

        /**
         * Creates SafeModeInfo when safe mode is entered manually, or because
         * available resources are low.
         * <p/>
         * The {@link #threshold} is set to 1.5 so that it could never be reached.
         * {@link #blockTotal} is set to -1 to indicate that safe mode is manual.
         *
         * @see SafeModeInfo
         */
        private SafeModeInfo(boolean resourcesLow) throws IOException {
            this.threshold = 1.5f;  // this threshold can never be reached
            this.datanodeThreshold = Integer.MAX_VALUE;
            this.extension = Integer.MAX_VALUE;
            this.safeReplication = Short.MAX_VALUE + 1; // more than maxReplication
            this.replicationQueueThreshold = 1.5f; // can never be reached
            HdfsVariables.setBlockTotal(-1);
            this.resourcesLow = resourcesLow;
            enter();
            reportStatus("STATE* Safe mode is ON.", true);
        }

        private SafeModeInfo(double threshold, int datanodeThreshold, int extension, int safeReplication,
                             double replicationQueueThreshold, boolean resourcesLow) throws IOException {
            this.threshold = threshold;
            this.datanodeThreshold = datanodeThreshold;
            this.extension = extension;
            this.safeReplication = safeReplication;
            this.replicationQueueThreshold = replicationQueueThreshold;
            this.resourcesLow = resourcesLow;
        }

        private SafeModeInfo(){};

        public double getThreshold() {
            return threshold;
        }

        public int getDatanodeThreshold() {
            return datanodeThreshold;
        }

        public int getExtension() {
            return extension;
        }

        public int getSafeReplication() {
            return safeReplication;
        }

        public double getReplicationQueueThreshold() {
            return replicationQueueThreshold;
        }

        public long getLastStatusReport() {
            return lastStatusReport;
        }

        public boolean isResourcesLow() {
            return resourcesLow;
        }

        public long getReached() throws IOException{
            return reached();
        }

        /**
         * Check if safe mode is on.
         *
         * @return true if in safe mode
         */
        private boolean isOn() throws IOException {
            doConsistencyCheck();
            return this.reached() >= 0;
        }

        /**
         * Enter safe mode.
         */
        private void enter() throws IOException {
            if(isLeader()){
                LOG.info("enter safe mode");
                HdfsVariables.setSafeModeReached(0);
            }
        }

        /**
         * Leave safe mode.
         * <p/>
         * Check for invalid, under- & over-replicated blocks in the end of
         * startup.
         */
        private void leave() throws IOException {
            if(!inSafeMode.getAndSet(false)){
                return;
            }
            forceReadTheSafeModeFromDB.set(false);
            // if not done yet, initialize replication queues.
            // In the standby, do not populate replication queues
            if (!isPopulatingReplQueues() && shouldPopulateReplicationQueues()) {
                initializeReplQueues();
            }

            leaveInternal();

            clearSafeBlocks();
        }

        private void leaveInternal() throws IOException {
            long timeInSafeMode = now() - startTime;
            ServerlessNameNode.stateChangeLog.info(
                    "STATE* Leaving safe mode after " + timeInSafeMode / 1000 + " secs");
            ServerlessNameNode.getNameNodeMetrics().setSafeModeTime((int) timeInSafeMode);

            //Log the following only once (when transitioning from ON -> OFF)
            if (reached() >= 0) {
                ServerlessNameNode.stateChangeLog.info("STATE* Safe mode is OFF");
            }
            if(isLeader()){
                HdfsVariables.exitSafeMode();
            }
            final NetworkTopology nt =
                    blockManager.getDatanodeManager().getNetworkTopology();
            ServerlessNameNode.stateChangeLog.info(
                    "STATE* Network topology has " + nt.getNumOfRacks() + " racks and " +
                            nt.getNumOfLeaves() + " datanodes");
            ServerlessNameNode.stateChangeLog.info("STATE* UnderReplicatedBlocks has " +
                    blockManager.numOfUnderReplicatedBlocks() + " blocks");

            startSecretManagerIfNecessary();
            // If startup has not yet completed, end safemode phase.
            StartupProgress prog = ServerlessNameNode.getStartupProgress();
            if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
                prog.endStep(Phase.SAFEMODE, STEP_AWAITING_REPORTED_BLOCKS);
                prog.endPhase(Phase.SAFEMODE);
            }
        }

        /**
         * Check whether we have reached the threshold for
         * initializing replication queues.
         */
        private boolean canInitializeReplicationQueues() throws IOException {
            return shouldPopulateReplicationQueues() &&
                    blockSafe() >= blockReplicationQueueThreshold();
        }

        /**
         * Safe mode can be turned off iff
         * another namenode went out of safe mode or
         * the threshold is reached and
         * the extension time have passed.
         *
         * @return true if can leave or false otherwise.
         */
        private boolean canLeave() throws IOException {
            if(!isLeader()){
                return false;
            }
            if (reached() == 0) {
                return false;
            }
            if (now() - reached() < extension) {
                reportStatus("STATE* Safe mode ON, in safe mode extension.", false);
                return false;
            }
            if (needEnter()) {
                reportStatus("STATE* Safe mode ON, thresholds not met.", false);
                return false;
            }
            return true;
        }

        /**
         * This ServerlessNameNode tries to help the cluster to get out of safe mode by
         * updating the safe block count.
         * This call will trigger the @link{SafeModeMonitor} if it's not already
         * started.
         * @throws IOException
         */
        private void tryToHelpToGetOut() throws IOException{
            //if the cluster was set in safe mode by the admin we should wait for the admin to get out of safe mode
            //if the cluster has low resources we should wait for the resources to increase to get out of safe mode
            //if the namenode is the leader it should get out by other means.
            if (isManual() || areResourcesLow() || isLeader()) {
                return;
            }
            checkMode();
        }

        /**
         * The cluster already left safe mode, now it's time to for this namenode
         * to leave as well.
         * @throws IOException
         */
        private void clusterLeftSafeModeAlready() throws IOException {
            leaveInternal();
        }

        /**
         * There is no need to enter safe mode
         * if DFS is empty or {@link #threshold} == 0 or another namenode already
         * went out of safe mode
         */
        private boolean needEnter() throws IOException {
            return (threshold != 0 && blockSafe() < blockThreshold()) ||
                    (datanodeThreshold != 0 && getNumLiveDataNodes() < datanodeThreshold) ||
                    (!nameNodeHasResourcesAvailable());
        }

        /**
         * Check and trigger safe mode if needed.
         */

        private void checkMode() throws IOException {
            if(!isLeader() && !(reached()>=0)){
                return;
            }
            // if smmthread is already running, the block threshold must have been
            // reached before, there is no need to enter the safe mode again
            if (smmthread == null && needEnter()) {
                enter();
                // check if we are ready to initialize replication queues
                if (canInitializeReplicationQueues() && !isPopulatingReplQueues()) {
                    initializeReplQueues();
                }
                reportStatus("STATE* Safe mode ON.", false);
                return;
            }
            // the threshold is reached or was reached before
            if (!isOn() ||                           // safe mode is off
                    extension <= 0 || threshold <= 0) {  // don't need to wait
                this.leave(); // leave safe mode
                return;
            }
            if (reached() > 0) {  // threshold has already been reached before
                reportStatus("STATE* Safe mode ON.", false);
                return;
            }
            // start monitor
            if(isLeader()){
                HdfsVariables.setSafeModeReached(now());
            }
            startSafeModeMonitor();

            reportStatus("STATE* Safe mode extension entered.", true);

            // check if we are ready to initialize replication queues
            if (canInitializeReplicationQueues() && !isPopulatingReplQueues()) {
                initializeReplQueues();
            }
        }

        private synchronized void startSafeModeMonitor() throws IOException{
            if (smmthread == null) {
                smmthread = new Daemon(new SafeModeMonitor());
                smmthread.start();
                reportStatus("STATE* Safe mode extension entered.", true);
            }
        }

        /**
         * Set total number of blocks.
         */
        private synchronized void setBlockTotal(int total) throws IOException {
            int blockTotal = total;
            int blockThreshold = (int) (blockTotal * threshold);
            int blockReplicationQueueThreshold = (int) (blockTotal * replicationQueueThreshold);
            HdfsVariables.setBlockTotal(blockTotal, blockThreshold, blockReplicationQueueThreshold);
            checkMode();
        }

        private synchronized void updateBlockTotal(int deltaTotal) throws IOException {
            HdfsVariables.updateBlockTotal(deltaTotal, threshold, replicationQueueThreshold);
            setSafeModePendingOperation(true);
        }

        /**
         * Increment number of safe blocks if current block has
         * reached minimal replication.
         *
         * @param blk
         *     current block
         */
        private void incrementSafeBlockCount(short replication, Block blk) throws IOException {
            if (replication == safeReplication) {
                int added = addSafeBlock(blk.getBlockId());

                // Report startup progress only if we haven't completed startup yet.
                //todo this will not work with multiple NN
                StartupProgress prog = ServerlessNameNode.getStartupProgress();
                if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
                    if (this.awaitingReportedBlocksCounter == null) {
                        this.awaitingReportedBlocksCounter = prog.getCounter(Phase.SAFEMODE,
                                STEP_AWAITING_REPORTED_BLOCKS);
                    }
                    this.awaitingReportedBlocksCounter.add(added);
                }

                setSafeModePendingOperation(true);
            }
        }

        /**
         * Decrement number of safe blocks if current block has
         * fallen below minimal replication.
         * @param blk
         *     current block
         * @param replication
         *     current replication
         */
        private void decrementSafeBlockCount(Block blk, short replication)
                throws IOException {
            if (replication == safeReplication - 1) {
                removeSafeBlock(blk.getBlockId());
                setSafeModePendingOperation(true);
            }
        }

        /**
         * Check if safe mode was entered manually or automatically (at startup, or
         * when disk space is low).
         */
        private boolean isManual() {
            return extension == Integer.MAX_VALUE;
        }

        /**
         * Set manual safe mode.
         */
        private synchronized void setManual() {
            extension = Integer.MAX_VALUE;
        }

        /**
         * Check if safe mode was entered due to resources being low.
         */
        private boolean areResourcesLow() {
            return resourcesLow;
        }

        /**
         * Set that resources are low for this instance of safe mode.
         */
        private void setResourcesLow() {
            resourcesLow = true;
        }

        /**
         * A tip on how safe mode is to be turned off: manually or automatically.
         */
        String getTurnOffTip() throws IOException{
            if(!isOn()){
                return "Safe mode is OFF.";
            }

            //Manual OR low-resource safemode. (Admin intervention required)
            String adminMsg = "It was turned on manually. ";
            if (areResourcesLow()) {
                adminMsg = "Resources are low on NN. Please add or free up more "
                        + "resources then turn off safe mode manually. NOTE:  If you turn off"
                        + " safe mode before adding resources, "
                        + "the NN will immediately return to safe mode. ";
            }
            if (isManual() || areResourcesLow()) {
                return adminMsg
                        + "Use \"hdfs dfsadmin -safemode leave\" to turn safe mode off.";
            }

            boolean thresholdsMet = true;
            int numLive = getNumLiveDataNodes();
            String msg = "";

            int blockSafe;
            int blockThreshold;
            int blockTotal;
            try {
                blockSafe = blockSafe();
                blockThreshold = blockThreshold();
                blockTotal = blockTotal();
            } catch (IOException ex) {
                LOG.error(ex);
                return "got exception " + ex.getMessage();
            }
            if (blockSafe < blockThreshold) {
                msg += String.format(
                        "The reported blocks %d needs additional %d"
                                + " blocks to reach the threshold %.4f of total blocks %d.%n",
                        blockSafe, (blockThreshold - blockSafe), threshold, blockTotal);
                thresholdsMet = false;
            } else {
                msg += String.format("The reported blocks %d has reached the threshold" +
                        " %.4f of total blocks %d. ", blockSafe, threshold, blockTotal);
            }
            if (numLive < datanodeThreshold) {
                msg += String.format(
                        "The number of live datanodes %d needs an additional %d live "
                                + "datanodes to reach the minimum number %d.%n",
                        numLive, (datanodeThreshold - numLive), datanodeThreshold);
                thresholdsMet = false;
            } else {
                msg += String.format("The number of live datanodes %d has reached " +
                                "the minimum number %d. ",
                        numLive, datanodeThreshold);
            }
            long reached = reached();
            msg += (reached > 0) ? "In safe mode extension. " : "";
            msg += "Safe mode will be turned off automatically ";

            if (!thresholdsMet) {
                msg += "once the thresholds have been reached.";
            } else if (reached + extension - now() > 0) {
                msg += ("in " + (reached + extension - now()) / 1000 + " seconds.");
            } else {
                msg += "soon.";
            }

            return msg;
        }

        /**
         * Print status every 20 seconds.
         */
        private void reportStatus(String msg, boolean rightNow)  throws IOException{
            long curTime = now();
            if (!rightNow && (curTime - lastStatusReport < 20 * 1000)) {
                return;
            }
            ServerlessNameNode.stateChangeLog.error(msg + " \n" + getTurnOffTip());
            lastStatusReport = curTime;
        }

        @Override
        public String toString() {
            String blockSafe;
            long blockThreshold=-1;
            long reached = -1;
            try {
                blockSafe = "" + blockSafe();
                blockThreshold = blockThreshold();
                reached = reached();
            } catch (IOException ex) {
                blockSafe = ex.getMessage();
            }
            String resText =
                    "Current safe blocks = " + blockSafe + ". Target blocks = " +
                            blockThreshold + " for threshold = %" + threshold +
                            ". Minimal replication = " + safeReplication + ".";
            if (reached > 0) {
                resText += " Threshold was reached " + new Date(reached) + ".";
            }
            return resText;
        }

        /**
         * Checks consistency of the class state.
         * This is costly so only runs if asserts are enabled.
         */
        private void doConsistencyCheck() throws IOException {
            boolean assertsOn = false;
            assert assertsOn = true; // set to true if asserts are on
            if (!assertsOn) {
                return;
            }

            long blockTotal = blockTotal();

            if (blockTotal == -1 /*&& blockSafe == -1*/) {
                return; // manual safe mode
            }
            long blockSafe = blockSafe();
            int activeBlocks = blockManager.getActiveBlockCount();
            if ((blockTotal != activeBlocks) &&
                    !(blockSafe >= 0 && blockSafe <= blockTotal)) {
                throw new AssertionError(" SafeMode: Inconsistent filesystem state: " +
                        "SafeMode data: blockTotal=" + blockTotal + " blockSafe=" +
                        blockSafe + "; " + "BlockManager data: active=" + activeBlocks);
            }
        }

        private void adjustBlockTotals(List<Block> deltaSafe, int deltaTotal)
                throws IOException {
            int oldBlockSafe = 0;
            if (LOG.isDebugEnabled()) {
                oldBlockSafe = blockSafe();
            }

            if (deltaSafe != null) {
                for (Block b : deltaSafe) {
                    removeSafeBlock(b.getBlockId());
                }
            }

            if (LOG.isDebugEnabled()) {
                int newBlockSafe = blockSafe();
                long blockTotal = blockTotal();
                LOG.debug("Adjusting block totals from " + oldBlockSafe + "/" + blockTotal + " to " + newBlockSafe + "/"
                        + (blockTotal + deltaTotal));
            }

            updateBlockTotal(deltaTotal);
        }

        private void setSafeModePendingOperation(Boolean val) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("SafeModeX Some operation are put on hold");
            }
            safeModePendingOperation.set(val);
        }

        private void adjustSafeBlocks(Set<Long> safeBlocks) throws IOException {
            int added = addSafeBlocks(new ArrayList<Long>(safeBlocks));
            if (LOG.isDebugEnabled()) {
                long blockTotal = blockTotal();
                LOG.debug("Adjusting safe blocks, added " + added +" blocks");
            }

            StartupProgress prog = ServerlessNameNode.getStartupProgress();
            if (prog.getStatus(Phase.SAFEMODE) != Status.COMPLETE) {
                if (this.awaitingReportedBlocksCounter == null) {
                    this.awaitingReportedBlocksCounter = prog.getCounter(Phase.SAFEMODE,
                            STEP_AWAITING_REPORTED_BLOCKS);
                }
                this.awaitingReportedBlocksCounter.add(added);
            }

            setSafeModePendingOperation(true);

            checkMode();
        }

        private void performSafeModePendingOperation() throws IOException {
            if (safeModePendingOperation.get() != null) {
                if (safeModePendingOperation.get()) {
                    LOG.debug("SafeMode about to perform pending safe mode operation");
                    safeModePendingOperation.set(false);
                    checkMode();
                }
            }
        }

        /**
         * Get number of safe blocks from the database
         * @return
         * @throws IOException
         */
        int blockSafe() throws IOException {
            return getBlockSafe();
        }

        /**
         * Get number of total blocks from the database
         * @return
         * @throws IOException
         */
        int blockTotal() throws IOException {
            return HdfsVariables.getBlockTotal();
        }

        /**
         * Get blockReplicationQueueThreshold from the database
         * @return
         * @throws IOException
         */
        int blockReplicationQueueThreshold() throws IOException {
            return HdfsVariables.getBlockReplicationQueueThreshold();
        }

        /**
         * Get blockThreshold from the database
         * @return
         * @throws IOException
         */
        int blockThreshold() throws IOException {
            return HdfsVariables.getBlockThreshold();
        }
    }

    /**
     * Perform resource checks and cache the results.
     */
    void checkAvailableResources() {
        Preconditions.checkState(nnResourceChecker != null, "nnResourceChecker not initialized");
        int tries = 0;
        Throwable lastThrowable = null;
        while (tries < maxDBTries) {
            try {
                SafeModeInfo safeMode = safeMode();
                if(safeMode!=null){
                    //another namenode set safeMode to true since last time we checked
                    //we need to start reading safeMode from the database for every opperation
                    //until the all cluster get out of safemode.
                    forceReadTheSafeModeFromDB.set(true);
                }
                if (!nnResourceChecker.hasAvailablePrimarySpace()) {
                    //Database resources are in between preThreshold and Threshold
                    //that means that soon enough we will hit the resources threshold
                    //therefore, we enable reading the safemode variable from the
                    //database, since at any point from now on, the leader will go to
                    //safe mode.
                    forceReadTheSafeModeFromDB.set(true);
                }

                hasResourcesAvailable = nnResourceChecker.hasAvailableSpace();
                break;
            } catch (StorageException e) {
                LOG.warn("StorageException in checkAvailableResources (" + tries + "/" + maxDBTries + ").", e);
                if (e instanceof TransientStorageException) {
                    continue; //do not count TransientStorageException as a failled try
                }
                lastThrowable = e;
                tries++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // Deliberately ignore
                }
            } catch (Throwable t) {
                LOG.error("Runtime exception in checkAvailableResources. ", t);
                lastThrowable = t;
                tries++;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    // Deliberately ignore
                }
            }
        }
        if (tries >= maxDBTries) {
            terminate(1, lastThrowable);
        }
    }

    /**
     * Periodically calls hasAvailableResources of NameNodeResourceChecker, and
     * if
     * there are found to be insufficient resources available, causes the NN to
     * enter safe mode. If resources are later found to have returned to
     * acceptable levels, this daemon will cause the NN to exit safe mode.
     */
    class NameNodeResourceMonitor implements Runnable {
        boolean shouldNNRmRun = true;

        @Override
        public void run() {
            try {
                while (fsRunning && shouldNNRmRun) {
                    checkAvailableResources();
                    if (!nameNodeHasResourcesAvailable()) {
                        String lowResourcesMsg = "NameNode's database low on available " +
                                "resources.";
                        if (!isInSafeMode()) {
                            LOG.warn(lowResourcesMsg + "Entering safe mode.");
                        } else {
                            LOG.warn(lowResourcesMsg + "Already in safe mode.");
                        }
                        enterSafeMode(true);
                    }
                    try {
                        Thread.sleep(resourceRecheckInterval);
                    } catch (InterruptedException ie) {
                        // Deliberately ignore
                    }
                }
            } catch (Exception e) {
                FSNamesystem.LOG.error("Exception in NameNodeResourceMonitor: ", e);
            }
        }

        public void stopMonitor() {
            shouldNNRmRun = false;
        }
    }

    private class Times {
        long delayBeforeSTOFlag = 0; //This parameter can not be more than TxInactiveTimeout: 1.2 sec
        long delayAfterBuildingTree = 0;

        public Times(long delayBeforeSTOFlag, long delayAfterBuildingTree) {
            this.delayBeforeSTOFlag = delayBeforeSTOFlag;
            this.delayAfterBuildingTree = delayAfterBuildingTree;
        }
    }
}
