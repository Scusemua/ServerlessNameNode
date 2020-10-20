package com.gmail.benrcarver.serverlessnamenode.server;

import com.gmail.benrcarver.serverlessnamenode.exceptions.ParentNotDirectoryException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.DSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.NSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.UnresolvedLinkException;
import com.gmail.benrcarver.serverlessnamenode.fs.Options;
import com.gmail.benrcarver.serverlessnamenode.fs.Path;
import com.gmail.benrcarver.serverlessnamenode.protocol.NamenodeProtocols;
import org.slf4j.Logger;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.security.AccessControlException;

import static com.gmail.benrcarver.serverlessnamenode.util.HdfsServerConstants.MAX_PATH_DEPTH;
import static com.gmail.benrcarver.serverlessnamenode.util.HdfsServerConstants.MAX_PATH_LENGTH;

public class ServerlessNameNodeRPCServer implements NamenodeProtocols {
    // Dependencies from other parts of NN.
    protected final ServerlessNameNode nameNode;

    private static final Logger LOG = ServerlessNameNode.LOG;
    private static final Logger stateChangeLog = ServerlessNameNode.stateChangeLog;
    private static final Logger blockStateChangeLog = ServerlessNameNode.blockStateChangeLog;

    public ServerlessNameNodeRPCServer(ServerlessNameNode nameNode) {
        this.nameNode = nameNode;
    }

    private void checkNNStartup() throws IOException {
        if (!this.nameNode.isStarted()) {
            throw new IOException(this.nameNode.getRole() + " still not started");
        }
    }

    /**
     * Check path length does not exceed maximum.  Returns true if
     * length and depth are okay.  Returns false if length is too long
     * or depth is too great.
     */
    private boolean checkPathLength(String src) {
        Path srcPath = new Path(src);
        return (src.length() <= MAX_PATH_LENGTH &&
                srcPath.depth() <= MAX_PATH_DEPTH);
    }

    @Override
    public boolean rename(String src, String dst) throws UnresolvedLinkException, IOException {
        return false;
    }

    @Override
    public void rename2(String src, String dst, Options.Rename... options) throws AccessControlException,
            DSQuotaExceededException, FileAlreadyExistsException, FileNotFoundException, NSQuotaExceededException,
            ParentNotDirectoryException, SafeModeException, UnresolvedLinkException, IOException {
        // Check that we've started up.
        checkNNStartup();

        if (stateChangeLog.isDebugEnabled()) {
            stateChangeLog.debug("*DIR* NameNode.rename: " + src + " to " + dst);
        }

        // Check that the path is not too long now.
        if (!checkPathLength(dst)) {
            throw new IOException("rename: Pathname too long.  Limit " + MAX_PATH_LENGTH + " characters, " + MAX_PATH_DEPTH + " levels.");
        }

        LOG.debug("Not checking for duplicate request in retry cache bc caching has not been implemented yet.");
        /*RetryCacheEntry cacheEntry = LightWeightCacheDistributed.getTransactional();
        if (cacheEntry != null && cacheEntry.isSuccess()) {
            return; // Return previous response
        }*/

        boolean success = false;
        try {
            namesystem.renameTo(src, dst, options);
            success = true;
        } finally {
            LOG.debug("Not adding result to retry cache bc caching has not been implemented yet.");
            /*LightWeightCacheDistributed.putTransactional(success);*/
        }
        //metrics.incrFilesRenamed();
    }
}
