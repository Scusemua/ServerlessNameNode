package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Describes a Cache Pool entry.
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public class CachePoolEntry {
    private final CachePoolInfo info;
    private final CachePoolStats stats;

    public CachePoolEntry(CachePoolInfo info, CachePoolStats stats) {
        this.info = info;
        this.stats = stats;
    }

    public CachePoolInfo getInfo() {
        return info;
    }

    public CachePoolStats getStats() {
        return stats;
    }
}
