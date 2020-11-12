package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.server.blockmanagement.BlockStoragePolicySuite;
import com.google.common.base.Preconditions;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

@InterfaceAudience.Private
@InterfaceStability.Unstable
public class ContentSummaryComputationContext {
    private FSDirectory dir = null;
    private FSNameSystem fsn = null;
    private BlockStoragePolicySuite bsps = null;
    private ContentCounts counts = null;
    private long nextCountLimit = 0;
    private long limitPerRun = 0;
    private long yieldCount = 0;
    private long sleepMilliSec = 0;
    private int sleepNanoSec = 0;

    /**
     * Constructor
     *
     * @param dir The FSDirectory instance
     * @param fsn The FSNamesystem instance
     * @param limitPerRun allowed number of operations in one
     *        locking period. 0 or a negative number means
     *        no limit (i.e. no yielding)
     */
    public ContentSummaryComputationContext(FSDirectory dir,
                                            FSNameSystem fsn, long limitPerRun, long sleepMicroSec) {
        this.dir = dir;
        this.fsn = fsn;
        this.limitPerRun = limitPerRun;
        this.nextCountLimit = limitPerRun;
        this.counts = new ContentCounts.Builder().build();
        this.sleepMilliSec = sleepMicroSec/1000;
        this.sleepNanoSec = (int)((sleepMicroSec%1000)*1000);
    }

    /** Constructor for blocking computation. */
    public ContentSummaryComputationContext(BlockStoragePolicySuite bsps) {
        this(null, null, 0, 1000);
        this.bsps = bsps;
    }

    /** Return current yield count */
    public long getYieldCount() {
        return yieldCount;
    }

    //HOPS this is not very relevant as we do a subtree operation instead
    //but this may be a source of inspiration to not hit to hard on the database when doing subtree operations
    /**
     * Relinquish locks held during computation for a short while
     * and reacquire them. This will give other threads a chance
     * to acquire the contended locks and run.
     *
     * @return true if locks were released and reacquired.
     */
    public boolean yield() {
        // Are we set up to do this?
        if (limitPerRun <= 0 || dir == null || fsn == null) {
            return false;
        }

        // Have we reached the limit?
        long currentCount = counts.getFileCount() +
                counts.getSymlinkCount() +
                counts.getDirectoryCount() +
                counts.getSnapshotableDirectoryCount();
        if (currentCount <= nextCountLimit) {
            return false;
        }

        // Update the next limit
        nextCountLimit = currentCount + limitPerRun;

//    boolean hadDirReadLock = dir.hasReadLock();
//    boolean hadDirWriteLock = dir.hasWriteLock();
//    boolean hadFsnReadLock = fsn.hasReadLock();
//    boolean hadFsnWriteLock = fsn.hasWriteLock();
//
//    // sanity check.
//    if (!hadDirReadLock || !hadFsnReadLock || hadDirWriteLock ||
//        hadFsnWriteLock || dir.getReadHoldCount() != 1 ||
//        fsn.getReadHoldCount() != 1) {
//      // cannot relinquish
//      return false;
//    }
//
//    // unlock
//    dir.readUnlock();
//    fsn.readUnlock();

        try {
            Thread.sleep(sleepMilliSec, sleepNanoSec);
        } catch (InterruptedException ie) {
        } finally {
            // reacquire
//      fsn.readLock();
//      dir.readLock();
        }
        yieldCount++;
        return true;
    }

    /** Get the content counts */
    public ContentCounts getCounts() {
        return counts;
    }

    public BlockStoragePolicySuite getBlockStoragePolicySuite() {
        Preconditions.checkState((bsps != null || fsn != null),
                "BlockStoragePolicySuite must be either initialized or available via" +
                        " FSNameSystem");
        return (bsps != null) ? bsps:
                fsn.getBlockManager().getStoragePolicySuite();
    }
}
