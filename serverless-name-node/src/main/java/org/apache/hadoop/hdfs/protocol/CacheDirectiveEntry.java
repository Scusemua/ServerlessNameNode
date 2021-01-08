package org.apache.hadoop.hdfs.protocol;

import org.apache.hadoop.hdfs.protocol.CacheDirectiveInfo;
import org.apache.hadoop.hdfs.protocol.CacheDirectiveStats;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Describes a path-based cache directive entry.
 */
@InterfaceStability.Evolving
@InterfaceAudience.Public
public class CacheDirectiveEntry {
    private final org.apache.hadoop.hdfs.protocol.CacheDirectiveInfo info;
    private final org.apache.hadoop.hdfs.protocol.CacheDirectiveStats stats;

    public CacheDirectiveEntry(org.apache.hadoop.hdfs.protocol.CacheDirectiveInfo info,
                               org.apache.hadoop.hdfs.protocol.CacheDirectiveStats stats) {
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