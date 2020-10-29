package com.gmail.benrcarver.serverlessnamenode.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.util.EnumCounters;
import org.apache.hadoop.fs.StorageType;

/**
 * Counters for namespace, storage space and storage type space quota and usage.
 */
public class QuotaCounts {
    // Name space and storage space counts (HDFS-7775 refactors the original disk
    // space count to storage space counts)
    private EnumCounters<Quota> nsSsCounts;
    // Storage type space counts
    private EnumCounters<StorageType> tsCounts;

    public static class Builder {
        private EnumCounters<Quota> nsSsCounts;
        private EnumCounters<StorageType> tsCounts;

        public Builder() {
            this.nsSsCounts = new EnumCounters<Quota>(Quota.class);
            this.tsCounts = new EnumCounters<StorageType>(StorageType.class);
        }

        public Builder nameSpace(long val) {
            this.nsSsCounts.set(Quota.NAMESPACE, val);
            return this;
        }

        public Builder storageSpace(long val) {
            this.nsSsCounts.set(Quota.STORAGESPACE, val);
            return this;
        }

        public Builder typeSpaces(EnumCounters<StorageType> val) {
            if (val != null) {
                this.tsCounts.set(val);
            }
            return this;
        }

        public Builder typeSpaces(long val) {
            this.tsCounts.reset(val);
            return this;
        }

        public Builder quotaCount(QuotaCounts that) {
            this.nsSsCounts.set(that.nsSsCounts);
            this.tsCounts.set(that.tsCounts);
            return this;
        }

        public QuotaCounts build() {
            return new QuotaCounts(this);
        }
    }

    private QuotaCounts(Builder builder) {
        this.nsSsCounts = builder.nsSsCounts;
        this.tsCounts = builder.tsCounts;
    }

    public void add(QuotaCounts that) {
        this.nsSsCounts.add(that.nsSsCounts);
        this.tsCounts.add(that.tsCounts);
    }

    public void subtract(QuotaCounts that) {
        this.nsSsCounts.subtract(that.nsSsCounts);
        this.tsCounts.subtract(that.tsCounts);
    }

    /**
     * Returns a QuotaCounts whose value is {@code (-this)}.
     *
     * @return {@code -this}
     */
    public QuotaCounts negation() {
        QuotaCounts ret = new QuotaCounts.Builder().quotaCount(this).build();
        ret.nsSsCounts.negation();
        ret.tsCounts.negation();
        return ret;
    }

    public long getNameSpace(){
        return nsSsCounts.get(Quota.NAMESPACE);
    }

    public void setNameSpace(long nameSpaceCount) {
        this.nsSsCounts.set(Quota.NAMESPACE, nameSpaceCount);
    }

    public void addNameSpace(long nsDelta) {
        this.nsSsCounts.add(Quota.NAMESPACE, nsDelta);
    }

    public long getStorageSpace(){
        return nsSsCounts.get(Quota.STORAGESPACE);
    }

    public void setStorageSpace(long spaceCount) {
        this.nsSsCounts.set(Quota.STORAGESPACE, spaceCount);
    }

    public void addStorageSpace(long dsDelta) {
        this.nsSsCounts.add(Quota.STORAGESPACE, dsDelta);
    }

    public EnumCounters<StorageType> getTypeSpaces() {
        EnumCounters<StorageType> ret =
                new EnumCounters<StorageType>(StorageType.class);
        ret.set(tsCounts);
        return ret;
    }

    void setTypeSpaces(EnumCounters<StorageType> that) {
        if (that != null) {
            this.tsCounts.set(that);
        }
    }

    long getTypeSpace(StorageType type) {
        return this.tsCounts.get(type);
    }

    void setTypeSpace(StorageType type, long spaceCount) {
        this.tsCounts.set(type, spaceCount);
    }

    public void addTypeSpace(StorageType type, long delta) {
        this.tsCounts.add(type, delta);
    }

    public boolean anyNsSsCountGreaterOrEqual(long val) {
        return nsSsCounts.anyGreaterOrEqual(val);
    }

    public boolean anyTypeSpaceCountGreaterOrEqual(long val) {
        return tsCounts.anyGreaterOrEqual(val);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || !(obj instanceof QuotaCounts)) {
            return false;
        }
        final QuotaCounts that = (QuotaCounts)obj;
        return this.nsSsCounts.equals(that.nsSsCounts)
                && this.tsCounts.equals(that.tsCounts);
    }

    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42; // any arbitrary constant will do
    }

    @Override
    public String toString(){
        return "nsSpCounts: " + nsSsCounts.toString() + " typeCounts: " + tsCounts.toString();
    }
}
