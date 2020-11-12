package org.apache.hadoop.hdfs.server.datanode;

/**
 * The caching strategy we should use for an HDFS read or write operation.
 */
public class CachingStrategy {
    private final Boolean dropBehind; // null = use server defaults
    private final Long readahead; // null = use server defaults

    public static CachingStrategy newDefaultStrategy() {
        return new CachingStrategy(null, null);
    }

    public static CachingStrategy newDropBehind() {
        return new CachingStrategy(true, null);
    }

    public static class Builder {
        private Boolean dropBehind;
        private Long readahead;

        public Builder(CachingStrategy prev) {
            this.dropBehind = prev.dropBehind;
            this.readahead = prev.readahead;
        }

        public Builder setDropBehind(Boolean dropBehind) {
            this.dropBehind = dropBehind;
            return this;
        }

        public Builder setReadahead(Long readahead) {
            this.readahead = readahead;
            return this;
        }

        public CachingStrategy build() {
            return new CachingStrategy(dropBehind, readahead);
        }
    }

    public CachingStrategy(Boolean dropBehind, Long readahead) {
        this.dropBehind = dropBehind;
        this.readahead = readahead;
    }

    public Boolean getDropBehind() {
        return dropBehind;
    }

    public Long getReadahead() {
        return readahead;
    }

    public String toString() {
        return "CachingStrategy(dropBehind=" + dropBehind +
                ", readahead=" + readahead + ")";
    }
}
