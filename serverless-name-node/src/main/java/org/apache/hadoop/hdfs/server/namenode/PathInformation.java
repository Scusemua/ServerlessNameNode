package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.server.namenode.INodesInPath;
import org.apache.hadoop.fs.permission.AclEntry;

import java.util.List;

public class PathInformation {

    private String path;
    private byte[][] pathComponents;
    private org.apache.hadoop.hdfs.server.namenode.INodesInPath IIP;
    private boolean dir;
    private QuotaCounts usage;
    private QuotaCounts quota;

    private final List<AclEntry>[] pathInodeAcls;

    public PathInformation(String path,
                           byte[][] pathComponents, org.apache.hadoop.hdfs.server.namenode.INodesInPath IIP,
                           boolean dir, QuotaCounts quota, QuotaCounts usage, List<AclEntry>[] pathInodeAcls) {
        this.path = path;
        this.pathComponents = pathComponents;
        this.IIP = IIP;
        this.dir = dir;
        this.quota = quota;
        this.usage = usage;
        this.pathInodeAcls = pathInodeAcls;
    }

    public String getPath() {
        return path;
    }

    public byte[][] getPathComponents() {
        return pathComponents;
    }

    public boolean isDir() {
        return dir;
    }

    public INodesInPath getINodesInPath(){
        return IIP;
    }

    public QuotaCounts getQuota() {
        return quota;
    }

    public QuotaCounts getUsage() {
        return usage;
    }

    public List<AclEntry>[] getPathInodeAcls() {
        return pathInodeAcls;
    }
}

