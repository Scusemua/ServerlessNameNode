package io.hops.metadata.adaptor;

import io.hops.exception.StorageException;
import io.hops.metadata.DalAdaptor;
import io.hops.metadata.hdfs.dal.PendingBlockDataAccess;
import io.hops.metadata.hdfs.entity.PendingBlockInfo;

import java.util.Collection;
import java.util.List;

public class PendingBlockInfoDALAdaptor extends
        DalAdaptor<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo, PendingBlockInfo>
        implements
        PendingBlockDataAccess<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> {

    private final PendingBlockDataAccess<PendingBlockInfo> dataAccces;

    public PendingBlockInfoDALAdaptor(
            PendingBlockDataAccess<PendingBlockInfo> dataAccess) {
        this.dataAccces = dataAccess;
    }

    @Override
    public List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> findByTimeLimitLessThan(
            long timeLimit) throws StorageException {
        return (List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByTimeLimitLessThan(timeLimit));
    }

    @Override
    public List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> findAll()
            throws StorageException {
        return (List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findAll());
    }

    @Override
    public org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo findByBlockAndInodeIds(
            long blockId, long inodeId) throws StorageException {
        return convertDALtoHDFS(dataAccces.findByBlockAndInodeIds(blockId, inodeId));
    }

    @Override
    public int countValidPendingBlocks(long timeLimit) throws StorageException {
        return dataAccces.countValidPendingBlocks(timeLimit);
    }

    @Override
    public void prepare(
            Collection<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> removed,
            Collection<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> newed,
            Collection<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> modified)
            throws StorageException {
        dataAccces.prepare(convertHDFStoDAL(removed), convertHDFStoDAL(newed),
                convertHDFStoDAL(modified));
    }

    @Override
    public void removeAll() throws StorageException {
        dataAccces.removeAll();
    }

    @Override
    public PendingBlockInfo convertHDFStoDAL(
            org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo hdfsClass)
            throws StorageException {
        if (hdfsClass != null) {
            return new PendingBlockInfo(hdfsClass.getBlockId(),
                    hdfsClass.getInodeId(), hdfsClass.getTimeStamp(),
                    hdfsClass.getTargets());
        } else {
            return null;
        }
    }

    @Override
    public org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo convertDALtoHDFS(
            PendingBlockInfo dalClass) throws StorageException {
        if (dalClass != null) {
            return new org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo(
                    dalClass.getBlockId(), dalClass.getInodeId(), dalClass.getTimeStamp(),
                    dalClass.getTargets());
        } else {
            return null;
        }
    }

    @Override
    public List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> findByINodeId(
            long inodeId) throws StorageException {
        return (List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByINodeId(inodeId));
    }

    @Override
    public List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo> findByINodeIds(
            long[] inodeIds) throws StorageException {
        return (List<org.apache.hadoop.hdfs.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByINodeIds(inodeIds));
    }
}

