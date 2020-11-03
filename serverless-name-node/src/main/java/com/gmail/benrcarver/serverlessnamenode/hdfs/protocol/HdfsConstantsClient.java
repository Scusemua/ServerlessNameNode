package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

public interface HdfsConstantsClient {
    /**
     * Generation stamp of blocks that pre-date the introduction
     * of a generation stamp.
     */
    long GRANDFATHER_GENERATION_STAMP = 0;
    /**
     * The inode id validation of lease check will be skipped when the request
     * uses GRANDFATHER_INODE_ID for backward compatibility.
     */
    long GRANDFATHER_INODE_ID = 0;
    byte BLOCK_STORAGE_POLICY_ID_UNSPECIFIED = 0;
    /**
     * A prefix put before the namenode URI inside the "service" field
     * of a delgation token, indicating that the URI is a logical (HA)
     * URI.
     */
    String HA_DT_SERVICE_PREFIX = "ha-";
    // The name of the SafeModeException. FileSystem should retry if it sees
    // the below exception in RPC
    String SAFEMODE_EXCEPTION_CLASS_NAME = "org.apache.hadoop.hdfs.server" +
            ".namenode.SafeModeException";
}