package org.apache.hadoop.hdfs.protocol;

import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.BatchedRemoteIterator;
import org.apache.htrace.core.TraceScope;
import org.apache.htrace.core.Tracer;

/**
 * CachePoolIterator is a remote iterator that iterates cache pools.
 * It supports retrying in case of namenode failover.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class CachePoolIterator
        extends BatchedRemoteIterator<String, CachePoolEntry> {

    private final ClientProtocol namenode;
    private final Tracer tracer;

    public CachePoolIterator(ClientProtocol namenode, Tracer tracer) {
        super("");
        this.namenode = namenode;
        this.tracer = tracer;
    }

    @Override
    public BatchedEntries<CachePoolEntry> makeRequest(String prevKey)
            throws IOException {
        try (TraceScope ignored = tracer.newScope("listCachePools")) {
            return namenode.listCachePools(prevKey);
        }
    }

    @Override
    public String elementToPrevKey(CachePoolEntry entry) {
        return entry.getInfo().getPoolName();
    }
}
