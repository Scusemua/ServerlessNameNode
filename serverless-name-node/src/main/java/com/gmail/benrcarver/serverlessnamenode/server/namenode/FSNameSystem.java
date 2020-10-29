package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.hops.common.CountersQueue;
import com.gmail.benrcarver.serverlessnamenode.hops.metadata.HdfsStorageFactory;
import com.gmail.benrcarver.serverlessnamenode.hops.metadata.HdfsVariables;
import com.gmail.benrcarver.serverlessnamenode.hops.transaction.handler.HDFSOperationType;
import com.gmail.benrcarver.serverlessnamenode.protocol.Block;
import com.gmail.benrcarver.serverlessnamenode.protocol.ClientProtocol;
import com.gmail.benrcarver.serverlessnamenode.protocol.HdfsFileStatus;
import com.google.common.annotations.VisibleForTesting;
import com.google.rpc.Status;
import com.sun.org.apache.xerces.internal.impl.xpath.XPath;
import io.hops.metadata.hdfs.dal.SafeBlocksDataAccess;
import io.hops.transaction.handler.HDFSOperationType;
import io.hops.transaction.handler.HopsTransactionalRequestHandler;
import io.hops.transaction.handler.LightWeightRequestHandler;
import io.hops.transaction.lock.LockFactory;
import io.hops.transaction.lock.TransactionLockTypes;
import io.hops.transaction.lock.TransactionLocks;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.Options;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsAction;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.fs.permission.PermissionStatus;
import org.apache.hadoop.ipc.RetriableException;
import org.apache.hadoop.security.AccessControlException;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.security.token.delegation.DelegationKey;
import org.apache.hadoop.security.token.delegation.web.DelegationTokenManager;
import org.apache.hadoop.hdfs.security.token.delegation.DelegationTokenSecretManager;
import org.apache.hadoop.util.Daemon;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.IOException;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.*;
import static org.apache.hadoop.ipc.Server.getRemoteIp;
import static org.apache.hadoop.ipc.Server.getRemoteUser;

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
     * Logger for audit events, noting successful FSNamesystem operations. Emits
     * to FSNamesystem.audit at INFO. Each event causes a set of tab-separated
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

    // The actual resource checker instance.
    //NameNodeResourceChecker nnResourceChecker;

    private final int maxDBTries;

    //private final FsServerDefaults serverDefaults;
    private final boolean supportAppends;
    //private final ReplaceDatanodeOnFailure dtpReplaceDatanodeOnFailure;

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
    //private ThreadLocal<Times> delays = new ThreadLocal<Times>();
    long delayBeforeSTOFlag = 0; //This parameter can not be more than TxInactiveTimeout: 1.2 sec
    long delayAfterBuildingTree=0;

    int slicerBatchSize;
    int slicerNbThreads;

    private volatile AtomicBoolean forceReadTheSafeModeFromDB = new AtomicBoolean(true);

    //private final TopConf topConf;
    //private TopMetrics topMetrics;

    private final int leaseCreationLockRows;

    /**
     * Create an FSNamesystem.
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

            this.dtpReplaceDatanodeOnFailure = ReplaceDatanodeOnFailure.get(conf);


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

    @Override
    public boolean isRunning() {
        return fsRunning;
    }

    @Override
    public void checkSuperuserPrivilege() throws AccessControlException {

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
        throw new NotImplementedException();
    }

    boolean isAuditEnabled() {
        return !isDefaultAuditLogger || auditLog.isInfoEnabled();
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
     *           If 1) The NameNode is in SafeMode, 2) HA is enabled, and 3)
     *           NameNode is in active state
     * @throws SafeModeException
     *           Otherwise if NameNode is in SafeMode.
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
            NameNode.stateChangeLog.info(
                    "STATE* Leaving safe mode after " + timeInSafeMode / 1000 + " secs");
            NameNode.getNameNodeMetrics().setSafeModeTime((int) timeInSafeMode);

            //Log the following only once (when transitioning from ON -> OFF)
            if (reached() >= 0) {
                NameNode.stateChangeLog.info("STATE* Safe mode is OFF");
            }
            if(isLeader()){
                HdfsVariables.exitSafeMode();
            }
            final NetworkTopology nt =
                    blockManager.getDatanodeManager().getNetworkTopology();
            NameNode.stateChangeLog.info(
                    "STATE* Network topology has " + nt.getNumOfRacks() + " racks and " +
                            nt.getNumOfLeaves() + " datanodes");
            NameNode.stateChangeLog.info("STATE* UnderReplicatedBlocks has " +
                    blockManager.numOfUnderReplicatedBlocks() + " blocks");

            startSecretManagerIfNecessary();
            // If startup has not yet completed, end safemode phase.
            StartupProgress prog = NameNode.getStartupProgress();
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
         * This NameNode tries to help the cluster to get out of safe mode by
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
                StartupProgress prog = NameNode.getStartupProgress();
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
            NameNode.stateChangeLog.error(msg + " \n" + getTurnOffTip());
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

            StartupProgress prog = NameNode.getStartupProgress();
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
}
