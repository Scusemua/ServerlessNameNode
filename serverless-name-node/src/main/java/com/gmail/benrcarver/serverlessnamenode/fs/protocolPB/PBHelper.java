package com.gmail.benrcarver.serverlessnamenode.fs.protocolPB;

import com.gmail.benrcarver.serverlessnamenode.fs.FileStatus;
import com.gmail.benrcarver.serverlessnamenode.fs.Path;
import com.gmail.benrcarver.serverlessnamenode.fs.permission.FsPermission;
import com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos;

import java.io.IOException;

/**
 * Utility methods aiding conversion of fs data structures.
 */
public final class PBHelper {
    private PBHelper() {
        // prevent construction
    }

    public static FsPermission convert(HdfsProtos.FsPermissionProto proto)
            throws IOException {
        return new FsPermission((short)proto.getPerm());
    }

    public static HdfsProtos.FsPermissionProto convert(FsPermission p) throws IOException {
        HdfsProtos.FsPermissionProto.Builder bld = HdfsProtos.FsPermissionProto.newBuilder();
        bld.setPerm(p.toShort());
        return bld.build();
    }

    public static FileStatus convert(FileStatusProto proto) throws IOException {
        final Path path;
        final long length;
        final boolean isdir;
        final short blockReplication;
        final long blocksize;
        final long mtime;
        final long atime;
        final String owner;
        final String group;
        final FsPermission permission;
        final Path symlink;
        switch (proto.getFileType()) {
            case FT_DIR:
                isdir = true;
                symlink = null;
                blocksize = 0;
                length = 0;
                blockReplication = 0;
                break;
            case FT_SYMLINK:
                isdir = false;
                symlink = new Path(proto.getSymlink());
                blocksize = 0;
                length = 0;
                blockReplication = 0;
                break;
            case FT_FILE:
                isdir = false;
                symlink = null;
                blocksize = proto.getBlockSize();
                length = proto.getLength();
                int brep = proto.getBlockReplication();
                if ((brep & 0xffff0000) != 0) {
                    throw new IOException(String.format("Block replication 0x%08x " +
                            "doesn't fit in 16 bits.", brep));
                }
                blockReplication = (short)brep;
                break;
            default:
                throw new IllegalStateException("Unknown type: " + proto.getFileType());
        }
        path = new Path(proto.getPath());
        mtime = proto.getModificationTime();
        atime = proto.getAccessTime();
        permission = convert(proto.getPermission());
        owner = proto.getOwner();
        group = proto.getGroup();
        int flags = proto.getFlags();
        FileStatus fileStatus = new FileStatus(length, isdir, blockReplication,
                blocksize, mtime, atime, permission, owner, group, symlink, path,
                FileStatus.attributes(
                        (flags & FileStatusProto.Flags.HAS_ACL_VALUE) != 0,
                        (flags & FileStatusProto.Flags.HAS_CRYPT_VALUE) != 0,
                        (flags & FileStatusProto.Flags.HAS_EC_VALUE) != 0,
                        (flags & FileStatusProto.Flags.SNAPSHOT_ENABLED_VALUE) != 0));
        return fileStatus;
    }

    public static FileStatusProto convert(FileStatus stat) throws IOException {
        FileStatusProto.Builder bld = FileStatusProto.newBuilder();
        bld.setPath(stat.getPath().toString());
        if (stat.isDirectory()) {
            bld.setFileType(FileStatusProto.FileType.FT_DIR);
        } else if (stat.isSymlink()) {
            bld.setFileType(FileStatusProto.FileType.FT_SYMLINK)
                    .setSymlink(stat.getSymlink().toString());
        } else {
            bld.setFileType(FileStatusProto.FileType.FT_FILE)
                    .setLength(stat.getLen())
                    .setBlockReplication(stat.getReplication())
                    .setBlockSize(stat.getBlockSize());
        }
        bld.setAccessTime(stat.getAccessTime())
                .setModificationTime(stat.getModificationTime())
                .setOwner(stat.getOwner())
                .setGroup(stat.getGroup())
                .setPermission(convert(stat.getPermission()));
        int flags = 0;
        flags |= stat.hasAcl()         ? FileStatusProto.Flags.HAS_ACL_VALUE   : 0;
        flags |= stat.isEncrypted()    ? FileStatusProto.Flags.HAS_CRYPT_VALUE : 0;
        flags |= stat.isErasureCoded() ? FileStatusProto.Flags.HAS_EC_VALUE    : 0;
        flags |= stat.isSnapshotEnabled() ? FileStatusProto.Flags
                .SNAPSHOT_ENABLED_VALUE : 0;
        bld.setFlags(flags);
        return bld.build();
    }

}