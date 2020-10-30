package io.hops.common;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSConfigKeys;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;

import java.io.IOException;

public class IDsMonitor implements Runnable {

    private static final Log LOG = LogFactory.getLog(IDsMonitor.class);
    private static IDsMonitor instance = null;
    private Thread th = null;
    private boolean isRunning = true;

    private int checkInterval;
    private IDsMonitor() {
    }

    public static IDsMonitor getInstance() {
        if (instance == null) {
            instance = new IDsMonitor();
        }
        return instance;
    }

    public static void reset() {
        IDsGeneratorFactory.reset();
        if (instance != null) {
            instance.stop();
        }
        instance=null;
    }

    public void setConfiguration(Configuration conf) {
        IDsGeneratorFactory.getInstance().setConfiguration(conf.getInt
                        (DFSConfigKeys.DFS_NAMENODE_INODEID_BATCH_SIZE,
                                DFSConfigKeys.DFS_NAMENODE_INODEID_BATCH_SIZE_DEFAULT),
                conf.getInt(DFSConfigKeys.DFS_NAMENODE_BLOCKID_BATCH_SIZE,
                        DFSConfigKeys.DFS_NAMENODE_BLOCKID_BATCH_SIZE_DEFAULT),
                conf.getInt(DFSConfigKeys.DFS_NAMENODE_QUOTA_UPDATE_ID_BATCH_SIZE,
                        DFSConfigKeys.DFS_NAMENODE_QUOTA_UPDATE_ID_BATCH_SIZ_DEFAULT),
                conf.getInt
                        (DFSConfigKeys.DFS_NAMENODE_CACHE_DIRECTIVE_ID_BATCH_SIZE,
                                DFSConfigKeys.DFS_NAMENODE_CACHE_DIRECTIVE_ID_BATCH_SIZE_DEFAULT),
                conf.getFloat(DFSConfigKeys.DFS_NAMENODE_INODEID_UPDATE_THRESHOLD,
                        DFSConfigKeys.DFS_NAMENODE_INODEID_UPDATE_THRESHOLD_DEFAULT),
                conf.getFloat(DFSConfigKeys.DFS_NAMENODE_BLOCKID_UPDATE_THRESHOLD,
                        DFSConfigKeys.DFS_NAMENODE_BLOCKID_UPDATE_THRESHOLD_DEFAULT),
                conf.getFloat(
                        DFSConfigKeys.DFS_NAMENODE_QUOTA_UPDATE_ID_UPDATE_THRESHOLD,
                        DFSConfigKeys.DFS_NAMENODE_QUOTA_UPDATE_ID_UPDATE_THRESHOLD_DEFAULT),
                conf.getFloat(DFSConfigKeys.DFS_NAMENODE_CACHE_DIRECTIVE_ID_UPDATE_THRESHOLD,
                        DFSConfigKeys.DFS_NAMENODE_CACHE_DIRECTIVE_ID_UPDATE_THRESHOLD_DEFAULT)
        );

        checkInterval = conf.getInt(DFSConfigKeys.DFS_NAMENODE_IDSMONITOR_CHECK_INTERVAL_IN_MS,
                DFSConfigKeys.DFS_NAMENODE_IDSMONITOR_CHECK_INTERVAL_IN_MS_DEFAULT);
    }



    public void start() {
        isRunning = true;
        getNewIds(); // Avoid race conditions between operations and the first acquisition of ids
        th = new Thread(this, "IDsMonitor");
        th.setDaemon(true);
        th.start();
    }

    public void stop() {
        isRunning=false;
    }

    @Override
    public void run() {
        while (isRunning) {
            getNewIds();
        }
    }

    private void getNewIds() {
        try {

            IDsGeneratorFactory.getInstance().getNewIDs();

            Thread.sleep(checkInterval);
        } catch (InterruptedException ex) {
            LOG.warn("IDsMonitor interrupted: " + ex);
        } catch (IOException ex) {
            LOG.warn("IDsMonitor got exception: " + ex);
        }
    }
}

