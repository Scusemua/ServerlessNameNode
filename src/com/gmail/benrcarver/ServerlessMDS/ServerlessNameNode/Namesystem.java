package com.gmail.benrcarver.ServerlessMDS.ServerlessNameNode;

import com.gmail.benrcarver.ServerlessMDS.Exceptions.AccessControlException;
import com.gmail.benrcarver.ServerlessMDS.Exceptions.StorageException;
import com.gmail.benrcarver.ServerlessMDS.Protocol.Block;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface Namesystem extends SafeMode {
    /**
     * Is this name system running?
     */
    public boolean isRunning();

    /**
     * Check if the user has superuser privilege.
     */
    public void checkSuperuserPrivilege() throws AccessControlException;

    /**
     * @return the block pool ID
     */
    public String getBlockPoolId();

    public boolean isGenStampInFuture(Block block)
            throws StorageException;

    public void adjustSafeModeBlockTotals(List<Block> deltaSafe, int deltaTotal)
            throws IOException;


    /**
     * Is it a Leader
     */
    public boolean isLeader();

    /**
     * Returns the namenode id
     */
    public long getNamenodeId();

    /**
     * Get the associated NameNode
     * @return the @link{NameNode}
     */
    public NameNode getNameNode();

    /**
     * Adjust the safeblocks if the current namenode is in safemode
     * @param safeBlocks
     *      list of blocks to be considered safe
     * @throws IOException
     */
    public void adjustSafeModeBlocks(Set<Long> safeBlocks) throws IOException;


}