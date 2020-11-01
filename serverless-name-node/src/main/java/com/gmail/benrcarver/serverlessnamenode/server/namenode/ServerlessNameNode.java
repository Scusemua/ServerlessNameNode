package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys;
import com.gmail.benrcarver.serverlessnamenode.server.common.HdfsServerConstants.StartupOption;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsConstants;
import com.gmail.benrcarver.serverlessnamenode.protocol.NamenodeProtocols;
import com.gmail.benrcarver.serverlessnamenode.server.common.HdfsServerConstants;
import io.hops.leader_election.node.ActiveNode;
import io.hops.leader_election.node.SortedActiveNodeList;
import org.apache.hadoop.fs.Trash;
import org.apache.hadoop.util.ExitUtil.ExitException;
import io.hops.HdfsStorageFactory;
import io.hops.HdfsVariables;
import io.hops.security.HopsUGException;
import io.hops.security.UsersGroups;
import io.hops.transaction.handler.RequestHandler;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.ha.ServiceFailedException;
import org.apache.hadoop.ipc.Server;
import org.apache.hadoop.net.NetUtils;
import org.apache.hadoop.security.SecurityUtil;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.tracing.TraceUtils;
import org.apache.hadoop.tracing.TracerConfigurationManager;
import org.apache.hadoop.util.JvmPauseMonitor;
import org.apache.htrace.core.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.apache.hadoop.util.ExitUtil.terminate;

import javax.management.ObjectName;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.security.PrivilegedExceptionAction;
import java.util.Collection;
import java.util.concurrent.atomic.AtomicBoolean;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys.*;

public class ServerlessNameNode {

    private AtomicBoolean started = new AtomicBoolean(false);
    public static final Logger LOG = LoggerFactory.getLogger(ServerlessNameNode.class.getName());
    public static final Logger stateChangeLog = LoggerFactory.getLogger("org.apache.hadoop.hdfs.StateChange");
    public static final Logger blockStateChangeLog = LoggerFactory.getLogger("BlockStateChange");

    private Thread emptier;

    public static final int DEFAULT_PORT = 8020;
    private ServerlessNameNodeRPCServer rpcServer;
    private JvmPauseMonitor pauseMonitor;

    private static final String NAMENODE_HTRACE_PREFIX = "namenode.htrace.";

    protected LeaderElection leaderElection;

    protected FSNameSystem namesystem;
    protected final Configuration conf;
    private AtomicBoolean started = new AtomicBoolean(false);

    private ObjectName nameNodeStatusBeanName;
    protected final Tracer tracer;
    protected final TracerConfigurationManager tracerConfigurationManager;

    /**
     * Start NameNode.
     * <p/>
     * The name-node can be started with one of the following startup options:
     * <ul>
     * <li>{@link StartupOption#REGULAR REGULAR} - normal name node startup</li>
     * <li>{@link StartupOption#FORMAT FORMAT} - format name node</li>
     * @param conf
     *     confirguration
     * @throws IOException
     */
    public NameNode(Configuration conf) throws IOException {
        this(conf, HdfsServerConstants.NamenodeRole.NAMENODE);
    }

