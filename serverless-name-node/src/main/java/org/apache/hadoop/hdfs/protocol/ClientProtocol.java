package org.apache.hadoop.hdfs.protocol;

import io.hops.metadata.hdfs.entity.EncodingStatus;
import org.apache.hadoop.hdfs.server.namenode.NotReplicatedYetException;
import org.apache.hadoop.hdfs.server.namenode.SafeModeException;
import org.apache.hadoop.fs.Options;
import org.apache.hadoop.fs.ParentNotDirectoryException;
import org.apache.hadoop.fs.StorageType;
import org.apache.hadoop.fs.UnresolvedLinkException;
import org.apache.hadoop.io.retry.AtMostOnce;
import org.apache.hadoop.io.retry.Idempotent;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.security.AccessControlException;

/**
 * *******************************************************************
 * ClientProtocol is used by user code via
 * org.apache.hadoop.hdfs.DistributedFileSystem class to communicate
 * with the NameNode. User code can manipulate the directory namespace,
 * as well as open/close file streams, etc.
 * <p/>
 * ********************************************************************
 */
public interface ClientProtocol {

    /**
     * The client can give up on a block by calling abandonBlock().
     * The client can then  either obtain a new block, or complete or abandon the
     * file.
     * Any partial writes to the block will be discarded.
     *
     * @param b         Block to abandon
     * @param fileId    The id of the file where the block resides.  Older clients
     *                    will pass GRANDFATHER_INODE_ID here.
     * @param src       The path of the file where the block resides.
     * @param holder    Lease holder.
     *
     * @throws AccessControlException
     *     If access is denied
     * @throws FileNotFoundException
     *     file <code>src</code> is not found
     * @throws UnresolvedLinkException
     *     If <code>src</code> contains a symlink
     * @throws IOException
     *     If an I/O error occurred
     */
    @Idempotent
    public void abandonBlock(ExtendedBlock b, long fileId, String src, String holder)
            throws AccessControlException, FileNotFoundException,
            UnresolvedLinkException, IOException;

    /**
     * Update a pipeline for a block under construction
     *
     * @param clientName
     *     the name of the client
     * @param oldBlock
     *     the old block
     * @param newBlock
     *     the new block containing new generation stamp and length
     * @param newNodes
     *     datanodes in the pipeline
     * @throws IOException
     *     if any error occurs
     */
    @AtMostOnce
    public void updatePipeline(String clientName, ExtendedBlock oldBlock,
                               ExtendedBlock newBlock, DatanodeID[] newNodes, String[] newStorages)
            throws IOException;

    /**
     * A client that wants to write an additional block to the
     * indicated filename (which must currently be open for writing)
     * should call addBlock().
     * <p/>
     * addBlock() allocates a new block and datanodes the block data
     * should be replicated to.
     * <p/>
     * addBlock() also commits the previous block by reporting
     * to the name-node the actual generation stamp and the length
     * of the block that the client has transmitted to data-nodes.
     *
     * @param src
     *     the file being created
     * @param clientName
     *     the name of the client that adds the block
     * @param previous
     *     previous block
     * @param excludeNodes
     *     a list of nodes that should not be
     *     allocated for the current block
     * @param fileId the id uniquely identifying a file
     *
     * @return LocatedBlock allocated block information.
     * @throws AccessControlException
     *     If access is denied
     * @throws FileNotFoundException
     *     If file <code>src</code> is not found
     * @throws NotReplicatedYetException
     *     previous blocks of the file are not
     *     replicated yet. Blocks cannot be added until replication
     *     completes.
     * @throws SafeModeException
     *     create not allowed in safemode
     * @throws UnresolvedLinkException
     *     If <code>src</code> contains a symlink
     * @throws IOException
     *     If an I/O error occurred
     */
    @Idempotent
    public LocatedBlock addBlock(String src, String clientName,
                                 ExtendedBlock previous, DatanodeInfo[] excludeNodes, long fileId, String[] favoredNodes)
            throws AccessControlException, FileNotFoundException,
            NotReplicatedYetException, SafeModeException, UnresolvedLinkException,
            IOException;

