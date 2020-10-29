package io.hops.common;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.UnresolvedPathException;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.INode;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.INodeDirectory;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.INodeSymlink;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.ServerlessNameNode;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;

import java.util.NoSuchElementException;

public class INodeResolver {
    private final byte[][] components;
    private final boolean resolveLink;
    private final boolean transactional;
    private INode currentInode;
    private int count = 0;
    private int depth = INodeDirectory.ROOT_DIR_DEPTH;

    public INodeResolver(byte[][] components, INode baseINode,
                         boolean resolveLink, boolean transactional) {
        this.components = components;
        currentInode = baseINode;
        this.resolveLink = resolveLink;
        this.transactional = transactional;
    }

    public INodeResolver(byte[][] components, INode baseINode,
                         boolean resolveLink, boolean transactional, int initialCount) {
        this(components, baseINode, resolveLink, transactional);
        this.count = initialCount;
        this.depth = INodeDirectory.ROOT_DIR_DEPTH + (initialCount);
    }

    public boolean hasNext() {
        if (currentInode == null) {
            return false;
        }
        if (currentInode.isFile()) {
            return false;
        }
        return count + 1 < components.length;
    }

    public INode next() throws UnresolvedPathException, StorageException,
            TransactionContextException {
        boolean lastComp = (count == components.length - 1);
        if (currentInode.isSymlink() && (!lastComp || resolveLink)) {
            final String symPath =
                    INodeUtil.constructPath(components, 0, components.length);
            final String preceding = INodeUtil.constructPath(components, 0, count);
            final String remainder =
                    INodeUtil.constructPath(components, count + 1, components.length);
            final String link = DFSUtil.bytes2String(components[count]);
            final String target = ((INodeSymlink) currentInode).getSymlinkString();
            if (ServerlessNameNode.stateChangeLog.isDebugEnabled()) {
                ServerlessNameNode.stateChangeLog.debug(
                        "UnresolvedPathException " + " path: " + symPath + " preceding: " +
                                preceding + " count: " + count + " link: " + link +
                                " target: " + target + " remainder: " + remainder);
            }
            throw new UnresolvedPathException(symPath, preceding, remainder, target);
        }

        if (!hasNext()) {
            throw new NoSuchElementException(
                    "Trying to read more components than available");
        }

        depth++;
        count++;
        long partitionId = INode.calculatePartitionId(currentInode.getId(), DFSUtil.bytes2String(components[count]), (short) depth);

        currentInode = INodeUtil
                .getNode(components[count], currentInode.getId(), partitionId, transactional);
        return currentInode;
    }

    public int getCount() {
        return count;
    }
}
