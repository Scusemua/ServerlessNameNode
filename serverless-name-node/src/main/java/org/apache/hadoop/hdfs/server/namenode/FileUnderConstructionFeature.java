package org.apache.hadoop.hdfs.server.namenode;

import io.hops.metadata.hdfs.entity.LeasePath;
import org.apache.hadoop.hdfs.server.blockmanagement.BlockInfoContiguous;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.hdfs.server.blockmanagement.BlockInfoContiguousUnderConstruction;

import java.io.IOException;

/**
 * I-node for file being written.
 */
@InterfaceAudience.Private
public class FileUnderConstructionFeature implements INode.Feature {
    private String clientName; // lease holder
    private final String clientMachine;
    private final INode inode;

    private long lastBlockId = -1;
    private long penultimateBlockId = -1;

    public FileUnderConstructionFeature(final String clientName, final String clientMachine, final INode inode) {
        this.clientName = clientName;
        this.clientMachine = clientMachine;
        this.inode = inode;
    }

    public String getClientName() {
        return clientName;
    }

    void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientMachine() {
        return clientMachine;
    }

    /**
     * Update the length for the last block
     *
     * @param lastBlockLength
     *          The length of the last block reported from client
     * @throws IOException
     */
    void updateLengthOfLastBlock(INodeFile f, long lastBlockLength)
            throws IOException {
        BlockInfoContiguous lastBlock = f.getLastBlock();
        assert (lastBlock != null) : "The last block for path "
                + f.getFullPathName() + " is null when updating its length";
        assert (lastBlock instanceof BlockInfoContiguousUnderConstruction)
                : "The last block for path " + f.getFullPathName()
                + " is not a BlockInfoUnderConstruction when updating its length";
        lastBlock.setNumBytes(lastBlockLength);
    }

    /**
     * When deleting a file in the current fs directory, and the file is contained
     * in a snapshot, we should delete the last block if it's under construction
     * and its size is 0.
     */
    void cleanZeroSizeBlock(final INodeFile f,
                            final INode.BlocksMapUpdateInfo collectedBlocks) throws IOException, StorageException, TransactionContextException {
        final BlockInfoContiguous[] blocks = f.getBlocks();
        if (blocks != null && blocks.length > 0
                && blocks[blocks.length - 1] instanceof BlockInfoContiguousUnderConstruction) {
            BlockInfoContiguousUnderConstruction lastUC =
                    (BlockInfoContiguousUnderConstruction) blocks[blocks.length - 1];
            if (lastUC.getNumBytes() == 0) {
                // this is a 0-sized block. do not need check its UC state here
                collectedBlocks.addDeleteBlock(lastUC);
                f.removeLastBlock(lastUC);
            }
        }
    }

    public void updateLastTwoBlocks(Lease lease)
            throws TransactionContextException, StorageException {
        updateLastTwoBlocks(lease, getFullPathName());
    }

    public void updateLastTwoBlocks(Lease lease, String src)
            throws TransactionContextException, StorageException {
        LeasePath lp = lease.getLeasePath(src);
        setLastBlockId(lp.getLastBlockId());
        setPenultimateBlockId(lp.getPenultimateBlockId());
    }

    private String getFullPathName() throws StorageException, TransactionContextException {
        return FSDirectory.getFullPathName(inode);
    }

    void setLastBlockId(long lastBlockId) {
        this.lastBlockId = lastBlockId;
    }

    void setPenultimateBlockId(long penultimateBlockId) {
        this.penultimateBlockId = penultimateBlockId;
    }

    public long getLastBlockId() {
        return lastBlockId;
    }

    public long getPenultimateBlockId() {
        return penultimateBlockId;
    }
}
