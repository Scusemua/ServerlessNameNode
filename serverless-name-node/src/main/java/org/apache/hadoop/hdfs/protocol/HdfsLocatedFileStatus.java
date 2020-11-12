package org.apache.hadoop.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtilClient;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.fs.FileEncryptionInfo;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.permission.FsPermission;

import java.io.IOException;
import java.net.URI;

/**
 * Interface that represents the over the wire information
 * including block locations for a file.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class HdfsLocatedFileStatus extends HdfsFileStatus {
    private LocatedBlocks locations;

    /**
     * Constructor
     *
     * @param length size
     * @param isdir if this is directory
     * @param block_replication the file's replication factor
     * @param blocksize the file's block size
     * @param modification_time most recent modification time
     * @param access_time most recent access time
     * @param permission permission
     * @param owner owner
     * @param group group
     * @param symlink symbolic link
     * @param path local path name in java UTF8 format
     * @param fileId the file id
     * @param locations block locations
     * @param feInfo file encryption info
     */
    public HdfsLocatedFileStatus(long length, boolean isdir,
                                 int block_replication, long blocksize, long modification_time,
                                 long access_time, FsPermission permission, String owner, String group,
                                 byte[] symlink, byte[] path, long fileId, LocatedBlocks locations,
                                 int childrenNum, FileEncryptionInfo feInfo, byte storagePolicy) {
        super(length, isdir, block_replication, blocksize, modification_time,
                access_time, permission, owner, group, symlink, path, fileId, childrenNum, feInfo, storagePolicy);
        this.locations = locations;
    }

    public LocatedBlocks getBlockLocations() {
        return locations;
    }

    /**
     * This function is used to transform the underlying HDFS LocatedBlocks to
     * BlockLocations.
     *
     * The returned BlockLocation will have different formats for replicated
     * and erasure coded file.
     * Please refer to
     * {@link org.apache.hadoop.fs.FileSystem#getFileBlockLocations
     * (FileStatus, long, long)}
     * for examples.
     */
    public final LocatedFileStatus makeQualifiedLocated(URI defaultUri, Path path)
            throws IOException {
        return new LocatedFileStatus(getLen(), isDir(), getReplication(),
                getBlockSize(), getModificationTime(),
                getAccessTime(),
                getPermission(), getOwner(), getGroup(),
                isSymlink() ? new Path(getSymlink()) : null,
                (getFullPath(path)).makeQualified(
                        defaultUri, null), // fully-qualify path
                DFSUtilClient.locatedBlocks2Locations(getBlockLocations()));
    }

}
