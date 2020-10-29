package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import com.gmail.benrcarver.serverlessnamenode.exceptions.QuotaExceededException;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Abstract class for deriving exceptions related to filesystem constraints
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public abstract class FSLimitException extends QuotaExceededException {
    protected static final long serialVersionUID = 1L;

    protected FSLimitException() {
    }

    protected FSLimitException(String msg) {
        super(msg);
    }

    protected FSLimitException(long quota, long count) {
        super(quota, count);
    }

    /**
     * Path component length is too long
     */
    public static final
    class PathComponentTooLongException extends FSLimitException {
        protected static final long serialVersionUID = 1L;

        private String childName;

        protected PathComponentTooLongException() {}

        protected PathComponentTooLongException(String msg) {
            super(msg);
        }

        public PathComponentTooLongException(long quota, long count,
                                             String parentPath, String childName) {
            super(quota, count);
            setPathName(parentPath);
            this.childName = childName;
        }

        String getParentPath() {
            return pathName;
        }

        @Override
        public String getMessage() {
            return "The maximum path component name limit of " + childName +
                    " in directory " + getParentPath() +
                    " is exceeded: limit=" + quota + " length=" + count;
        }
    }

    /**
     * Directory has too many items
     */
    public static final class MaxDirectoryItemsExceededException
            extends FSLimitException {
        protected static final long serialVersionUID = 1L;

        protected MaxDirectoryItemsExceededException() {
        }

        protected MaxDirectoryItemsExceededException(String msg) {
            super(msg);
        }

        public MaxDirectoryItemsExceededException(long quota, long count) {
            super(quota, count);
        }

        @Override
        public String getMessage() {
            return "The directory item limit of " + pathName +
                    " is exceeded: limit=" + quota + " items=" + count;
        }
    }
}
