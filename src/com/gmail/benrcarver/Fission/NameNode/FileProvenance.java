/*
 * Copyright (C) 2019 hops.io.
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
package com.gmail.benrcarver.Fission.NameNode;

import io.hops.common.Pair;
import io.hops.exception.StorageException;
import io.hops.exception.TransactionContextException;
import io.hops.metadata.hdfs.entity.FileProvXAttrBufferEntry;
import io.hops.metadata.hdfs.entity.FileProvenanceEntry;
import io.hops.security.UsersGroups;
import io.hops.transaction.EntityManager;
import org.apache.hadoop.fs.XAttr;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hadoop.util.Time;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Optional;

public class FileProvenance {
  final static org.slf4j.Logger LOG =
    LoggerFactory.getLogger(FileProvenance.class.getName());
  
  final static String PROV_PROJECTS = "Projects";
  final static String[] PROV_HIVE = new String[]{"apps", "hive", "warehouse", "_featurestore.db"};
  // "/Projects" + project + dataset + 2 dirs(ml)
  final static int PROV_PROJ_RETAINED_DIRS = ProvParents.PROJECT.ordinal() + 2;
  final static int PROV_HIVE_RETAINED_DIRS = 6; // "/apps/hive/warehouse" + featurestore + featuregroup
  final static int PROV_RETAINED_DIRS = Math.max(PROV_PROJ_RETAINED_DIRS, PROV_HIVE_RETAINED_DIRS);
  
  private enum TrackedProv {
    PROJECT,
    DATASET,
    HIVE,
    OTHER
  }
  
  //ordinal is important - preserve order - array generated by the ProvHelper.provenanceDirs
  private enum ProvParents {
    PARENT_DIRECT,
    PARENT_P2,
    PARENT_P1,
    DATASET,
    PROJECT
  }
  
  public static void log(long namenodeId, INode inode, FileProvenanceEntry.Operation op) throws IOException {
    Pair<TrackedProv, INodeDirectory[]> parents = provenanceDirs(inode);
    switch(parents.getL()) {
      case DATASET:
      case HIVE:{
        switch(parents.getR()[ProvParents.DATASET.ordinal()].getMetaStatus()) {
          case FULL_PROV_ENABLED:
            break;
          case MIN_PROV_ENABLED:
            switch(op) {
              case CREATE:
              case DELETE:
                break;
              case XATTR_ADD:
              case XATTR_UPDATE:
              case XATTR_DELETE:
                return; // these should be intercepted in the xattr call
              default: return;
            } break;
          default:
            return;
        }
        //if we are here we are tracking
        Optional<XAttr> xattr = Optional.empty();
        log(namenodeId, inode, parents.getL(), parents.getR(), op, xattr);
      } break;
      case PROJECT:
      case OTHER:
      default: return;
    }
  }
  
  public static void log(long namenodeId, INode inode, FileProvenanceEntry.Operation op, XAttr xattr)
    throws IOException {
    if(XAttr.NameSpace.PROVENANCE.equals(xattr.getNameSpace())) {
      Pair<TrackedProv, INodeDirectory[]> parents = provenanceDirs(inode);
      switch(parents.getL()) {
        case DATASET:
        case HIVE:{
          switch(parents.getR()[ProvParents.DATASET.ordinal()].getMetaStatus()) {
            case FULL_PROV_ENABLED:
            case MIN_PROV_ENABLED:
              break;
            default:
              return;
          }
          //if we are here we are tracking
          log(namenodeId, inode, parents.getL(), parents.getR(), op, Optional.of(xattr));
        } break;
        case PROJECT:
        case OTHER:
        default: return;
      }
    }
  }
  
  private static void log(long namenodeId, INode inode, TrackedProv type, INodeDirectory[] parents,
    FileProvenanceEntry.Operation op, Optional<XAttr> xattr) throws IOException {
    String tieBreaker = namenodeId + "_" + Thread.currentThread().getId();
    Long projectIId;
    String projectName;
    switch(type) {
      case DATASET: {
        projectIId = parents[ProvParents.PROJECT.ordinal()].getId();
        projectName = parents[ProvParents.PROJECT.ordinal()].getLocalName();
      } break;
      case HIVE: {
        projectIId = -1l;
        projectName = projectNameFromHiveDB(parents[ProvParents.DATASET.ordinal()].getLocalName());
      } break;
      default: return;
    }
    
    UserGroupInformation ugi;
    int remoteUserId;
    String remoteUserName;
    try {
      ugi = NameNode.getRemoteUser();
      remoteUserId = UsersGroups.getUserID(ugi.getUserName());
      remoteUserName = ugi.getUserName();
    } catch (IOException ex) {
      String msg = "provenance - error getting user - issuer of operation for inode:" + inode.getLocalName();
      LOG.error(msg, ex);
      throw ex;
    }
    String appId = ugi.getApplicationId();
    if(appId == null) {
      appId = "none";
    }
    
    long timestamp = System.currentTimeMillis();
    String p1Name = parents[ProvParents.PARENT_P1.ordinal()] != null
      ? parents[ProvParents.PARENT_P1.ordinal()].getLocalName() : "";
    String p2Name = parents[ProvParents.PARENT_P2.ordinal()] != null
      ? parents[ProvParents.PARENT_P2.ordinal()].getLocalName() : "";
    
    String xattrName = xattr.isPresent() ? xattr.get().getName() : "";
    byte[] xattrVal = xattr.isPresent() ? xattr.get().getValue() : null;
    
    FileProvenanceEntry ple = new FileProvenanceEntry(inode.getId(), op, inode.getLogicalTime(), timestamp,
      appId, remoteUserId, tieBreaker, inode.getPartitionId(), projectIId,
      parents[ProvParents.DATASET.ordinal()].getId(), parents[ProvParents.PARENT_DIRECT.ordinal()].getId(),
      inode.getLocalName(), projectName, parents[ProvParents.DATASET.ordinal()].getLocalName(),
      p1Name, p2Name, parents[ProvParents.PARENT_DIRECT.ordinal()].getLocalName(),
      remoteUserName, xattrName, inode.getLogicalTime(), timestamp,
      parents[ProvParents.DATASET.ordinal()].getLogicalTime(), xattrVal);
    
    try {
      if (xattr.isPresent()) {
        byte xattrNamespace = xattr.get().getNameSpace().getId();
        byte[] xattrValue = xattr.get().getValue();
        FileProvXAttrBufferEntry xattrEntry
          = new FileProvXAttrBufferEntry(inode.getId(), xattrNamespace, xattrName, inode.getLogicalTime(), xattrValue);
        EntityManager.add(xattrEntry);
      }
      EntityManager.add(ple);
    } catch (TransactionContextException | StorageException ex) {
      String msg = "provenance - error persisting in ndb for inode:" + inode.getLocalName();
      LOG.error(msg, ex);
      throw ex;
    }
  }
  static Pair<TrackedProv, INodeDirectory[]> provenanceDirs(INode inode)
    throws TransactionContextException, StorageException {
    INodeDirectory[] provDirs = new INodeDirectory[]{null, null, null, null, null, null};
    LinkedList<INodeDirectory> aux = new LinkedList<>();
    
    if (inode.isRoot()) {
      return new Pair<>(TrackedProv.OTHER, provDirs);
    }
    
    //save the top PROV_RETAINED_DIRS(6) dirs - this is used to detect the Datasets/Hive patterns
    try {
      INodeDirectory current = inode.getParent();
      provDirs[ProvParents.PARENT_DIRECT.ordinal()] = current;
      if(current.isRoot()) {
        return new Pair<>(TrackedProv.OTHER, provDirs);
      }
      aux.add(current);
      while (!current.isRoot()) {
        current = current.getParent();
        aux.add(current);
        if (aux.size() > PROV_RETAINED_DIRS) {
          aux.removeFirst();
        }
      }
    } catch (TransactionContextException | StorageException ex) {
      String msg = "provenance - error getting inode parents:" + inode.getLocalName();
      LOG.error(msg, ex);
      throw ex;
    }
    
    aux.removeLast();  //drop root path element
    //this is under root
    if(aux.isEmpty()) {
      return new Pair<>(TrackedProv.OTHER, provDirs);
    }
    if (PROV_PROJECTS.equals(aux.getLast().getLocalName())) { //part of a project - path - /Projects/
      aux.removeLast(); //drop Projects path element
      if(aux.isEmpty()) { //project
        if(inode instanceof INodeDirectory) {
          provDirs[ProvParents.PROJECT.ordinal()] = (INodeDirectory) inode;
          return new Pair<>(TrackedProv.PROJECT, provDirs);
        } else {
          return new Pair<>(TrackedProv.OTHER, provDirs);
        }
      }
      provDirs[ProvParents.PROJECT.ordinal()] = aux.removeLast();
      if (aux.isEmpty()) { //dataset
        if(inode instanceof INodeDirectory) {
          provDirs[ProvParents.DATASET.ordinal()] = (INodeDirectory) inode;
          return new Pair<>(TrackedProv.DATASET, provDirs);
        } else {
          return new Pair<>(TrackedProv.OTHER, provDirs);
        }
      } else { //part of a dataset
        provDirs[ProvParents.DATASET.ordinal()] = aux.removeLast();
        provDirs[ProvParents.PARENT_P1.ordinal()] = aux.isEmpty() ? null : aux.removeLast();
        provDirs[ProvParents.PARENT_P2.ordinal()] = aux.isEmpty() ? null : aux.removeLast();
        return new Pair<>(TrackedProv.DATASET, provDirs);
      }
    } else if(isHive(aux)) { //part of hive - path - /apps/hive/warehouse/
      aux.removeLast(); // PROV_HIVE[0] - apps
      aux.removeLast(); // PROV_HIVE[1] - hive
      aux.removeLast(); // PROV_HIVE[2] - warehouse
      
      provDirs[ProvParents.PROJECT.ordinal()] = null; //when in hive path we do not know the project
      
      if(aux.isEmpty()) { //hive database
        if(inode instanceof INodeDirectory) {
          provDirs[ProvParents.DATASET.ordinal()] = (INodeDirectory) inode;
        } else {
          return new Pair<>(TrackedProv.OTHER, provDirs);
        }
      } else { //
        provDirs[ProvParents.DATASET.ordinal()] = aux.removeLast();
      }
      provDirs[ProvParents.PARENT_P1.ordinal()] = aux.isEmpty() ? null : aux.removeLast();
      provDirs[ProvParents.PARENT_P2.ordinal()] = aux.isEmpty() ? null : aux.removeLast();
      return new Pair<>(TrackedProv.HIVE, provDirs);
    } else {
      return new Pair<>(TrackedProv.OTHER, provDirs);
    }
  }
  
  static boolean isHive(LinkedList<INodeDirectory> parents) {
    return parents.size() >= 3
      && PROV_HIVE[0].equals(parents.get(parents.size()-1).getLocalName())
      && PROV_HIVE[1].equals(parents.get(parents.size()-2).getLocalName())
      && PROV_HIVE[2].equals(parents.get(parents.size()-3).getLocalName());
  }
  
  static String projectNameFromHiveDB(String hiveDBName) {
    int idx = hiveDBName.indexOf(PROV_HIVE[3]); //featurestore hive db
    idx = (idx == -1) ? hiveDBName.indexOf(".db") : idx; //normal hive db
    idx = (idx == -1) ? hiveDBName.length() : idx; //other
    return hiveDBName.substring(0, idx);
  }
}
