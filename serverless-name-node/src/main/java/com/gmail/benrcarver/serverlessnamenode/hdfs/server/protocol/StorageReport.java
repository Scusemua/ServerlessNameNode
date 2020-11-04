package com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol;

/**
 * Utilization report for a Datanode storage
 */
public class StorageReport {
    public static final StorageReport[] EMPTY_ARRAY = {};

    private final DatanodeStorage storage;
    private final boolean failed;
    private final long capacity;
    private final long dfsUsed;
    private final long remaining;
    private final long blockPoolUsed;

    public StorageReport(DatanodeStorage storage, boolean failed, long capacity,
                         long dfsUsed, long remaining, long bpUsed) {
        this.storage = storage;
        this.failed = failed;
        this.capacity = capacity;
        this.dfsUsed = dfsUsed;
        this.remaining = remaining;
        this.blockPoolUsed = bpUsed;
    }

    public DatanodeStorage getStorage() {
        return storage;
    }

    public boolean isFailed() {
        return failed;
    }

    public long getCapacity() {
        return capacity;
    }

    public long getDfsUsed() {
        return dfsUsed;
    }

    public long getRemaining() {
        return remaining;
    }

    public long getBlockPoolUsed() {
        return blockPoolUsed;
    }
}