    protected NameNode(Configuration conf, HdfsServerConstants.NamenodeRole role) throws IOException {
        this.tracer = new Tracer.Builder("NameNode").
                conf(TraceUtils.wrapHadoopConf(NAMENODE_HTRACE_PREFIX, conf)).
                build();
        this.tracerConfigurationManager =
                new TracerConfigurationManager(NAMENODE_HTRACE_PREFIX, conf);
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
                conf.getLong(FS_TRASH_INTERVAL_KEY, FS_TRASH_INTERVAL_DEFAULT);
        if (trashInterval == 0) {
            return;
        } else if (trashInterval < 0) {
            throw new IOException(
                    "Cannot start trash emptier with negative interval." + " Set " +
                            FS_TRASH_INTERVAL_KEY + " to a positive value.");
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
        this.emptier =
                new Thread(new Trash(fs, conf).getEmptier(), "Trash Emptier");
        this.emptier.setDaemon(true);
        this.emptier.start();
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
        terminate(1, t);
    }

    /**
     * Initialize name-node.
     *
     * @param conf
     *     the configuration
     */
    protected void initialize(Configuration conf) throws IOException {
        if (conf.get(HADOOP_USER_GROUP_METRICS_PERCENTILES_INTERVALS) == null) {
            String intervals = conf.get(DFS_METRICS_PERCENTILES_INTERVALS_KEY);
            if (intervals != null) {
                conf.set(HADOOP_USER_GROUP_METRICS_PERCENTILES_INTERVALS,
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

        ServerlessNameNode.initMetrics(conf, this.getRole());
        StartupProgressMetrics.register(startupProgress);

        startHttpServer(conf);
        loadNamesystem(conf);

        rpcServer = createRpcServer(conf);
        tokenServiceName = NetUtils.getHostPortString(rpcServer.getRpcAddress());
        httpServer.setNameNodeAddress(getNameNodeAddress());

        pauseMonitor = new JvmPauseMonitor();
        pauseMonitor.init(conf);
        pauseMonitor.start();

        // metrics.getJvmMetrics().setPauseMonitor(pauseMonitor);

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

    static void initMetrics(Configuration conf, HdfsServerConstants.NamenodeRole role) {
        // metrics = NameNodeMetrics.create(conf, role);
    }

    public static void initializeGenericKeys(Configuration conf) {
        // If the RPC address is set use it to (re-)configure the default FS
        if (conf.get(DFS_NAMENODE_RPC_ADDRESS_KEY) != null) {
            URI defaultUri = URI.create(HdfsConstants.HDFS_URI_SCHEME + "://" +
                    conf.get(DFS_NAMENODE_RPC_ADDRESS_KEY));
            conf.set(FS_DEFAULT_NAME_KEY, defaultUri.toString());
            if (LOG.isDebugEnabled()) {
                LOG.debug("Setting " + FS_DEFAULT_NAME_KEY + " to " + defaultUri.toString());
            }
        }
    }

    public NamenodeProtocols getRpcServer() {
        return rpcServer;
    }

    /**
     * Return the {@link FSNameSystem} object.
     *
     * @return {@link FSNameSystem} object.
     */
    public FSNameSystem getNamesystem() {
        return namesystem;
    }

    /**
     * TODO:FEDERATION
     *
     * @param filesystemURI
     * @return address of file system
     */
    public static InetSocketAddress getAddress(URI filesystemURI) {
        String authority = filesystemURI.getAuthority();
        if (authority == null) {
            throw new IllegalArgumentException(String.format(
                    "Invalid URI for NameNode address (check %s): %s has no authority.",
                    FileSystem.FS_DEFAULT_NAME_KEY, filesystemURI.toString()));
        }
        if (!HdfsConstants.HDFS_URI_SCHEME
                .equalsIgnoreCase(filesystemURI.getScheme()) &&
                !HdfsConstants.ALTERNATIVE_HDFS_URI_SCHEME.equalsIgnoreCase(filesystemURI.getScheme())) {
            throw new IllegalArgumentException(String.format(
                    "Invalid URI for NameNode address (check %s): %s is not of scheme '%s'.",
                    FileSystem.FS_DEFAULT_NAME_KEY, filesystemURI.toString(),
                    HdfsConstants.HDFS_URI_SCHEME));
        }
        return getAddress(authority);
    }

    public static URI getUri(InetSocketAddress namenode) {
        int port = namenode.getPort();
        String portString = port == DEFAULT_PORT ? "" : (":" + port);
        return URI.create(
                HdfsConstants.HDFS_URI_SCHEME + "://" + namenode.getHostName() +
                        portString);
    }

    /**
     * Fetches the address for services to use when connecting to namenode based
     * on the value of fallback returns null if the special address is not
     * specified or returns the default namenode address to be used by both
     * clients and services. Services here are datanodes, backup node, any non
     * client connection
     */
    public static InetSocketAddress getServiceAddress(Configuration conf,
                                                      boolean fallback) {
        String addr = conf.getTrimmed(DFS_NAMENODE_SERVICE_RPC_ADDRESS_KEY);
        if (addr == null || addr.isEmpty()) {
            return fallback ? getAddress(conf) : null;
        }
        return getAddress(addr);
    }

    public static InetSocketAddress getAddress(Configuration conf) {
        URI filesystemURI = FileSystem.getDefaultUri(conf);
        return getAddress(filesystemURI);
    }

    /**
     * Given a configuration get the address of the service rpc server If the
     * service rpc is not configured returns null
     */
    protected InetSocketAddress getServiceRpcServerAddress(Configuration conf) {
        return ServerlessNameNode.getServiceAddress(conf, false);
    }

    protected InetSocketAddress getRpcServerAddress(Configuration conf) {
        return getAddress(conf);
    }

    /* optimize ugi lookup for RPC operations to avoid a trip through
     * UGI.getCurrentUser which is synch'ed
     */
    public static UserGroupInformation getRemoteUser() throws IOException {
        UserGroupInformation ugi = Server.getRemoteUser();
        return (ugi != null) ? ugi : UserGroupInformation.getCurrentUser();
    }

    /**
     * Login as the configured user for the NameNode.
     */
    void loginAsNameNodeUser(Configuration conf) throws IOException {
        InetSocketAddress socAddr = getRpcServerAddress(conf);
        SecurityUtil
                .login(conf, DFS_NAMENODE_KEYTAB_FILE_KEY, DFS_NAMENODE_KERBEROS_PRINCIPAL_KEY,
                        socAddr.getHostName());
    }

    public long getLeCurrentId() {
        return 0;
    }

    public static boolean isNameNodeAlive(Collection<ActiveNode> activeNamenodes,
                                          long namenodeId) {
        if (activeNamenodes == null) {
            // We do not know yet, be conservative
            return true;
        }

        for (ActiveNode namenode : activeNamenodes) {
            if (namenode.getId() == namenodeId) {
                return true;
            }
        }
        return false;
    }

    public SortedActiveNodeList getActiveNameNodes() {
        return leaderElection.getActiveNamenodes();
    }

    /**
     * Returns the id of this namenode
     */
    public long getId() {
        return leaderElection.getCurrentId();
    }

    /**
     * Returns whether the NameNode is completely started
     */
    public boolean isStarted() {
        return this.started.get();
    }

    public HdfsServerConstants.NamenodeRole getRole() {
        /*if (leaderElection != null && leaderElection.isLeader()) {
            return HdfsServerConstants.NamenodeRole.LEADER_NAMENODE;
        }
        return HdfsServerConstants.NamenodeRole.NAMENODE;*/
        LOG.debug("Returning default role of LEADER_NAMENODE as leader election is not implemented yet...");
        return HdfsServerConstants.NamenodeRole.LEADER_NAMENODE;
    }
}
