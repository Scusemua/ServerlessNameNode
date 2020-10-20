package com.gmail.benrcarver.serverlessnamenode.server;

import com.gmail.benrcarver.serverlessnamenode.util.HdfsServerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

public class ServerlessNameNode {

    private AtomicBoolean started = new AtomicBoolean(false);
    public static final Logger LOG = LoggerFactory.getLogger(ServerlessNameNode.class.getName());
    public static final Logger stateChangeLog = LoggerFactory.getLogger("org.apache.hadoop.hdfs.StateChange");
    public static final Logger blockStateChangeLog = LoggerFactory.getLogger("BlockStateChange");

    public ServerlessNameNode() {

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
