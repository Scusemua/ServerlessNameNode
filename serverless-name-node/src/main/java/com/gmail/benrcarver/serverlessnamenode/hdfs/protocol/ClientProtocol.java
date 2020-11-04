package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.exceptions.DSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.NSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.protocol.CorruptFileBlocks;
import org.apache.hadoop.fs.Options;
import org.apache.hadoop.fs.ParentNotDirectoryException;
import org.apache.hadoop.fs.UnresolvedLinkException;
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

}
