package com.gmail.benrcarver.serverlessnamenode.server;

import org.slf4j.Logger;

import java.io.IOException;
import java.security.AccessControlException;
import java.util.Set;

public class FSNameSystem implements NameSystem {

    // Block pool ID used by this namenode
    // HOP made it final and now its value is read from the config file. all
    // namenodes should have same block pool id
    private final String blockPoolId;

    private volatile boolean hasResourcesAvailable = true;
    private volatile boolean fsRunning = true;

    private ServerlessNameNode nameNode;

    FSNameSystem(ServerlessNameNode nameNode) throws IOException {
        this.nameNode = nameNode;

        this.blockPoolId = "DefaultPoolId";
    }

    @Override
    public boolean isRunning() {
        return fsRunning;
    }

    @Override
    public void checkSuperuserPrivilege() throws AccessControlException {

    }

    @Override
    public String getBlockPoolId() {
        return blockPoolId;
    }

    @Override
    public boolean isLeader() {
        return false;
    }

    @Override
    public long getNamenodeId() {
        return 0;
    }

    @Override
    public ServerlessNameNode getNameNode() {
        return null;
    }

    @Override
    public void adjustSafeModeBlocks(Set<Long> safeBlocks) throws IOException {

    }
}
