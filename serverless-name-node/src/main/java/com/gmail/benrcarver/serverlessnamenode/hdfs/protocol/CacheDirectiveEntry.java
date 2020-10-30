package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Describes a path-based cache directive entry.
 */
@InterfaceStability.Evolving
@InterfaceAudience.Public
public class CacheDirectiveEntry {
    private final CacheDirectiveInfo info;
    private final CacheDirectiveStats stats;

    public CacheDirectiveEntry(CacheDirectiveInfo info,
                               CacheDirectiveStats stats) {
        this.info = info;
        this.stats = stats;
    }

    public CacheDirectiveInfo getInfo() {
        return info;
    }

    public CacheDirectiveStats getStats() {
        return stats;
    }
};