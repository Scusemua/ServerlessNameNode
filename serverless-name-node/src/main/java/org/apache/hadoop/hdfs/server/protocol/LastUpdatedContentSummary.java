package org.apache.hadoop.hdfs.server.protocol;

public class LastUpdatedContentSummary {

    private final long fileAndDirCount;
    private final long spaceConsumed;
    private final long nsQuota;
    private final long dsQuota;

    public LastUpdatedContentSummary(long fileAndDirCount, long spaceConsumed,
                                     long nsQuota, long dsQuota) {
        this.fileAndDirCount = fileAndDirCount;
        this.spaceConsumed = spaceConsumed;
        this.nsQuota = nsQuota;
        this.dsQuota = dsQuota;
    }

    public long getFileAndDirCount() {
        return fileAndDirCount;
    }

    public long getSpaceConsumed() {
        return spaceConsumed;
    }

    public long getNsQuota() {
        return nsQuota;
    }

    public long getDsQuota() {
        return dsQuota;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LastUpdatedContentSummary)) {
            return false;
        }

        LastUpdatedContentSummary that = (LastUpdatedContentSummary) o;

        if (fileAndDirCount != that.fileAndDirCount) {
            return false;
        }
        if (spaceConsumed != that.spaceConsumed) {
            return false;
        }
        if (nsQuota != that.nsQuota) {
            return false;
        }
        return dsQuota == that.dsQuota;

    }

    @Override
    public int hashCode() {
        int result = (int) (fileAndDirCount ^ (fileAndDirCount >>> 32));
        result = 31 * result + (int) (spaceConsumed ^ (spaceConsumed >>> 32));
        result = 31 * result + (int) (nsQuota ^ (nsQuota >>> 32));
        result = 31 * result + (int) (dsQuota ^ (dsQuota >>> 32));
        return result;
    }

}

