package org.apache.hadoop.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.util.EnumCounters;

/**
 * The content types such as file, directory and symlink to be computed.
 */
public enum Content {
    /** The number of files. */
    FILE,
    /** The number of directories. */
    DIRECTORY,
    /** The number of symlinks. */
    SYMLINK,

    /** The total of file length in bytes. */
    LENGTH,
    /** The total of disk space usage in bytes including replication. */
    DISKSPACE,

    /** The number of snapshots. */
    SNAPSHOT,
    /** The number of snapshottable directories. */
    SNAPSHOTTABLE_DIRECTORY;

    /** Content counts. */
    public static class Counts extends EnumCounters<Content> {
        public static Counts newInstance() {
            return new Counts();
        }

        private Counts() {
            super(Content.class);
        }
    }

    private static final EnumCounters.Factory<Content, Counts> FACTORY
            = new EnumCounters.Factory<Content, Counts>() {
        @Override
        public Counts newInstance() {
            return Counts.newInstance();
        }
    };

    /** A map of counters for the current state and the snapshots. */
    public static class CountsMap
            extends EnumCounters.Map<CountsMap.Key, Content, Counts> {
        /** The key type of the map. */
        public static enum Key { CURRENT, SNAPSHOT }

        CountsMap() {
            super(FACTORY);
        }
    }
}
