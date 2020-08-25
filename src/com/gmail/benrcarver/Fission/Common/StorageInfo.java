package com.gmail.benrcarver.Fission.Common;

import com.gmail.benrcarver.Fission.Protocol.HdfsServerConstants.NodeType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class StorageInfo {
    public static final Logger LOG = LoggerFactory.getLogger(StorageInfo.class.getName());

    public static final int DEFAULT_ROW_ID = 0;
    // StorageInfo is stored as one row in the database.
    protected String blockpoolID = "";
    // id of the block pool. moved it from NNStorage.java to here. This is where it should have been
    private static StorageInfo storageInfo = null;

    public int layoutVersion;   // layout version of the storage data
    public int namespaceID;     // id of the file system
    public String clusterID;      // id of the cluster
    public long cTime;           // creation time of the file system state

    protected final NodeType storageType; // Type of the node using this storage

    protected static final String STORAGE_FILE_VERSION = "VERSION";

    public StorageInfo(NodeType type) {
        this(0, 0, "", 0L, type, "");
    }

    public StorageInfo(int layoutV, int nsID, String cid, long cT, NodeType type, String bpid) {
        layoutVersion = layoutV;
        clusterID = cid;
        namespaceID = nsID;
        cTime = cT;
        storageType = type;
        blockpoolID = bpid;
    }

    public StorageInfo(StorageInfo from) {
        this(from.layoutVersion, from.namespaceID, from.clusterID, from.cTime, from.storageType,
                from.getBlockPoolId());
    }

    public StorageInfo(StorageInfo from, long cTime) {
        this(from.layoutVersion, from.namespaceID, from.clusterID, cTime, from.storageType,
                from.getBlockPoolId());
    }

    public String getBlockPoolId() {
        return blockpoolID;
    }

    public static String newClusterID() {
        return "CID-" + UUID.randomUUID().toString();
    }

    public int getDefaultRowId() {
        return this.DEFAULT_ROW_ID;
    }

    public void setServiceLayoutVersion(int lv) {
        this.layoutVersion = lv;
    }

    /**
     * Layout version of the storage data.
     */
    public int getLayoutVersion() {
        return layoutVersion;
    }

    /**
     * Namespace id of the file system.<p>
     * Assigned to the file system at formatting and never changes after that.
     * Shared by all file system components.
     */
    public int getNamespaceID() {
        return namespaceID;
    }

    /**
     * cluster id of the file system.<p>
     */
    public String getClusterID() {
        return clusterID;
    }

    /**
     * Creation time of the file system state.<p>
     * Modified during upgrades.
     */
    public long getCTime() {
        return cTime;
    }

    public void setStorageInfo(StorageInfo from) {
        layoutVersion = from.layoutVersion;
        clusterID = from.clusterID;
        namespaceID = from.namespaceID;
        cTime = from.cTime;
    }
}
