package io.hops.transaction.lock;

import org.apache.hadoop.hdfs.server.namenode.ServerlessNameNode;
import io.hops.leader_election.node.ActiveNode;

import java.util.Collection;

public class SubtreeLockHelper {
    public static boolean isSTOLocked(boolean subtreeLocked, long nameNodeId,
                                      Collection<ActiveNode> activeNamenodes) {
        return subtreeLocked &&
                ServerlessNameNode.isNameNodeAlive(activeNamenodes, nameNodeId);
    }
}
