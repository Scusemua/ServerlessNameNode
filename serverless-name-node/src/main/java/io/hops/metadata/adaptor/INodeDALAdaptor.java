package io.hops.metadata.adaptor;

import com.gmail.benrcarver.serverlessnamenode.hdfs.DFSUtil;
import com.gmail.benrcarver.serverlessnamenode.server.namenode.*;
import io.hops.exception.StorageException;
import io.hops.metadata.DalAdaptor;
import io.hops.metadata.hdfs.dal.INodeDataAccess;
import io.hops.metadata.hdfs.entity.INode;
import io.hops.metadata.hdfs.entity.INodeIdentifier;
import io.hops.metadata.hdfs.entity.INodeMetadataLogEntry;
import io.hops.metadata.hdfs.entity.ProjectedINode;
import io.hops.security.GroupNotFoundException;
import io.hops.security.UserNotFoundException;
import io.hops.security.UsersGroups;
import io.hops.transaction.context.EntityContext;
import org.apache.hadoop.fs.permission.FsPermission;
import org.apache.hadoop.fs.permission.PermissionStatus;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class INodeDALAdaptor
        extends DalAdaptor<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode, INode>
        implements INodeDataAccess<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> {

    private INodeDataAccess<INode> dataAccess;

    public INodeDALAdaptor(INodeDataAccess<INode> dataAccess) {
        this.dataAccess = dataAccess;
    }

    @Override
    public com.gmail.benrcarver.serverlessnamenode.server.namenode.INode findInodeByIdFTIS(
            long inodeId) throws StorageException {
        return convertDALtoHDFS(dataAccess.findInodeByIdFTIS(inodeId));
    }

    @Override
    public Collection<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> findInodesByIdsFTIS(long[] inodeId) throws
            StorageException {
        return convertDALtoHDFS(dataAccess.findInodesByIdsFTIS(inodeId));
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> findInodesByParentIdFTIS(
            long parentId) throws StorageException {
        List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> list =
                (List) convertDALtoHDFS(
                        dataAccess.findInodesByParentIdFTIS(parentId));
        Collections
                .sort(list, com.gmail.benrcarver.serverlessnamenode.server.namenode.INode.Order.ByName);
        return list;
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> findInodesByParentIdAndPartitionIdPPIS(
            long parentId, long partitionId) throws StorageException {
        List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> list =
                (List) convertDALtoHDFS(
                        dataAccess.findInodesByParentIdAndPartitionIdPPIS(parentId, partitionId));
        Collections
                .sort(list, com.gmail.benrcarver.serverlessnamenode.server.namenode.INode.Order.ByName);
        return list;
    }

    @Override
    public List<ProjectedINode> findInodesPPISTx(
            long parentId, long partitionId, EntityContext.LockMode lock) throws StorageException {
        List<ProjectedINode> list =
                dataAccess.findInodesPPISTx(parentId, partitionId, lock);
        Collections.sort(list);
        return list;
    }

    @Override
    public List<ProjectedINode> findInodesFTISTx(
            long parentId, EntityContext.LockMode lock) throws StorageException {
        List<ProjectedINode> list =
                dataAccess.findInodesFTISTx(parentId, lock);
        Collections.sort(list);
        return list;
    }

    public List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> lockInodesUsingPkBatchTx(
            String[] names, long[] parentIds, long[] partitionIds, EntityContext.LockMode lock)
            throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode>) convertDALtoHDFS(
                dataAccess.lockInodesUsingPkBatchTx(names, parentIds, partitionIds, lock));
    }

    @Override
    public com.gmail.benrcarver.serverlessnamenode.server.namenode.INode findInodeByNameParentIdAndPartitionIdPK(
            String name, long parentId, long partitionId) throws StorageException {
        return convertDALtoHDFS(
                dataAccess.findInodeByNameParentIdAndPartitionIdPK(name, parentId, partitionId));
    }

    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> getINodesPkBatched(
            String[] names, long[] parentIds, long[] partitionIds) throws StorageException {
        return (List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode>) convertDALtoHDFS(
                dataAccess.getINodesPkBatched(names, parentIds, partitionIds));
    }

    @Override
    public void prepare(
            Collection<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> removed,
            Collection<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> newed,
            Collection<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> modified)
            throws StorageException {
        dataAccess.prepare(convertHDFStoDAL(removed), convertHDFStoDAL(newed),
                convertHDFStoDAL(modified));
    }

    @Override
    public int countAll() throws StorageException {
        return dataAccess.countAll();

    }

    @Override
    public List<INodeIdentifier> getAllINodeFiles(long startId, long endId)
            throws StorageException {
        return dataAccess.getAllINodeFiles(startId, endId);
    }

    @Override
    public boolean haveFilesWithIdsGreaterThan(long id) throws StorageException {
        return dataAccess.haveFilesWithIdsGreaterThan(id);
    }

    @Override
    public boolean haveFilesWithIdsBetween(long startId, long endId)
            throws StorageException {
        return dataAccess.haveFilesWithIdsBetween(startId, endId);
    }

    @Override
    public long getMinFileId() throws StorageException {
        return dataAccess.getMinFileId();
    }

    @Override
    public long getMaxFileId() throws StorageException {
        return dataAccess.getMaxFileId();
    }

    @Override
    public int countAllFiles() throws StorageException {
        return dataAccess.countAllFiles();
    }

    @Override
    public boolean hasChildren(long parentId, boolean areChildrenRandomlyPartitioned ) throws StorageException {
        return dataAccess.hasChildren(parentId, areChildrenRandomlyPartitioned);
    }

    //Only for testing
    @Override
    public List<com.gmail.benrcarver.serverlessnamenode.server.namenode.INode> allINodes() throws StorageException {
        return (List) convertDALtoHDFS(dataAccess.allINodes());
    }

    @Override
    public void deleteInode(String name) throws StorageException {
        dataAccess.deleteInode(name);
    }

    @Override
    public void updateLogicalTime(Collection<INodeMetadataLogEntry> logEntries)
            throws StorageException {
        dataAccess.updateLogicalTime(logEntries);
    }

    @Override
    public int countSubtreeLockedInodes() throws StorageException {
        return dataAccess.countSubtreeLockedInodes();
    }

    @Override
    public INode convertHDFStoDAL(
            com.gmail.benrcarver.serverlessnamenode.server.namenode.INode inode)
            throws StorageException {
        INode hopINode = null;
        if (inode != null) {
            hopINode = new INode();
            hopINode.setAccessTime(inode.getAccessTime());
            hopINode.setGroupID(inode.getGroupID());
            hopINode.setId(inode.getId());
            hopINode.setIsDir(inode.isDirectory());
            hopINode.setPartitionId(inode.getPartitionId());
            hopINode.setLogicalTime(inode.getLogicalTime());
            hopINode.setModificationTime(inode.getModificationTime());
            hopINode.setName(inode.getLocalName());
            hopINode.setParentId(inode.getParentId());
            hopINode.setPermission(inode.getFsPermission().toShort());
            if(!inode.isSymlink()){
                hopINode.setStoragePolicyID(inode.getLocalStoragePolicyID());
            }
            hopINode.setSubtreeLocked(inode.isSTOLocked());
            hopINode.setSubtreeLockOwner(inode.getSTOLockOwner());
            hopINode.setUserID(inode.getUserID());

            if (inode.isDirectory()) {
                hopINode.setUnderConstruction(false);
                hopINode.setDirWithQuota(((INodeDirectory) inode).isWithQuota());
                hopINode.setMetaStatus(((INodeDirectory) inode).getMetaStatus());
                hopINode.setChildrenNum(((INodeDirectory) inode).getChildrenNum());
            }
            if (inode instanceof INodeFile) {
                if (inode.isUnderConstruction()) {
                    hopINode.setClientName(((INodeFile) inode).getFileUnderConstructionFeature().getClientName());
                    hopINode.setClientMachine(((INodeFile) inode).getFileUnderConstructionFeature().getClientMachine());
                }
                hopINode.setUnderConstruction(inode.isUnderConstruction());
                hopINode.setDirWithQuota(false);
                hopINode.setHeader(((INodeFile) inode).getHeader());
                hopINode.setGenerationStamp(((INodeFile) inode).getGenerationStamp());
                hopINode.setFileSize(((INodeFile) inode).getSize());
                hopINode.setFileStoredInDB(((INodeFile)inode).isFileStoredInDB());
            }
            if (inode instanceof INodeSymlink) {
                hopINode.setUnderConstruction(false);
                hopINode.setDirWithQuota(false);

                String linkValue = DFSUtil.bytes2String(((INodeSymlink) inode).getSymlink());
                hopINode.setSymlink(linkValue);
            }
            hopINode.setHeader(inode.getHeader());
            hopINode.setNumAces(inode.getNumAces());
            hopINode.setNumUserXAttrs(inode.getNumUserXAttrs());
            hopINode.setNumSysXAttrs(inode.getNumSysXAttrs());
        }
        return hopINode;
    }

    @Override
    public com.gmail.benrcarver.serverlessnamenode.server.namenode.INode convertDALtoHDFS(
            INode hopINode) throws StorageException {
        try{
            com.gmail.benrcarver.serverlessnamenode.server.namenode.INode inode = null;
            if (hopINode != null) {
                String group = null;
                String user = null;

                try {
                    user = UsersGroups.getUser(hopINode.getUserID());
                } catch (UserNotFoundException e) {
                }

                try {
                    group = UsersGroups.getGroup(hopINode.getGroupID());
                } catch (GroupNotFoundException e) {
                }

                PermissionStatus ps = new PermissionStatus(user, group,
                        new FsPermission(hopINode.getPermission()));
                if (hopINode.isDirectory()) {
                    if (hopINode.isDirWithQuota()) {
                        inode = new INodeDirectory(hopINode.getId(), hopINode.getName(), ps, true);
                        DirectoryWithQuotaFeature quota = new DirectoryWithQuotaFeature.Builder(inode.getId()).build();
                        ((INodeDirectory)inode).addFeature(quota);
                    } else {
                        String iname = (hopINode.getName().length() == 0) ? INodeDirectory.ROOT_NAME : hopINode.getName();
                        inode = new INodeDirectory(hopINode.getId(), iname, ps, true);
                    }

                    inode.setAccessTimeNoPersistance(hopINode.getAccessTime());
                    inode.setModificationTimeNoPersistance(hopINode.getModificationTime());
                    ((INodeDirectory) inode).setMetaStatus(hopINode.getMetaStatus());
                    ((INodeDirectory) inode).setChildrenNum(hopINode.getChildrenNum());
                } else if (hopINode.getSymlink() != null) {
                    inode = new INodeSymlink(hopINode.getId(), hopINode.getSymlink(),
                            hopINode.getModificationTime(), hopINode.getAccessTime(), ps, true);
                } else {
                    inode = new INodeFile(hopINode.getId(), ps, hopINode.getHeader(),
                            hopINode.getModificationTime(), hopINode.getAccessTime(), hopINode.isFileStoredInDB(),
                            hopINode.getStoragePolicyID(), true);
                    if (hopINode.isUnderConstruction()) {
                        FileUnderConstructionFeature ucf = new FileUnderConstructionFeature(hopINode.getClientName(),
                                hopINode.getClientMachine(), inode);
                        ((INodeFile) inode).addFeature(ucf);
                    }
                    ((INodeFile) inode).setGenerationStampNoPersistence(
                            hopINode.getGenerationStamp());
                    ((INodeFile) inode).setSizeNoPersistence(hopINode.getFileSize());

                    ((INodeFile) inode).setFileStoredInDBNoPersistence(hopINode.isFileStoredInDB());
                }
                inode.setLocalNameNoPersistance(hopINode.getName());
                inode.setParentIdNoPersistance(hopINode.getParentId());
                inode.setSubtreeLocked(hopINode.isSubtreeLocked());
                inode.setSubtreeLockOwner(hopINode.getSubtreeLockOwner());
                inode.setUserIDNoPersistence(hopINode.getUserID());
                inode.setGroupIDNoPersistence(hopINode.getGroupID());
                inode.setHeaderNoPersistance(hopINode.getHeader());
                inode.setPartitionIdNoPersistance(hopINode.getPartitionId());
                inode.setLogicalTimeNoPersistance(hopINode.getLogicalTime());
                inode.setBlockStoragePolicyIDNoPersistance(hopINode.getStoragePolicyID());
                inode.setNumAcesNoPersistence(hopINode.getNumAces());
                inode.setNumUserXAttrsNoPersistence(hopINode.getNumUserXAttrs());
                inode.setNumSysXAttrsNoPersistence(hopINode.getNumSysXAttrs());

                if(inode.hasXAttrs()){
                    ((INodeWithAdditionalFields) inode).addXAttrFeature(new XAttrFeature(hopINode.getId()));
                }

            }
            return inode;
        } catch (IOException ex) {
            throw new StorageException(ex);
        }
    }

    @Override
    public long getMaxId() throws StorageException{
        return dataAccess.getMaxId();
    }
}