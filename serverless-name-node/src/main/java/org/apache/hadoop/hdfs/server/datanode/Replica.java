package org.apache.hadoop.hdfs.server.datanode;


import com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.HdfsServerConstants;
import org.apache.hadoop.classification.InterfaceAudience;

/**
 * This represents block replicas which are stored in DataNode.
 */
@InterfaceAudience.Private
public interface Replica {
    /**
     * Get the block ID
     */
    public long getBlockId();

    /**
     * Get the generation stamp
     */
    public long getGenerationStamp();

    /**
     * Get the replica state
     *
     * @return the replica state
     */
    public HdfsServerConstants.ReplicaState getState();

    /**
     * Get the number of bytes received
     *
     * @return the number of bytes that have been received
     */
    public long getNumBytes();

    /**
     * Get the number of bytes that have written to disk
     *
     * @return the number of bytes that have written to disk
     */
    public long getBytesOnDisk();

    /**
     * Get the number of bytes that are visible to readers
     *
     * @return the number of bytes that are visible to readers
     */
    public long getVisibleLength();

    /**
     * Return the storageUuid of the volume that stores this replica.
     */
    public String getStorageUuid();
}

