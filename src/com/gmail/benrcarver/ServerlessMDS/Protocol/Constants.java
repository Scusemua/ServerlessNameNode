package com.gmail.benrcarver.ServerlessMDS.Protocol;

public class Constants {
    // Long that indicates "leave current quota unchanged"
    public static final long QUOTA_DONT_SET = Long.MAX_VALUE;
    public static final long QUOTA_RESET = -1L;
    public static final int BYTES_IN_INTEGER = Integer.SIZE / Byte.SIZE;

    /**
     * URI Scheme for hdfs://namenode/ URIs.
     */
    public static final String HDFS_URI_SCHEME = "hdfs";

    public static final byte MEMORY_STORAGE_POLICY_ID = 15;
    public static final String MEMORY_STORAGE_POLICY_NAME = "LAZY_PERSIST";
    public static final byte ALLSSD_STORAGE_POLICY_ID = 12;
    public static final String ALLSSD_STORAGE_POLICY_NAME = "ALL_SSD";
    public static final byte ONESSD_STORAGE_POLICY_ID = 10;
    public static final String ONESSD_STORAGE_POLICY_NAME = "ONE_SSD";
    public static final byte HOT_STORAGE_POLICY_ID = 7;
    public static final String HOT_STORAGE_POLICY_NAME = "HOT";
    public static final byte WARM_STORAGE_POLICY_ID = 5;
    public static final String WARM_STORAGE_POLICY_NAME = "WARM";
    public static final byte COLD_STORAGE_POLICY_ID = 2;
    public static final String COLD_STORAGE_POLICY_NAME = "COLD";

    // TODO should be conf injected?
    public static final int DEFAULT_DATA_SOCKET_SIZE = 128 * 1024;

    // Timeouts for communicating with DataNode for streaming writes/reads
    public static final int READ_TIMEOUT = 60 * 1000;
    public static final int READ_TIMEOUT_EXTENSION = 5 * 1000;
    public static final int WRITE_TIMEOUT = 8 * 60 * 1000;
    //for write pipeline
    public static final int WRITE_TIMEOUT_EXTENSION = 5 * 1000;
}
