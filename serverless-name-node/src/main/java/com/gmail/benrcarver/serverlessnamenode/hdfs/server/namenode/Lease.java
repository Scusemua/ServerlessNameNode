package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.Block;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.metadata.common.CounterType;
import io.hops.metadata.common.FinderType;
import io.hops.metadata.hdfs.entity.LeasePath;
import io.hops.transaction.EntityManager;

import java.util.Collection;

/**
 * **********************************************************
 * A Lease governs all the locks held by a single client. For each client
 * there's a corresponding lease, whose timestamp is updated when the client
 * periodically checks in. If the client dies and allows its lease to expire,
 * all the corresponding locks can be released.
 * ***********************************************************
 */
public class Lease implements Comparable<Lease> {

    public static enum Counter implements CounterType<Lease> {

        All;

        @Override
        public Class getType() {
            return Lease.class;
        }
    }

    public static enum Finder implements FinderType<Lease> {

        ByHolder,
        ByHolderId,
        All;

        @Override
        public Class getType() {
            return Lease.class;
        }

        @Override
        public Annotation getAnnotated() {
            switch (this) {
                case ByHolder:
                    return Annotation.PrimaryKey;
                case ByHolderId:
                    return Annotation.PrunedIndexScan;
                case All:
                    return Annotation.FullTable;
                default:
                    throw new IllegalStateException();
            }
        }

    }

    private final String holder;
    private long lastUpdate;
    private int holderID;
    private int count;

    public Lease(String holder, int holderID, long lastUpd, int count) {
        this.holder = holder;
        this.holderID = holderID;
        this.lastUpdate = lastUpd;
        this.count = count;
    }

    public void setLastUpdate(long lastUpd) {
        this.lastUpdate = lastUpd;
    }

    public long getLastUpdate() {
        return this.lastUpdate;
    }

    public void setHolderID(int holderID) {
        this.holderID = holderID;
    }

    public int getHolderID() {
        return this.holderID;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean removePath(LeasePath lPath)
            throws StorageException, TransactionContextException {
        if( getPaths().remove(lPath) ){
            assert count > 0;
            count--;
            savePersistent();
            return  true;
        } else {
            return false;
        }
    }

    public void addPath(LeasePath lPath)
            throws StorageException, TransactionContextException {
        EntityManager.update(lPath);
        lPath.savePersistent();
        count++;
        savePersistent();
    }

    /**
     * Does this lease contain any path?
     */
    boolean hasPath() throws StorageException, TransactionContextException {
        return !(this.getPaths().isEmpty() && count == 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "[Lease.  Holder: " + holder + ", pendingcreates: " + count + "]";
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int compareTo(Lease o) {
        Lease l1 = this;
        Lease l2 = o;
        long lu1 = l1.lastUpdate;
        long lu2 = l2.lastUpdate;
        if (lu1 < lu2) {
            return -1;
        } else if (lu1 > lu2) {
            return 1;
        } else {
            return l1.holder.compareTo(l2.holder);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Lease)) {
            return false;
        }
        Lease obj = (Lease) o;
        if (lastUpdate == obj.lastUpdate && holder.equals(obj.holder)) {
            return true;
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return holder.hashCode();
    }

    public Collection<LeasePath> getPaths()
            throws StorageException, TransactionContextException {
        return EntityManager.findList(LeasePath.Finder.ByHolderId, holderID);
    }

    public String getHolder() {
        return holder;
    }

//  void replacePath(LeasePath oldpath, LeasePath newpath)
//      throws StorageException, TransactionContextException {
//    getPaths().remove(oldpath);
//    getPaths().add(newpath);
//  }

    public static int getHolderId(String holder){
        return holder.hashCode();
    }


    public void updateLastTwoBlocksInLeasePath(String path, Block
            lastBlock, Block penultimateBlock)
            throws TransactionContextException, StorageException {
        updateLastTwoBlocksInLeasePath(path, lastBlock == null ? -1 : lastBlock
                .getBlockId(), penultimateBlock == null ? -1 : penultimateBlock.getBlockId());
    }

    private void updateLastTwoBlocksInLeasePath(String path, long lastBlockId, long
            penultimateBlockId)
            throws TransactionContextException, StorageException {
        Collection<LeasePath> lps = getPaths();
        for(LeasePath lp : lps){
            if(lp.getPath().equals(path)){
                lp.setLastBlockId(lastBlockId);
                lp.setPenultimateBlockId(penultimateBlockId);
                lp.savePersistent();
                break;
            }
        }
    }

    public LeasePath getLeasePath(String path)
            throws TransactionContextException, StorageException {
        Collection<LeasePath> lps = getPaths();
        for(LeasePath lp : lps){
            if(lp.getPath().equals(path)){
                return lp;
            }
        }
        return null;
    }

    public void deletePersistent() throws TransactionContextException, StorageException {
        EntityManager.remove(this);
    }

    public void savePersistent() throws TransactionContextException, StorageException {
        EntityManager.update(this);
    }
}