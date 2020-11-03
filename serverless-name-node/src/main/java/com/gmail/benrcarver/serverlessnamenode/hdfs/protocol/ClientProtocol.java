package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.exceptions.DSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.NSQuotaExceededException;
import com.gmail.benrcarver.serverlessnamenode.exceptions.SafeModeException;
import org.apache.hadoop.fs.Options;

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
