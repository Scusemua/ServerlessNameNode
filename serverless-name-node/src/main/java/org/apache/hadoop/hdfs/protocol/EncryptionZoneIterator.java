package org.apache.hadoop.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.BatchedRemoteIterator;

import java.io.IOException;

/**
 * EncryptionZoneIterator is a remote iterator that iterates over encryption
 * zones. It supports retrying in case of namenode failover.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class EncryptionZoneIterator
        extends BatchedRemoteIterator<Long, EncryptionZone> {

    private final ClientProtocol namenode;

    public EncryptionZoneIterator(ClientProtocol namenode) {
        super(Long.valueOf(0));
        this.namenode = namenode;
    }

    @Override
    public BatchedEntries<EncryptionZone> makeRequest(Long prevId)
            throws IOException {
        return namenode.listEncryptionZones(prevId);
    }

    @Override
    public Long elementToPrevKey(EncryptionZone entry) {
        return entry.getId();
    }
}

