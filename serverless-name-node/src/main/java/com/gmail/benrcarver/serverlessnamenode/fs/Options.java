package com.gmail.benrcarver.serverlessnamenode.fs;

/**
 * This class contains options related to file system operations.
 */
public final class Options {

    /**
     * Enum to support the varargs for rename() options
     */
    public enum Rename {
        NONE((byte) 0), // No options
        OVERWRITE((byte) 1), // Overwrite the rename destination
        TO_TRASH ((byte) 2),  // Rename to trash
        KEEP_ENCODING_STATUS((byte)3);

        private final byte code;

        private Rename(byte code) {
            this.code = code;
        }

        public static Rename valueOf(byte code) {
            return code < 0 || code >= values().length ? null : values()[code];
        }

        public byte value() {
            return code;
        }
    }
}
