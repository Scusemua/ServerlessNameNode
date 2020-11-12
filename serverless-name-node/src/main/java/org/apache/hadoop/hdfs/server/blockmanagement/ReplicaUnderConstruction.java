package org.apache.hadoop.hdfs.server.blockmanagement;

import com.gmail.benrcarver.serverlessnamenode.hdfs.server.common.HdfsServerConstants.ReplicaState;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.metadata.common.FinderType;
import io.hops.metadata.hdfs.entity.Replica;

import java.util.Comparator;

/**
 * ReplicaUnderConstruction contains information about replicas while they are
 * under construction. The GS, the length and the state of the replica is as
 * reported by the data-node. It is not guaranteed, but expected, that
 * data-nodes actually have corresponding replicas.
 */
public class ReplicaUnderConstruction extends Replica {

    public static enum Finder implements FinderType<ReplicaUnderConstruction> {

        ByBlockIdAndINodeId,
        ByINodeId,
        ByINodeIds;

        @Override
        public Class getType() {
            return ReplicaUnderConstruction.class;
        }

        @Override
        public Annotation getAnnotated() {
            switch (this) {
                case ByBlockIdAndINodeId:
                    return Annotation.PrunedIndexScan;
                case ByINodeId:
                    return Annotation.PrunedIndexScan;
                case ByINodeIds:
                    return Annotation.BatchedPrunedIndexScan;
                default:
                    throw new IllegalStateException();
            }
        }

    }

    public static enum Order implements Comparator<ReplicaUnderConstruction> {
        ByStorageId() {
            @Override
            public int compare(ReplicaUnderConstruction o1, ReplicaUnderConstruction o2) {
                return Integer.valueOf(o1.getStorageId()).compareTo(Integer.valueOf(o2.getStorageId()));
            }
        }
    }

    ReplicaState state;
    private boolean chosenAsPrimary;
    private long generationStamp;

    public ReplicaUnderConstruction(ReplicaState state, int storageId,
                                    long blockId, long inodeId, int bucketId, long genStamp) {
        this(state, storageId, blockId, inodeId, bucketId, false, genStamp);
    }

    public ReplicaUnderConstruction(ReplicaState state, int storageId,
                                    long blockId, long inodeId, int bucketId, boolean chosenAsPrimary, long generationStamp) {
        super(storageId, blockId, inodeId, bucketId);
        this.state = state;
        this.chosenAsPrimary = chosenAsPrimary;
        this.generationStamp = generationStamp;
    }

    public DatanodeStorageInfo getExpectedStorageLocation(DatanodeManager manager) {
        return manager.getStorage(this.getStorageId());
    }

    public ReplicaState getState() {
        return state;
    }

    /**
     * Whether the replica was chosen for recovery.
     */
    public boolean getChosenAsPrimary() {
        return chosenAsPrimary;
    }

    public void setState(ReplicaState state) {
        this.state = state;
    }

    /**
     * Set whether this replica was chosen for recovery.
     */
    void setChosenAsPrimary(boolean chosenAsPrimary) throws TransactionContextException, StorageException {
        this.chosenAsPrimary = chosenAsPrimary;
    }

    public long getGenerationStamp() {
        return generationStamp;
    }

    public void setGenerationStamp(long generationStamp) {
        this.generationStamp = generationStamp;
    }
}
