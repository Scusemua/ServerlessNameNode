package com.gmail.benrcarver.Fission.ServerlessNameNode;

import com.gmail.benrcarver.Fission.Exceptions.ExitException;
import com.gmail.benrcarver.Fission.Exceptions.HadoopIllegalArgumentException;
import com.gmail.benrcarver.Fission.Exceptions.ServiceFailedException;

import com.gmail.benrcarver.Fission.Protocol.Constants;
import com.gmail.benrcarver.Fission.Protocol.DFSConfigKeys;
import com.gmail.benrcarver.Fission.Protocol.HdfsServerConstants.StartupOption;

import static com.gmail.benrcarver.Fission.Protocol.HdfsServerConstants.RollingUpgradeStartupOption;

import com.gmail.benrcarver.Fission.Common.StorageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.PrintStream;
import java.net.URI;
import java.security.PrivilegedExceptionAction;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class NameNode {
    public static final Logger LOG = LoggerFactory.getLogger(NameNode.class.getName());

    protected final Configuration conf;
    private AtomicBoolean started = new AtomicBoolean(false);

    /**
     * only used for testing purposes
     */
    protected boolean stopRequested = false;

    /**
     * The service name of the delegation token issued by the namenode. It is
     * the name service id in HA mode, or the rpc address in non-HA mode.
     */
    private String tokenServiceName;

    private NameNodeRpcServer rpcServer;

    private Thread emptier;

    private static final String USAGE =
            "Usage: java NameNode [" +
                    //StartupOption.BACKUP.getName() + "] | [" +
                    //StartupOption.CHECKPOINT.getName() + "] | [" +
                    StartupOption.FORMAT.getName() + " [" +
                    StartupOption.CLUSTERID.getName() + " cid ] | [" +
                    StartupOption.FORCE.getName() + "] [" +
                    StartupOption.NONINTERACTIVE.getName() + "] ] | [" +
                    //StartupOption.UPGRADE.getName() + "] | [" +
                    //StartupOption.ROLLBACK.getName() + "] | [" +
                    StartupOption.ROLLINGUPGRADE.getName() + " "
                    + RollingUpgradeStartupOption.getAllOptionString() + " ] | \n\t[" +
                    //StartupOption.FINALIZE.getName() + "] | [" +
                    //StartupOption.IMPORT.getName() + "] | [" +
                    //StartupOption.INITIALIZESHAREDEDITS.getName() + "] | [" +
                    //StartupOption.BOOTSTRAPSTANDBY.getName() + "] | [" +
                    //StartupOption.RECOVER.getName() + " [ " +
                    //StartupOption.FORCE.getName() + " ] ] | [ "+
                    StartupOption.NO_OF_CONCURRENT_BLOCK_REPORTS.getName() + " concurrentBlockReports ] | [" +
                    StartupOption.FORMAT_ALL.getName() + " ]";

    /**
     * Start NameNode.
     * <p/>
     * The name-node can be started with one of the following startup options:
     * @param conf
     *     confirguration
     * @throws IOException
     */
    protected NameNode(Configuration conf) throws IOException {
        this.conf = conf;
        try {
            initializeGenericKeys(conf);
            initialize(conf);
            this.started.set(true);
            enterActiveState();
        } catch (IOException | HadoopIllegalArgumentException e) {
            this.stop();
            throw e;
        }
    }

    private void stopCommonServices() {

    }

    /**
     * Stop all NameNode threads and wait for all to finish.
     */
    public void stop() {
        synchronized (this) {
            if (stopRequested) {
                return;
            }
            stopRequested = true;
        }
        try {
            exitActiveServices();
        } catch (ServiceFailedException e) {
            LOG.warn("Encountered exception while exiting state ", e);
        } finally {
            stopCommonServices();
//            if (metrics != null) {
//                metrics.shutdown();
//            }
//            if (namesystem != null) {
//                namesystem.shutdown();
//            }
//            if (nameNodeStatusBeanName != null) {
//                MBeans.unregister(nameNodeStatusBeanName);
//                nameNodeStatusBeanName = null;
//            }
        }
        //tracer.close();
    }

    private void enterActiveState() throws ServiceFailedException {
        try {
            startActiveServicesInternal();
        } catch (IOException e) {
            throw new ServiceFailedException("Failed to start active services", e);
        }
    }

    private void startActiveServicesInternal() throws IOException {
        try {
            namesystem.startActiveServices();
            startTrashEmptier(conf);
        } catch (Throwable t) {
            doImmediateShutdown(t);
        }
    }

    private void startTrashEmptier(final Configuration conf) throws IOException {
        long trashInterval =
                conf.getLong(DFSConfigKeys.FS_TRASH_INTERVAL_KEY, DFSConfigKeys.FS_TRASH_INTERVAL_DEFAULT);
        if (trashInterval == 0) {
            return;
        } else if (trashInterval < 0) {
            throw new IOException(
                    "Cannot start trash emptier with negative interval." + " Set " +
                            DFSConfigKeys.FS_TRASH_INTERVAL_KEY + " to a positive value.");
        }

        // This may be called from the transitionToActive code path, in which
        // case the current user is the administrator, not the NN. The trash
        // emptier needs to run as the NN. See HDFS-3972.
        FileSystem fs =
                SecurityUtil.doAsLoginUser(new PrivilegedExceptionAction<FileSystem>() {
                    @Override
                    public FileSystem run() throws IOException {
                        return FileSystem.get(conf);
                    }
                });
        this.emptier = new Thread(new Trash(fs, conf).getEmptier(), "Trash Emptier");
        this.emptier.setDaemon(true);
        this.emptier.start();
    }

    private void exitActiveServices() throws ServiceFailedException {
        try {
            stopActiveServicesInternal();
        } catch (IOException | ExitException e) {
            throw new ServiceFailedException("Failed to stop active services", e);
        }
    }

    private void stopActiveServicesInternal() throws IOException, ExitException {
        try {
            if (namesystem != null) {
                namesystem.stopActiveServices();
            }
            stopTrashEmptier();
        } catch (Throwable t) {
            doImmediateShutdown(t);
        }
    }

    private void stopTrashEmptier() {
        if (this.emptier != null) {
            emptier.interrupt();
            emptier = null;
        }
    }

    /**
     * Shutdown the NN immediately in an ungraceful way. Used when it would be
     * unsafe for the NN to continue operating, e.g. during a failed HA state
     * transition.
     *
     * @param t
     *     exception which warrants the shutdown. Printed to the NN log
     *     before exit.
     * @throws ExitException
     *     thrown only for testing.
     */
    protected synchronized void doImmediateShutdown(Throwable t)
            throws ExitException {
        String message = "Error encountered requiring NN shutdown. " +
                "Shutting down immediately.";
        try {
            LOG.error(message, t);
        } catch (Throwable ignored) {
            // This is unlikely to happen, but there's nothing we can do if it does.
        }
        // terminate(1, t);
    }

    /**
     * Initialize name-node.
     *
     * @param conf
     *     the configuration
     */
    protected void initialize(Configuration conf) throws IOException {
        if (conf.get(DFSConfigKeys.HADOOP_USER_GROUP_METRICS_PERCENTILES_INTERVALS) == null) {
            String intervals = conf.get(DFSConfigKeys.DFS_METRICS_PERCENTILES_INTERVALS_KEY);
            if (intervals != null) {
                conf.set(DFSConfigKeys.HADOOP_USER_GROUP_METRICS_PERCENTILES_INTERVALS,
                        intervals);
            }
        }

        UserGroupInformation.setConfiguration(conf);
        loginAsNameNodeUser(conf);

        HdfsStorageFactory.setConfiguration(conf);

        int baseWaitTime = conf.getInt(DFSConfigKeys.DFS_NAMENODE_TX_INITIAL_WAIT_TIME_BEFORE_RETRY_KEY,
                DFSConfigKeys.DFS_NAMENODE_TX_INITIAL_WAIT_TIME_BEFORE_RETRY_DEFAULT);
        int retryCount = conf.getInt(DFSConfigKeys.DFS_NAMENODE_TX_RETRY_COUNT_KEY,
                DFSConfigKeys.DFS_NAMENODE_TX_RETRY_COUNT_DEFAULT);
        RequestHandler.setRetryBaseWaitTime(baseWaitTime);
        RequestHandler.setRetryCount(retryCount);

        final long updateThreshold = conf.getLong(DFSConfigKeys.DFS_BR_LB_DB_VAR_UPDATE_THRESHOLD,
                DFSConfigKeys.DFS_BR_LB_DB_VAR_UPDATE_THRESHOLD_DEFAULT);
        final long  maxConcurrentBRs = conf.getLong( DFSConfigKeys.DFS_BR_LB_MAX_CONCURRENT_BR_PER_NN,
                DFSConfigKeys.DFS_BR_LB_MAX_CONCURRENT_BR_PER_NN_DEFAULT);
        final long brMaxProcessingTime = conf.getLong(DFSConfigKeys.DFS_BR_LB_MAX_BR_PROCESSING_TIME,
                DFSConfigKeys.DFS_BR_LB_MAX_BR_PROCESSING_TIME_DEFAULT);
        this.brTrackingService = new BRTrackingService(updateThreshold, maxConcurrentBRs,
                brMaxProcessingTime);
        this.mdCleaner = MDCleaner.getInstance();
        this.stoTableCleanDelay = conf.getLong(
                DFSConfigKeys.DFS_SUBTREE_CLEAN_FAILED_OPS_LOCKS_DELAY_KEY,
                DFSConfigKeys.DFS_SUBTREE_CLEAN_FAILED_OPS_LOCKS_DELAY_DEFAULT);

        String fsOwnerShortUserName = UserGroupInformation.getCurrentUser()
                .getShortUserName();
        String superGroup = conf.get(DFS_PERMISSIONS_SUPERUSERGROUP_KEY,
                DFS_PERMISSIONS_SUPERUSERGROUP_DEFAULT);

        try {
            UsersGroups.addUser(fsOwnerShortUserName);
            UsersGroups.addGroup(superGroup);
            UsersGroups.addUserToGroup(fsOwnerShortUserName, superGroup);
        } catch (HopsUGException e){ }

        try {
            createAndStartCRLFetcherService(conf);
        } catch (Exception ex) {
            LOG.error("Error starting CRL fetcher service", ex);
            throw new IOException(ex);
        }

        NameNode.initMetrics(conf, this.getRole());
        StartupProgressMetrics.register(startupProgress);

        startHttpServer(conf);
        loadNamesystem(conf);

        rpcServer = createRpcServer(conf);
        tokenServiceName = NetUtils.getHostPortString(rpcServer.getRpcAddress());
        httpServer.setNameNodeAddress(getNameNodeAddress());

        pauseMonitor = new JvmPauseMonitor();
        pauseMonitor.init(conf);
        pauseMonitor.start();

        metrics.getJvmMetrics().setPauseMonitor(pauseMonitor);

        startCommonServices(conf);

        if(isLeader()){ //if the newly started namenode is the leader then it means
            //that is cluster was restarted and we can reset the number of default
            // concurrent block reports
            HdfsVariables.setMaxConcurrentBrs(maxConcurrentBRs, null);
            createLeaseLocks(conf);
        }

        // in case of cluster upgrade the retry cache epoch is set to 0
        // update the epoch to correct value
        if (HdfsVariables.getRetryCacheCleanerEpoch() == 0){
            // -1 to ensure the entries in the current epoch are delete by the cleaner
            HdfsVariables.setRetryCacheCleanerEpoch(System.currentTimeMillis()/1000 - 1);
        }
    }

    public static void initializeGenericKeys(Configuration conf) {
        // If the RPC address is set use it to (re-)configure the default FS
        if (conf.get(DFSConfigKeys.DFS_NAMENODE_RPC_ADDRESS_KEY) != null) {
            URI defaultUri = URI.create(Constants.HDFS_URI_SCHEME + "://" +
                    conf.get(DFSConfigKeys.FS_NAMENODE_RPC_ADDRESS_KEY));
            conf.set(DFSConfigKeys.FS_DEFAULT_NAME_KEY, defaultUri.toString());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Setting " + DFSConfigKeys.FS_DEFAULT_NAME_KEY + " to " + defaultUri.toString());
            }
        }
    }

    /**
     * Wait for service to finish. (Normally, it runs forever.)
     */
    public void join() {
        try {
            rpcServer.join();
        } catch (InterruptedException ie) {
            LOG.info("Caught interrupted exception ", ie);
        }
    }

    public static void main(String argv[]) throws Exception {
        try {
            LOG.info("ServerlessNameNode starting now...");

            NameNode namenode = createNameNode(argv, null);
            if (namenode != null) {
                namenode.join();
            }
        } catch (Throwable e) {
            LOG.error("Failed to start namenode.", e);
            //terminate(1, e);
        }
    }

    private static void printUsage(PrintStream out) {
        out.println(USAGE + "\n");
    }

    private static void setStartupOption(Configuration conf, StartupOption opt) {
        conf.set(DFSConfigKeys.DFS_NAMENODE_STARTUP_KEY, opt.name());
    }

    static StartupOption getStartupOption(Configuration conf) {
        return StartupOption.valueOf(
                conf.get(DFSConfigKeys.DFS_NAMENODE_STARTUP_KEY, StartupOption.REGULAR.toString()));
    }

    public static NameNode createNameNode(String argv[], Configuration conf)
            throws IOException {
        LOG.info("createNameNode " + Arrays.asList(argv));
        if (conf == null) {
            conf = new HdfsConfiguration();
        }
        StartupOption startOpt = parseArguments(argv);
        if (startOpt == null) {
            printUsage(System.err);
            return null;
        }
        setStartupOption(conf, startOpt);

        switch (startOpt) {
            //HOP
            case NO_OF_CONCURRENT_BLOCK_REPORTS:
                HdfsVariables.setMaxConcurrentBrs(startOpt.getMaxConcurrentBlkReports(), conf);
                LOG.info("Setting concurrent block reports processing to "+startOpt
                        .getMaxConcurrentBlkReports());
                return null;
            case FORMAT: {
                boolean aborted = formatHdfs(conf, startOpt.getForceFormat(),
                        startOpt.getInteractiveFormat());
                // terminate(aborted ? 1 : 0);
                return null; // avoid javac warning
            }
            case FORMAT_ALL: {
                boolean aborted = formatAll(conf);
                // terminate(aborted ? 1 : 0);
                return null; // avoid javac warning
            }
            case GENCLUSTERID: {
                System.err.println("Generating new cluster id:");
                System.out.println(StorageInfo.newClusterID());
                // terminate(0);
                return null;
            }
            default: {
                DefaultMetricsSystem.initialize("NameNode");
                return new NameNode(conf);
            }
        }
    }
}
