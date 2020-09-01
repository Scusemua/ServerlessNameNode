package com.gmail.benrcarver.ServerlessMDS.ServerlessNameNode;

import com.gmail.benrcarver.ServerlessMDS.BlockManagement.BlockInfoContiguous;

import java.io.IOException;

public interface SafeMode {
    /**
     * Check safe mode conditions.
     * If the corresponding conditions are satisfied,
     * trigger the system to enter/leave safe mode.
     */
    public void checkSafeMode() throws IOException;

    /**
     * Is the system in safe mode?
     */
    public boolean isInSafeMode() throws IOException;

    /**
     * Is the system in startup safe mode, i.e. the system is starting up with
     * safe mode turned on automatically?
     */
    public boolean isInStartupSafeMode() throws IOException;

    /**
     * Check whether replication queues are being populated.
     */
    public boolean isPopulatingReplQueues() throws IOException;

    /**
     * Increment number of blocks that reached minimal replication.
     *
     * @param blk
     *     current block
     */
    public void incrementSafeBlockCount(int replication, BlockInfoContiguous blk) throws IOException;

    /**
     * Decrement number of blocks that reached minimal replication.
     * @param blk
     *     current block
     */
    public void decrementSafeBlockCount(BlockInfoContiguous blk)
            throws IOException;
}

