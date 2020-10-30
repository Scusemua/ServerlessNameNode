package io.hops.metadata.adaptor;

import io.hops.exception.StorageException;
import io.hops.metadata.DalAdaptor;
import io.hops.metadata.hdfs.dal.PendingBlockDataAccess;
import io.hops.metadata.hdfs.entity.PendingBlockInfo;

import java.util.Collection;
import java.util.List;

public class PendingBlockInfoDALAdaptor extends
        DalAdaptor<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo, PendingBlockInfo>
        implements
        PendingBlockDataAccess<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> {

    private final PendingBlockDataAccess<PendingBlockInfo> dataAccces;

    public PendingBlockInfoDALAdaptor(
            PendingBlockDataAccess<PendingBlockInfo> dataAccess) {
        this.dataAccces = dataAccess;
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> findByTimeLimitLessThan(
            long timeLimit) throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByTimeLimitLessThan(timeLimit));
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> findAll()
            throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findAll());
    }

    @Override
    public com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo findByBlockAndInodeIds(
            long blockId, long inodeId) throws StorageException {
        return convertDALtoHDFS(dataAccces.findByBlockAndInodeIds(blockId, inodeId));
    }

    @Override
    public int countValidPendingBlocks(long timeLimit) throws StorageException {
        return dataAccces.countValidPendingBlocks(timeLimit);
    }

    @Override
    public void prepare(
            Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> removed,
            Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> newed,
            Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> modified)
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
            com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo hdfsClass)
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
    public com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo convertDALtoHDFS(
            PendingBlockInfo dalClass) throws StorageException {
        if (dalClass != null) {
            return new com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo(
                    dalClass.getBlockId(), dalClass.getInodeId(), dalClass.getTimeStamp(),
                    dalClass.getTargets());
        } else {
            return null;
        }
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> findByINodeId(
            long inodeId) throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByINodeId(inodeId));
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo> findByINodeIds(
            long[] inodeIds) throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.PendingBlockInfo>) convertDALtoHDFS(
                dataAccces.findByINodeIds(inodeIds));
    }
}

