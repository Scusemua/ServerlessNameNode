package com.gmail.benrcarver.serverlessnamenode.server;

import com.gmail.benrcarver.serverlessnamenode.conf.Configuration;
import com.gmail.benrcarver.serverlessnamenode.fs.Options;
import com.gmail.benrcarver.serverlessnamenode.protocol.HdfsFileStatus;
import org.slf4j.Logger;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import sun.rmi.runtime.Log;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

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
            LogFactory.getLog(FSNamesystem.class.getName() + ".audit");

    static final int DEFAULT_MAX_CORRUPT_FILEBLOCKS_RETURNED = 100;
    static int BLOCK_DELETION_INCREMENT = 1000;

    private final boolean isPermissionEnabled;
    private final UserGroupInformation fsOwner;
    private final String superGroup;

    // Scan interval is not configurable.
    private static final long DELEGATION_TOKEN_REMOVER_SCAN_INTERVAL =
            TimeUnit.MILLISECONDS.convert(1, TimeUnit.HOURS);
    private final DelegationTokenSecretManager dtSecretManager;
    private final boolean alwaysUseDelegationTokensForTests;

    private static final Step STEP_AWAITING_REPORTED_BLOCKS =
            new Step(StepType.AWAITING_REPORTED_BLOCKS);

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

    private volatile boolean hasResourcesAvailable = true;
    private volatile boolean fsRunning = true;

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
    FSNamesystem(Configuration conf, ServerlessNameNode namenode) throws IOException {
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

    void renameTo(final String src, final String dst,
                  Options.Rename... options)
            throws IOException {
        Map.Entry<BlocksMapUpdateInfo, HdfsFileStatus> res = null;
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
}
