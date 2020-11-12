package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.util.EnumCounters;

/** Quota types. */
public enum Quota {
    /** The namespace usage, i.e. the number of name objects. */
    NAMESPACE,
    /** The storage space usage in bytes including replication. */
    STORAGESPACE;

    /** Counters for quota counts. */
    public static class Counts extends EnumCounters<Quota> {
        /** @return a new counter with the given namespace and storagespace usages. */
        public static Counts newInstance(long namespace, long storagespace) {
            final Counts c = new Counts();
            c.set(NAMESPACE, namespace);
            c.set(STORAGESPACE, storagespace);
            return c;
        }

        public static Counts newInstance() {
            return newInstance(0, 0);
        }

        Counts() {
            super(Quota.class);
        }
    }

    /**
     * Is quota violated?
     * The quota is violated if quota is set and usage > quota.
     */
    static boolean isViolated(final long quota, final long usage) {
        return quota >= 0 && usage > quota;
    }

    /**
     * Is quota violated?
     * The quota is violated if quota is set, delta > 0 and usage + delta > quota.
     */
    static boolean isViolated(final long quota, final long usage,
                              final long delta) {
        return quota >= 0 && delta > 0 && usage > quota - delta;
    }
}