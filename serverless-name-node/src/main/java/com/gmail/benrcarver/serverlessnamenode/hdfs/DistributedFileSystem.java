package com.gmail.benrcarver.serverlessnamenode.hdfs;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.util.Progressable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

/****************************************************************
 * Implementation of the abstract FileSystem for the DFS system.
 * This object is the way end-user code interacts with a Hadoop
 * DistributedFileSystem.
 *
 *****************************************************************/
@InterfaceAudience.LimitedPrivate({ "MapReduce", "HBase" })
@InterfaceStability.Unstable
public class DistributedFileSystem extends FileSystem {
    private Path workingDir;
    private URI uri;
    private String homeDirPrefix =
            DFSConfigKeys.DFS_USER_HOME_DIR_PREFIX_DEFAULT;

    DFSClient dfs;
    private boolean verifyChecksum = true;

    static{
        HdfsConfiguration.init();
    }

    public DistributedFileSystem() {
    }

    @Override
    public URI getUri() {
        return null;
    }

    @Override
    public FSDataInputStream open(Path f, int bufferSize) throws IOException {
        return null;
    }

    @Override
    public FSDataOutputStream create(Path f, FsPermission permission, boolean overwrite, int bufferSize, short replication, long blockSize, Progressable progress) throws IOException {
        return null;
    }

    @Override
    public FSDataOutputStream append(Path f, int bufferSize, Progressable progress) throws IOException {
        return null;
    }

    @SuppressWarnings("deprecation")
    @Override
    public boolean rename(Path src, Path dst) throws IOException {
        statistics.incrementWriteOps(1);

        final Path absSrc = fixRelativePart(src);
        final Path absDst = fixRelativePart(dst);

        // Try the rename without resolving first
        try {
            return dfs.rename(getPathName(absSrc), getPathName(absDst));
        } catch (UnresolvedLinkException e) {
            // Fully resolve the source
            final Path source = getFileLinkStatus(absSrc).getPath();
            // Keep trying to resolve the destination
            return new FileSystemLinkResolver<Boolean>() {
                @Override
                public Boolean doCall(final Path p)
                        throws IOException, UnresolvedLinkException {
                    return dfs.rename(getPathName(source), getPathName(p));
                }
                @Override
                public Boolean next(final FileSystem fs, final Path p)
                        throws IOException {
                    // Should just throw an error in FileSystem#checkPath
                    return doCall(p);
                }
            }.resolve(this, absDst);
        }
    }

    /**
     * This rename operation is guaranteed to be atomic.
     */
    @SuppressWarnings("deprecation")
    @Override
    public void rename(Path src, Path dst, final Options.Rename... options)
            throws IOException {
        statistics.incrementWriteOps(1);
        final Path absSrc = fixRelativePart(src);
        final Path absDst = fixRelativePart(dst);
        // Try the rename without resolving first
        try {
            dfs.rename(getPathName(absSrc), getPathName(absDst), options);
        } catch (UnresolvedLinkException e) {
            // Fully resolve the source
            final Path source = getFileLinkStatus(absSrc).getPath();
            // Keep trying to resolve the destination
            new FileSystemLinkResolver<Void>() {
                @Override
                public Void doCall(final Path p)
                        throws IOException, UnresolvedLinkException {
                    dfs.rename(getPathName(source), getPathName(p), options);
                    return null;
                }
                @Override
                public Void next(final FileSystem fs, final Path p)
                        throws IOException {
                    // Should just throw an error in FileSystem#checkPath
                    return doCall(p);
                }
            }.resolve(this, absDst);
        }
    }

    @Override
    public boolean delete(Path f, boolean recursive) throws IOException {
        return false;
    }

    @Override
    public FileStatus[] listStatus(Path f) throws FileNotFoundException, IOException {
        return new FileStatus[0];
    }

    @Override
    public void setWorkingDirectory(Path new_dir) {

    }

    @Override
    public Path getWorkingDirectory() {
        return null;
    }

    @Override
    public boolean mkdirs(Path f, FsPermission permission) throws IOException {
        return false;
    }

    @Override
    public FileStatus getFileStatus(Path f) throws IOException {
        return null;
    }

    /**
     * Checks that the passed URI belongs to this filesystem and returns
     * just the path component. Expects a URI with an absolute path.
     *
     * @param file URI with absolute path
     * @return path component of {file}
     * @throws IllegalArgumentException if URI does not belong to this DFS
     */
    private String getPathName(Path file) {
        checkPath(file);
        String result = file.toUri().getPath();
        if (!DFSUtil.isValidName(result)) {
            throw new IllegalArgumentException("Pathname " + result + " from " +
                    file+" is not a valid DFS filename.");
        }
        return result;
    }
}
