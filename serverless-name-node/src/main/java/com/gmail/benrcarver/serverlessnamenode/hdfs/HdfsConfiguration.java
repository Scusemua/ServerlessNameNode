package com.gmail.benrcarver.serverlessnamenode.hdfs;

import com.gmail.benrcarver.serverlessnamenode.hdfs.client.HdfsClientConfigKeys;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.conf.Configuration;

/**
 * Adds deprecated keys into the configuration.
 */
@InterfaceAudience.Private
public class HdfsConfiguration extends Configuration {
    static {
        addDeprecatedKeys();

        // adds the default resources
        Configuration.addDefaultResource("hdfs-default.xml");
        Configuration.addDefaultResource("hdfs-site.xml");

    }

    public HdfsConfiguration() {
        super();
    }

    public HdfsConfiguration(boolean loadDefaults) {
        super(loadDefaults);
    }

    public HdfsConfiguration(Configuration conf) {
        super(conf);
    }

    /**
     * This method is here so that when invoked, HdfsConfiguration is class-loaded
     * if
     * it hasn't already been previously loaded.  Upon loading the class, the
     * static
     * initializer block above will be executed to add the deprecated keys and to
     * add
     * the default resources.   It is safe for this method to be called multiple
     * times
     * as the static initializer block will only get invoked once.
     * <p/>
     * This replaces the previously, dangerous practice of other classes calling
     * Configuration.addDefaultResource("hdfs-default.xml") directly without
     * loading
     * HdfsConfiguration class first, thereby skipping the key deprecation
     */
    public static void init() {
    }

    private static void addDeprecatedKeys() {
        Configuration.addDeprecations(new DeprecationDelta[] {
                new DeprecationDelta("dfs.balance.bandwidthPerSec",
                        DFSConfigKeys.DFS_DATANODE_BALANCE_BANDWIDTHPERSEC_KEY),
                new DeprecationDelta("dfs.data.dir",
                        DFSConfigKeys.DFS_DATANODE_DATA_DIR_KEY),
                new DeprecationDelta("dfs.http.address",
                        DFSConfigKeys.DFS_NAMENODE_HTTP_ADDRESS_KEY),
                new DeprecationDelta("dfs.https.address",
                        DFSConfigKeys.DFS_NAMENODE_HTTPS_ADDRESS_KEY),
                new DeprecationDelta("dfs.max.objects",
                        DFSConfigKeys.DFS_NAMENODE_MAX_OBJECTS_KEY),
                new DeprecationDelta("dfs.name.dir.restore",
                        DFSConfigKeys.DFS_NAMENODE_NAME_DIR_RESTORE_KEY),
                new DeprecationDelta("dfs.read.prefetch.size",
                        HdfsClientConfigKeys.Read.PREFETCH_SIZE_KEY),
                new DeprecationDelta("dfs.safemode.extension",
                        DFSConfigKeys.DFS_NAMENODE_SAFEMODE_EXTENSION_KEY),
                new DeprecationDelta("dfs.safemode.threshold.pct",
                        DFSConfigKeys.DFS_NAMENODE_SAFEMODE_THRESHOLD_PCT_KEY),
                new DeprecationDelta("dfs.socket.timeout",
                        DFSConfigKeys.DFS_CLIENT_SOCKET_TIMEOUT_KEY),
                new DeprecationDelta("heartbeat.recheck.interval",
                        DFSConfigKeys.DFS_NAMENODE_HEARTBEAT_RECHECK_INTERVAL_KEY),
                new DeprecationDelta("dfs.https.client.keystore.resource",
                        DFSConfigKeys.DFS_CLIENT_HTTPS_KEYSTORE_RESOURCE_KEY),
                new DeprecationDelta("dfs.https.need.client.auth",
                        DFSConfigKeys.DFS_CLIENT_HTTPS_NEED_AUTH_KEY),
                new DeprecationDelta("slave.host.name",
                        DFSConfigKeys.DFS_DATANODE_HOST_NAME_KEY),
                new DeprecationDelta("session.id",
                        DFSConfigKeys.DFS_METRICS_SESSION_ID_KEY),
                new DeprecationDelta("dfs.access.time.precision",
                        DFSConfigKeys.DFS_NAMENODE_ACCESSTIME_PRECISION_KEY),
                new DeprecationDelta("dfs.replication.considerLoad",
                        DFSConfigKeys.DFS_NAMENODE_REPLICATION_CONSIDERLOAD_KEY),
                new DeprecationDelta("dfs.replication.interval",
                        DFSConfigKeys.DFS_NAMENODE_REPLICATION_INTERVAL_KEY),
                new DeprecationDelta("dfs.replication.min",
                        DFSConfigKeys.DFS_NAMENODE_REPLICATION_MIN_KEY),
                new DeprecationDelta("dfs.replication.pending.timeout.sec",
                        DFSConfigKeys.DFS_NAMENODE_REPLICATION_PENDING_TIMEOUT_SEC_KEY),
                new DeprecationDelta("dfs.max-repl-streams",
                        DFSConfigKeys.DFS_NAMENODE_REPLICATION_MAX_STREAMS_KEY),
                new DeprecationDelta("dfs.permissions",
                        DFSConfigKeys.DFS_PERMISSIONS_ENABLED_KEY),
                new DeprecationDelta("dfs.permissions.supergroup",
                        DFSConfigKeys.DFS_PERMISSIONS_SUPERUSERGROUP_KEY),
                new DeprecationDelta("dfs.write.packet.size",
                        DFSConfigKeys.DFS_CLIENT_WRITE_PACKET_SIZE_KEY),
                new DeprecationDelta("dfs.block.size",
                        DFSConfigKeys.DFS_BLOCK_SIZE_KEY),
                new DeprecationDelta("dfs.datanode.max.xcievers",
                        DFSConfigKeys.DFS_DATANODE_MAX_RECEIVER_THREADS_KEY),
                new DeprecationDelta("io.bytes.per.checksum",
                        DFSConfigKeys.DFS_BYTES_PER_CHECKSUM_KEY),
                new DeprecationDelta("dfs.client.file-block-storage-locations.timeout",
                        DFSConfigKeys.DFS_CLIENT_FILE_BLOCK_STORAGE_LOCATIONS_TIMEOUT_MS),
        });
    }

    public static void main(String[] args) {
        init();
        Configuration.dumpDeprecatedKeys();
    }
}

