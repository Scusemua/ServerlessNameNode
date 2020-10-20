package com.gmail.benrcarver.serverlessnamenode.util;

/**
 * *********************************
 * Some handy internal HDFS constants
 * <p/>
 * **********************************
 */
public final class HdfsServerConstants {
    /**
     * Defines the NameNode role.
     */
    static public enum NamenodeRole {
        NAMENODE("NameNode"),
        BACKUP("Backup Node"),
        CHECKPOINT("Checkpoint Node"),
        //START_HOPE_CODE
        LEADER_NAMENODE("Leader Node"),
        // FIXME. remove the all three above roles i.e. name node, backup and checkpoint roles
        SECONDARY("Secondary Node");

        private String description = null;

        private NamenodeRole(String arg) {
            this.description = arg;
        }

        @Override
        public String toString() {
            return description;
        }
    }

    //
    // Timeouts, constants
    //
    public static final long LEASE_SOFTLIMIT_PERIOD = 60 * 1000;
    public static final long LEASE_HARDLIMIT_PERIOD = 60 * LEASE_SOFTLIMIT_PERIOD;
    public static final long LEASE_RECOVER_PERIOD = 10 * 1000; // in ms

    // We need to limit the length and depth of a path in the filesystem.
    // HADOOP-438
    // Currently we set the maximum length to 8k characters and the maximum depth
    // to 1k.
    public static int MAX_PATH_LENGTH = 3000;
    // HOP: it also mean that a there could be a file name 7999 char long e.g. /very_long_file........name.txt
    // The column that holds the file name and symlink name are varchar(128).
    // It is not possible to set it to 8000 as the NDB engine only supports max varchar width of 3072.
    // For now I am setting it to 3000. In NDB 7.X varchar behaves just like varchar in MyISAM
    public static int MAX_PATH_DEPTH = 1000;
}
