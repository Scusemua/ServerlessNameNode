/*
 * Copyright (C) 2015 hops.io.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.hops.metadata.adaptor;

import com.gmail.benrcarver.serverlessnamenode.server.common.HdfsServerConstants;
import io.hops.exception.StorageException;
import io.hops.metadata.DalAdaptor;
import io.hops.metadata.hdfs.dal.ReplicaUnderConstructionDataAccess;
import io.hops.metadata.hdfs.entity.ReplicaUnderConstruction;

import java.util.Collection;
import java.util.List;

public class ReplicaUnderConstructionDALAdaptor extends
    DalAdaptor<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction, ReplicaUnderConstruction>
    implements
    ReplicaUnderConstructionDataAccess<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> {

  private final ReplicaUnderConstructionDataAccess<ReplicaUnderConstruction>
      dataAccces;

  public ReplicaUnderConstructionDALAdaptor(
      ReplicaUnderConstructionDataAccess<ReplicaUnderConstruction> dataAccess) {
    this.dataAccces = dataAccess;
  }

  
  @Override
  public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> findReplicaUnderConstructionByINodeId(
      long inodeId) throws StorageException {
    return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction>) convertDALtoHDFS(
        dataAccces.findReplicaUnderConstructionByINodeId(inodeId));
  }
  
  
  @Override
  public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> findReplicaUnderConstructionByINodeIds(
      long[] inodeIds) throws StorageException {
    return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction>) convertDALtoHDFS(
        dataAccces.findReplicaUnderConstructionByINodeIds(inodeIds));
  }
  
  @Override
  public List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> findReplicaUnderConstructionByBlockId(
      long blockId, long inodeId) throws StorageException {
    return (List<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction>) convertDALtoHDFS(
        dataAccces.findReplicaUnderConstructionByBlockId(blockId, inodeId));
  }

  @Override
  public void prepare(
      Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> removed,
      Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> newed,
      Collection<com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction> modified)
      throws StorageException {
    dataAccces.prepare(convertHDFStoDAL(removed), convertHDFStoDAL(newed),
        convertHDFStoDAL(modified));
  }

  @Override
  public int countAll() throws StorageException {
    return dataAccces.countAll();
  }

  @Override
  public ReplicaUnderConstruction convertHDFStoDAL(
      com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction hdfsClass)
      throws StorageException {
    if (hdfsClass != null) {
      return new ReplicaUnderConstruction(hdfsClass.getState().ordinal(),
          hdfsClass.getStorageId(), hdfsClass.getBlockId(),
          hdfsClass.getInodeId(), hdfsClass.getBucketId(), hdfsClass.getChosenAsPrimary(),
          hdfsClass.getGenerationStamp());
    } else {
      return null;
    }
  }

  @Override
  public com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction convertDALtoHDFS(
      ReplicaUnderConstruction dalClass) throws StorageException {
    if (dalClass != null) {
      return new com.gmail.benrcarver.serverlessnamenode.server.blockmanagement.ReplicaUnderConstruction(
          HdfsServerConstants.ReplicaState.values()[dalClass.getState()],
          dalClass.getStorageId(),
          dalClass.getBlockId(),
          dalClass.getInodeId(),
          dalClass.getBucketId(),
          dalClass.getChosenAsPrimary(),
          dalClass.getGenerationStamp());
    } else {
      return null;
    }
  }
}
