package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.util.EnumCounters;
import org.apache.hadoop.fs.StorageType;

/**
 * The counter to be computed for content types such as file, directory and symlink,
 * and the storage type usage such as SSD, DISK, ARCHIVE.
 */
public class ContentCounts {
    private EnumCounters<Content> contents;
    private EnumCounters<StorageType> types;

    public static class Builder {
        private EnumCounters<Content> contents;
        // storage spaces used by corresponding storage types
        private EnumCounters<StorageType> types;

        public Builder() {
            contents = new EnumCounters<Content>(Content.class);
            types = new EnumCounters<StorageType>(StorageType.class);
        }

        public Builder file(long file) {
            contents.set(Content.FILE, file);
            return this;
        }

        public Builder directory(long directory) {
            contents.set(Content.DIRECTORY, directory);
            return this;
        }

        public Builder symlink(long symlink) {
            contents.set(Content.SYMLINK, symlink);
            return this;
        }

        public Builder length(long length) {
            contents.set(Content.LENGTH, length);
            return this;
        }

        public Builder storagespace(long storagespace) {
            contents.set(Content.DISKSPACE, storagespace);
            return this;
        }

        public Builder snapshot(long snapshot) {
            contents.set(Content.SNAPSHOT, snapshot);
            return this;
        }

        public Builder snapshotable_directory(long snapshotable_directory) {
            contents.set(Content.SNAPSHOTTABLE_DIRECTORY, snapshotable_directory);
            return this;
        }

        public ContentCounts build() {
            return new ContentCounts(contents, types);
        }
    }

    private ContentCounts(EnumCounters<Content> contents,
                          EnumCounters<StorageType> types) {
        this.contents = contents;
        this.types = types;
    }

    // Get the number of files.
    public long getFileCount() {
        return contents.get(Content.FILE);
    }

    // Get the number of directories.
    public long getDirectoryCount() {
        return contents.get(Content.DIRECTORY);
    }

    // Get the number of symlinks.
    public long getSymlinkCount() {
        return contents.get(Content.SYMLINK);
    }

    // Get the total of file length in bytes.
    public long getLength() {
        return contents.get(Content.LENGTH);
    }

    // Get the total of storage space usage in bytes including replication.
    public long getStoragespace() {
        return contents.get(Content.DISKSPACE);
    }

    // Get the number of snapshots
    public long getSnapshotCount() {
        return contents.get(Content.SNAPSHOT);
    }

    // Get the number of snapshottable directories.
    public long getSnapshotableDirectoryCount() {
        return contents.get(Content.SNAPSHOTTABLE_DIRECTORY);
    }

    public long[] getTypeSpaces() {
        return types.asArray();
    }

    public long getTypeSpace(StorageType t) {
        return types.get(t);
    }

    public void addContent(Content c, long val) {
        contents.add(c, val);
    }

    public void addContents(ContentCounts that) {
        contents.add(that.contents);
        types.add(that.types);
    }

    public void addTypeSpace(StorageType t, long val) {
        types.add(t, val);
    }

    public void addTypeSpaces(EnumCounters<StorageType> that) {
        this.types.add(that);
    }
}