    /**
     * Get a datanode for an existing pipeline.
     *
     * @param src
     *     the file being written
     * @param fileId the ID of the file being written
     * @param blk
     *     the block being written
     * @param existings
     *     the existing nodes in the pipeline
     * @param excludes
     *     the excluded nodes
     * @param numAdditionalNodes
     *     number of additional datanodes
     * @param clientName
     *     the name of the client
     * @return the located block.
     * @throws AccessControlException
     *     If access is denied
     * @throws FileNotFoundException
     *     If file <code>src</code> is not found
     * @throws SafeModeException
     *     create not allowed in safemode
     * @throws UnresolvedLinkException
     *     If <code>src</code> contains a symlink
     * @throws IOException
     *     If an I/O error occurred
     */
    @Idempotent
    public LocatedBlock getAdditionalDatanode(final String src,final long fileId,
                                              final ExtendedBlock blk,
                                              final DatanodeInfo[] existings,
                                              final String[] existingStorageIDs,
                                              final DatanodeInfo[] excludes,
                                              final int numAdditionalNodes,
                                              final String clientName)
            throws AccessControlException, FileNotFoundException, SafeModeException,
            UnresolvedLinkException, IOException;

    /**
     * Enter, leave or get safe mode.
     * <p/>
     * Safe mode is a name node state when it
     * <ol><li>does not accept changes to name space (read-only), and</li>
     * <li>does not replicate or delete blocks.</li></ol>
     * <p/>
     * <p/>
     * Safe mode is entered automatically at name node startup.
     * Safe mode can also be entered manually using
     * {@link #setSafeMode(HdfsConstants.SafeModeAction, boolean)
     * setSafeMode(SafeModeAction.SAFEMODE_ENTER,false)}.
     * <p/>
     * At startup the name node accepts data node reports collecting
     * information about block locations.
     * In order to leave safe mode it needs to collect a configurable
     * percentage called threshold of blocks, which satisfy the minimal
     * replication condition.
     * The minimal replication condition is that each block must have at least
     * <tt>dfs.namenode.replication.min</tt> replicas.
     * When the threshold is reached the name node extends safe mode
     * for a configurable amount of time
     * to let the remaining data nodes to check in before it
     * will start replicating missing blocks.
     * Then the name node leaves safe mode.
     * <p/>
     * If safe mode is turned on manually using
     * {@link #setSafeMode(HdfsConstants.SafeModeAction, boolean)
     * setSafeMode(SafeModeAction.SAFEMODE_ENTER,false)}
     * then the name node stays in safe mode until it is manually turned off
     * using {@link #setSafeMode(HdfsConstants.SafeModeAction, boolean)
     * setSafeMode(SafeModeAction.SAFEMODE_LEAVE,false)}.
     * Current state of the name node can be verified using
     * {@link #setSafeMode(HdfsConstants.SafeModeAction, boolean)
     * setSafeMode(SafeModeAction.SAFEMODE_GET,false)}
     * <h4>Configuration parameters:</h4>
     * <tt>dfs.safemode.threshold.pct</tt> is the threshold parameter.<br>
     * <tt>dfs.safemode.extension</tt> is the safe mode extension parameter.<br>
     * <tt>dfs.namenode.replication.min</tt> is the minimal replication
     * parameter.
     * <p/>
     * <h4>Special cases:</h4>
     * The name node does not enter safe mode at startup if the threshold is
     * set to 0 or if the name space is empty.<br>
     * If the threshold is set to 1 then all blocks need to have at least
     * minimal replication.<br>
     * If the threshold value is greater than 1 then the name node will not be
     * able to turn off safe mode automatically.<br>
     * Safe mode can always be turned off manually.
     *
     * @param action
     *     <ul> <li>0 leave safe mode;</li>
     *     <li>1 enter safe mode;</li>
     *     <li>2 get safe mode state.</li></ul>
     * @param isChecked
     *     If true then action will be done only in ActiveNN.
     * @return <ul><li>0 if the safe mode is OFF or</li>
     * <li>1 if the safe mode is ON.</li></ul>
     * @throws IOException
     */
    @Idempotent
    public boolean setSafeMode(HdfsConstants.SafeModeAction action,
                               boolean isChecked) throws IOException;

