package org.apache.hadoop.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveInfo;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveStats;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Describes a path-based cache directive entry.
 */
@InterfaceStability.Evolving
@InterfaceAudience.Public
public class CacheDirectiveEntry {
    private final com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveInfo info;
    private final com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveStats stats;

    public CacheDirectiveEntry(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveInfo info,
                               com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.CacheDirectiveStats stats) {
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