package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.Set;

public interface NameSystem extends SafeMode {
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

//    public boolean isGenStampInFuture(Block block)
//            throws StorageException;
//
//    public void adjustSafeModeBlockTotals(List<Block> deltaSafe, int deltaTotal)
//            throws IOException;


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
    public ServerlessNameNode getNameNode();

    /**
     * Adjust the safeblocks if the current namenode is in safemode
     * @param safeBlocks
     *      list of blocks to be considered safe
     * @throws IOException
     */
    public void adjustSafeModeBlocks(Set<Long> safeBlocks) throws IOException;

}
