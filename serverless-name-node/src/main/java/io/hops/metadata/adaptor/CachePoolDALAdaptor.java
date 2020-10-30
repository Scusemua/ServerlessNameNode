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

import io.hops.exception.StorageException;
import io.hops.metadata.DalAdaptor;
import io.hops.metadata.hdfs.dal.CachePoolDataAccess;
import io.hops.metadata.hdfs.entity.CachePool;
import org.apache.hadoop.fs.permission.FsPermission;

import java.util.Collection;

public class CachePoolDALAdaptor extends DalAdaptor<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool, CachePool>
    implements CachePoolDataAccess<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool> {

  private CachePoolDataAccess<CachePool> dataAccess;

  public CachePoolDALAdaptor(CachePoolDataAccess<CachePool> dataAccess) {
    this.dataAccess = dataAccess;
  }

  @Override
  public CachePool convertHDFStoDAL(com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool cachePool) throws StorageException {
    return new CachePool(cachePool.getPoolName(), cachePool.getOwnerName(), cachePool.getGroupName(), cachePool.
        getMode().toShort(), cachePool.getLimit(), cachePool.getMaxRelativeExpiryMs(), cachePool.getBytesNeeded(),
        cachePool.getBytesCached(), cachePool.getFilesNeeded(), cachePool.getFilesCached());
  }

  @Override
  public com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool convertDALtoHDFS(CachePool cachePool) {
    if(cachePool==null){
      return null;
    }
    return new com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool(cachePool.getPoolName(), cachePool.getOwnerName(),
        cachePool.getGroupName(), new FsPermission(cachePool.getMode()), cachePool.getLimit(), cachePool.getMaxRelativeExpiryMs(), cachePool.getBytesNeeded(),
        cachePool.getBytesCached(), cachePool.getFilesNeeded(), cachePool.getFilesCached());
  }

  @Override
  public void prepare(Collection<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool> removed,
                      Collection<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool> newed) throws StorageException {
    dataAccess.prepare(convertHDFStoDAL(removed), convertHDFStoDAL(newed));
  }

  @Override
  public com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool find(String key) throws StorageException {
    return convertDALtoHDFS(dataAccess.find(key));
  }

  @Override
  public Collection<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool> findAboveName(String key) throws StorageException{
    return convertDALtoHDFS(dataAccess.findAboveName(key));
  }
  
  @Override
  public Collection<com.gmail.benrcarver.serverlessnamenode.server .namenode.CachePool> findAll() throws StorageException{
    return convertDALtoHDFS(dataAccess.findAll());
  }
}