    /**
     * Set the quota for a directory.
     * @param path  The string representation of the path to the directory
     * @param namespaceQuota Limit on the number of names in the tree rooted
     *                       at the directory
     * @param storagespaceQuota Limit on storage space occupied all the files under
     *                       this directory.
     * @param type StorageType that the space quota is intended to be set on.
     *             It may be null when called by traditional space/namespace quota.
     *             When type is is not null, the storagespaceQuota parameter is for
     *             type specified and namespaceQuota must be
     *             {@link HdfsConstants#QUOTA_DONT_SET}.
     *
     * <br><br>
     *
     * The quota can have three types of values : (1) 0 or more will set
     * the quota to that value, (2) {@link HdfsConstants#QUOTA_DONT_SET}  implies
     * the quota will not be changed, and (3) {@link HdfsConstants#QUOTA_RESET}
     * implies the quota will be reset. Any other value is a runtime error.
     *
     * @throws AccessControlException permission denied
     * @throws FileNotFoundException file <code>path</code> is not found
     * @throws QuotaExceededException if the directory size
     *           is greater than the given quota
     * @throws UnresolvedLinkException if the <code>path</code> contains a symlink.
     * @throws IOException If an I/O error occurred
     */
    @Idempotent
    public void setQuota(String path, long namespaceQuota, long storagespaceQuota,
                         StorageType type) throws AccessControlException, FileNotFoundException,
            UnresolvedLinkException, IOException;

    /**
     * @return CorruptFileBlocks, containing a list of corrupt files (with
     * duplicates if there is more than one corrupt block in a file)
     * and a cookie
     * @throws IOException
     *     Each call returns a subset of the corrupt files in the system. To
     *     obtain
     *     all corrupt files, call this method repeatedly and each time pass in
     *     the
     *     cookie returned from the previous call.
     */
    @Idempotent
    public CorruptFileBlocks listCorruptFileBlocks(String path, String cookie)
            throws IOException;

    /**
     * Get a new generation stamp together with an access token for
     * a block under construction
     * <p/>
     * This method is called only when a client needs to recover a failed
     * pipeline or set up a pipeline for appending to a block.
     *
     * @param block
     *     a block
     * @param clientName
     *     the name of the client
     * @return a located block with a new generation stamp and an access token
     * @throws IOException
     *     if any error occurs
     */
    @Idempotent
    public LocatedBlock updateBlockForPipeline(ExtendedBlock block,
                                               String clientName) throws IOException;

    /**
     * Rename an item in the file system namespace.
     *
     * @param src
     *     existing file or directory name.
     * @param dst
     *     new name.
     * @return true if successful, or false if the old name does not exist
     * or if the new name already belongs to the namespace.
     * @throws IOException
     *     an I/O error occurred
     */
    // @AtMostOnce
    public boolean rename(String src, String dst)
            throws UnresolvedLinkException, IOException;

    /**
     * Rename src to dst.
     * <ul>
     * <li>Fails if src is a file and dst is a directory.
     * <li>Fails if src is a directory and dst is a file.
     * <li>Fails if the parent of dst does not exist or is a file.
     * </ul>
     * <p/>
     * Without OVERWRITE option, rename fails if the dst already exists.
     * With OVERWRITE option, rename overwrites the dst, if it is a file
     * or an empty directory. Rename fails if dst is a non-empty directory.
     * <p/>
     * This implementation of rename is atomic.
     * <p/>
     *
     * @param src
     *     existing file or directory name.
     * @param dst
     *     new name.
     * @param options
     *     Rename options
     * @throws AccessControlException
     *     If access is denied
     * @throws DSQuotaExceededException
     *     If rename violates disk space
     *     quota restriction
     * @throws FileAlreadyExistsException
     *     If <code>dst</code> already exists and
     *     <code>options</options> has Rename#OVERWRITE option
     *     false.
     * @throws FileNotFoundException
     *     If <code>src</code> does not exist
     * @throws NSQuotaExceededException
     *     If rename violates namespace
     *     quota restriction
     * @throws ParentNotDirectoryException
     *     If parent of <code>dst</code>
     *     is not a directory
     * @throws SafeModeException
     *     rename not allowed in safemode
     * @throws UnresolvedLinkException
     *     If <code>src</code> or
     *     <code>dst</code> contains a symlink
     * @throws IOException
     *     If an I/O error occurred
     */
    // @AtMostOnce
    public void rename2(String src, String dst, Options.Rename... options)
            throws AccessControlException, DSQuotaExceededException,
            FileAlreadyExistsException, FileNotFoundException,
            NSQuotaExceededException, ParentNotDirectoryException, SafeModeException,
            UnresolvedLinkException, IOException;

    ///////////////////////////////////////
    // Erasure coding
    ///////////////////////////////////////

    /**
     * Get the erasure coding status of a file
     *
     * @param filePath
     *    the path of the file
     * @return
     *    the encoding status of the file
     * @throws IOException
     */
    @Idempotent
    public EncodingStatus getEncodingStatus(String filePath) throws IOException;

}
