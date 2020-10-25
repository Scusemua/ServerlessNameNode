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
package io.hops.transaction.handler;

import io.hops.transaction.handler.RequestHandler.OperationType;

public enum HDFSOperationType implements OperationType {
  // NameNodeRpcServer

  INITIALIZE,
  ACTIVATE,
  META_SAVE,
  SET_PERMISSION,
  SET_OWNER,
  SET_OWNER_SUBTREE,
  GET_BLOCK_LOCATIONS,
  GET_STATS,
  CONCAT,
  TRUNCATE,
  SET_TIMES,
  CREATE_SYM_LINK,
  SET_STORAGE_POLICY,
  GET_PREFERRED_BLOCK_SIZE,
  SET_REPLICATION,
  SET_META_ENABLED,
  START_FILE,
  RECOVER_LEASE,
  APPEND_FILE,
  GET_ADDITIONAL_BLOCK,
  GET_ADDITIONAL_DATANODE,
  ABANDON_BLOCK,
  COMPLETE_FILE,
  DEPRICATED_RENAME,
  RENAME,
  DELETE,
  GET_FILE_INFO,
  MKDIRS,
  GET_CONTENT_SUMMARY,
  SET_QUOTA,
  FSYNC,
  COMMIT_BLOCK_SYNCHRONIZATION,
  RENEW_LEASE,
  GET_LISTING,
  REGISTER_DATANODE,
  HANDLE_HEARTBEAT,
  GET_MISSING_BLOCKS_COUNT,
  SAVE_NAMESPACE,
  SAFE_MODE_MONITOR,
  SET_SAFE_MODE,
  GET_BLOCKS_TOTAL,
  PROCESS_DISTRIBUTED_UPGRADE,
  GET_FS_STATE,
  UPDATE_BLOCK_FOR_PIPELINE,
  UPDATE_PIPELINE,
  LIST_CORRUPT_FILE_BLOCKS,
  GET_DELEGATION_TOKEN,
  RENEW_DELEGATION_TOKEN,
  CANCEL_DELEGATION_TOKEN,
  GET_SAFE_MODE,
  GET_NUMBER_OF_MISSING_BLOCKS,
  GET_PENDING_DELETION_BLOCKS_COUNT,
  GET_EXCESS_BLOCKS_COUNT,
  SET_ROOT,
  GET_ROOT,
  GET_METADATA_LOG_ENTRIES,
  HAS_CHILDREN,
  GET_SMALL_FILE_DATA,
  GET_DB_FILE_TABLE_SIZE,
  //BlockManager
  FIND_AND_MARK_BLOCKS_AS_CORRUPT,
  PREPARE_PROCESS_REPORT,
  PROCESS_FIRST_BLOCK_REPORT,
  PROCESS_REPORT,
  AFTER_PROCESS_REPORT,
  BLOCK_RECEIVED_AND_DELETED_INC_BLK_REPORT,
  RESCAN_MISREPLICATED_BLOCKS,
  PROCESS_MIS_REPLICATED_BLOCKS,
  GET_ALL_MACHINE_BLOCKS,
  GET_ALL_MACHINE_BLOCKS_IDS,
  GET_ALL_STORAGE_BLOCKS_IDS,
  GET_ALL_STORAGE_IDS,
  REMOVE_STORED_BLOCK,
  REMOVE_STORED_BLOCKS,
  CHECK_REPLICATION_IN_PROGRESS,
  PROCESS_OVER_REPLICATED_BLOCKS_ON_RECOMMISSION,
  GET_ALL_BLOCKS,
  GET_ALL_BLOCKS_SIZE,
  REPLICATION_MONITOR,
  COMPUTE_REPLICATION_WORK_FOR_BLOCK,
  PROCESS_QUEUED_REPORT,
  PROCESS_TIMEDOUT_PENDING_BLOCK,
  GET_VALID_BLK_LOCS,
  COUNT_CORRUPT_REPLICAS,
  GET_ALL_CORRUPT_REPLICAS,
  REMOVE_CORRUPT_REPLICA,
  GET_NUM_INVALIDATED_BLKS,
  GET_INV_BLKS_BY_STORAGEID,
  RM_INV_BLKS,
  GET_ALL_INV_BLKS,
  DEL_ALL_INV_BLKS,
  DEL_ALL_EXCESS_BLKS,
  DEL_ALL_UNDER_REPLICATED_BLKS,
  COUNT_ALL_UNDER_REPLICATED_BLKS,
  COUNT_UNDER_REPLICATED_BLKS_LESS_THAN_LVL4,
  COUNT_CORRUPT_REPL_ONE_BLOCKS,
  COUNT_UNDER_REPLICATED_BLKS_AT_LVL,
  GET_ALL_UNDER_REPLICATED_BLKS,
  GET_UNDER_REPLICATED_BLKS_By_LEVEL_LIMITED,
  DEL_ALL_PENDING_REPL_BLKS,
  COUNT_ALL_VALID_PENDING_REPL_BLKS,
  GET_TIMED_OUT_PENDING_BLKS,
  GET_BLOCK,
  GET_EXCESS_RELPLICAS_BY_STORAGEID,
  CHOOSE_UNDER_REPLICATED_BLKS,
  ADD_INV_BLOCKS,
  AFTER_PROCESS_REPORT_ADD_BLK,
  AFTER_PROCESS_REPORT_ADD_BLK_IMMEDIATE,
  AFTER_PROCESS_REPORT_ADD_UC_BLK,
  AFTER_PROCESS_REPORT_ADD_UC_BLK_IMMEDIATE,
  AFTER_PROCESS_REPORT_ADD_CORRUPT_BLK,
  GET_INODE_IDS,
  GET_INODE_STORAGE_POLICY,
  RESOLVE_INODE_FROM_BLOCKID,
  RESOLVE_INODES_FROM_BLOCKIDS,
  GET_BLOCKS,
  REMOVE_UNDER_REPLICATED_BLOCK,
  // DatanodeManager
  REMOVE_DATANODE,
  REFRESH_NODES,
  GET_EXPECTED_BLK_LOCATIONS,
  //DecommisionManager
  DECOMMISION_MONITOR,
  //HeartbeatManager
  HEARTBEAT_MONITOR,
  //LeaseManager
  PREPARE_LEASE_MANAGER_MONITOR,
  LEASE_MANAGER_MONITOR,
  GET_SORTED_LEASES,
  REMOVE_ALL_LEASES,
  CREATE_LEASE_LOCKS,
  // LeaderElection
  SELECT_ALL_NAMENODES,
  LEADER_EXIT,
  GET_ALL_NAME_NODES,
  GET_LEADER,
  LEADER_ELECTION,
  UPDATE_LEADER_COUNTER,
  REMOVE_PREV_LEADERS,
  // BlockTokenSecretManagerNN
  ADD_BLOCK_TOKENS,
  GET_ALL_BLOCK_TOKENS,
  GET_BLOCK_TOKENS,
  REMOVE_ALL,
  GET_KEY_BY_TYPE,
  REMOVE_BLOCK_KEY,
  UPDATE_BLOCK_KEYS,
  GET_KEY_BY_ID,
  REMOVE_ALL_BLOCK_KEYS,
  // Block Generationstamp
  GET_GENERATION_STAMP,
  SET_GENERATION_STAMP,
  //FSNamesystem
  TOTAL_FILES,
  GET_STORAGE_INFO,
  GET_COMPLETE_BLOCKS_TOTAL,
  SET_GEN_STAMP,
  //ClusterInfos
  GET_CLUSTER_INFO,
  // NNStorage
  ADD_STORAGE_INFO,
  //NamenodeJspHElper
  GET_SAFE_MODE_TEXT,
  GENERATE_HEALTH_REPORT,
  GET_INODE,
  TO_XML_BLOCK_INFO,
  TO_XML_CORRUPT_BLOCK_INFO,
  // TestNamenodePing
  COUNT_LEASE,
  // BLockManagerTestUtil
  UPDATE_STATE,
  GET_REPLICA_INFO,
  GET_NUMBER_OF_RACKS,
  GET_COMPUTED_DATANODE_WORK,
  // TestBlockManager
  REMOVE_NODE,
  FULFILL_PIPELINE,
  BLOCK_ON_NODES,
  SCHEDULE_SINGLE_REPLICATION,
  // TestComputeInvalidatedWork
  COMP_INVALIDATE,
  // TestCorruptReplicaInfo
  TEST_CORRUPT_REPLICA_INFO,
  TEST_CORRUPT_REPLICA_INFO2,
  TEST_CORRUPT_REPLICA_INFO3,
  // TestDatanodeDescriptor
  TEST_BLOCKS_COUNTER,
  // TestNodeCount
  COUNT_NODES,
  // TestOverReplicatedBlocks
  TEST_PROCESS_OVER_REPLICATED_BLOCKS,
  // TestPendingReplication
  TEST_PENDING_REPLICATION,
  TEST_PENDING_REPLICATION2,
  TEST_PENDING_REPLICATION3,
  TEST_PENDING_REPLICATION4,
  // TestUnderReplicatedBlocks
  SET_REPLICA_INCREAMENT,
  // TestBlockReport
  TEST_BLOCK_REPORT,
  TEST_BLOCK_REPORT2,
  PRINT_STAT,
  // TestBlockUnderConstruction
  VERIFY_FILE_BLOCKS,
  // TestFsLimits
  VERIFY_FS_LIMITS,
  // TestSafeMode
  TEST_DATANODE_THRESHOLD,
  // TestDfsRename
  COUNT_LEASE_DFS_RENAME,
  // NameNodeAdapter
  GET_LEASE_BY_PATH,
  // DFSTestUtil
  WAIT_CORRUPT_REPLICAS,
  // NNThroughputBenchmark
  ADD_INODE,
  // TestNodeCount
  TEST_NODE_COUNT,
  // Transaction in unit tests.
  TEST,
  GET_VARIABLE,
  INITIALIZE_SID_MAP,
  UPDATE_SID_MAP,
  GET_SET_SID,
  GET_SET_STORAGE,
  UPDATE_INODE_ID_COUNTER,
  UPDATE_BLOCK_ID_COUNTER,
  UPDATE_CACHE_DIRECTIVE_ID_COUNTER,
  GET_INODES_BATCH,
  COUNT_REPLICAS_ON_NODE,
  COUNT_REPLICAS_ON_STORAGE,
  GET_INODEIDS_FOR_BLKS,
  GET_ALL_INODES,
  PROCESS_MIS_REPLICATED_BLOCKS_PER_INODE_BATCH,
  ADD_SAFE_BLOCKS,
  REMOVE_SAFE_BLOCKS,
  GET_SAFE_BLOCKS_COUNT,
  CLEAR_SAFE_BLOCKS,
  INCREMENT_MIS_REPLICATED_FILES_INDEX,
  UPDATE_MIS_REPLICATED_RANGE_QUEUE,
  COUNT_ALL_MIS_REPLICATED_RANGE_QUEUE,
  HAVE_FILES_WITH_IDS_GREATER_THAN,
  HAVE_FILES_WITH_IDS_BETWEEN,
  GET_MIN_FILE_ID,
  GET_MAX_INODE_ID,
  COUNTALL_FILES,
  SET_SAFE_MODE_INFO,
  EXIT_SAFE_MODE,
  SET_SAFE_MODE_REACHED,
  GET_SAFE_MODE_REACHED,
  GET_CLUSTER_SAFE_MODE,
  UNDER_REPLICATED_BLKS_ITERATOR,
  PROCESS_MIS_REPLICATED_BLOCKS_PER_INODE,
  // QuotaUpdateMonitor
  ADD_QUOTA_UPDATE,
  GET_NEXT_QUOTA_BATCH,
  APPLY_QUOTA_UPDATE,
  RESOLVE_INODE_FROM_ID,
  RESOLVE_INODES_FROM_IDS,
  GET_UPDATES_FOR_ID,
  SET_SUBTREE_LOCK,
  RESET_SUBTREE_LOCK,
  SET_ASYNC_SUBTREE_RECOVERY_FLAG,
  TEST_SUBTREE_LOCK,
  TEST_DB_FILES,
  GET_SUBTREE_ROOT,
  GET_CHILD_INODES,
  SUBTREE_PERMISSION_CHECK,
  SUBTREE_RENAME,
  SUBTREE_DELETE,
  SUBTREE_DEPRICATED_RENAME,
  SUBTREE_SETPERMISSION,
  GET_SUBTREE_ATTRIBUTES,
  SUBTREE_PATH_INFO,
  ADD_BLOCK_CHECKSUM,
  GET_BLOCK_CHECKSUM,
  // Erasure coding
  ADD_ENCODING_STATUS,
  DELETE_ENCODING_STATUS,
  UPDATE_ENCODING_STATUS,
  REVOKE_ENCODING_STATUS,
  FIND_ENCODING_STATUS,
  FIND_ACTIVE_ENCODINGS,
  FIND_REQUESTED_ENCODINGS,
  FIND_ENCODED,
  FIND_ACTIVE_REPAIRS,
  COUNT_REQUESTED_ENCODINGS,
  COUNT_ACTIVE_ENCODINGS,
  COUNT_ENCODED,
  COUNT_ACTIVE_REPAIRS,
  FIND_REQUESTED_REPAIRS,
  FIND_POTENTIALLY_FIXED,
  FIND_REQUESTED_PARITY_REPAIRS,
  FIND_POTENTIALLY_FIXED_PARITIES,
  FIND_DELETED,
  FIND_REVOKED,
  CHECK_FIXED_SOURCE,
  CHECK_FIXED_PARITY,
  RECOVER_ENCODING_JOBS,
  DELETE_ENCODING_JOBS,
  PERSIST_ENCODING_JOB,
  ADD_USER,
  GET_USER_GROUPS,
  GET_USERS_AND_GROUPS_BATCH,
  // Block Report Load Balancing
  GET_BR_LB_MAX_CONCURRENT_BRS,
  SET_BR_LB_MAX_CONCURRENT_BRS,
  BR_LB_GET_COUNT,
  BR_LB_GET_ALL,
  BR_LB_ADD,
  BR_LB_REMOVE,
  GET_ALL_MACHINE_BLOCKS_IN_BUCKET,
  GET_ALL_STORAGE_BLOCKS_IN_BUCKETS,
  // Block Report Hashes
  GET_STORAGE_HASHES,
  RESET_STORAGE_HASHES,
  CREATE_ALL_STORAGE_HASHES,
  CHECK_ACCESS,
  UPDATE_LOGICAL_TIME,
  GET_LAST_UPDATED_CONTENT_SUMMARY,
  //ACLs
  REMOVE_ACL_ENTRIES,
  REMOVE_ACL,
  GET_ACL_STATUS,
  REMOVE_DEFAULT_ACL,
  MODIFY_ACL_ENTRIES,
  ADD_INODE_ACES,
  REMOVE_INODE_ACES,
  GET_INODE_ACES_BY_INODE,
  GET_INODE_ACES_BY_PK,
  GET_NEAREST_DEFAULT_ACL_FOR_SUBTREE,
  SET_ACL,
  
  //retry cache
  RETRY_CACHE_WAIT_COMPLETION,
  RETRY_CACHE,
  RETRY_CACHE_GET_ALL,
  CLEAN_RETRY_CACHE,

  //Metadata GC
  MDCLEANER,
  
  //DN CACHE
  ADD_CACHE_DIRECTIVE,
  MODIFY_CACHE_DIRECTIVE,
  REMOVE_CACHE_DIRECTIVE,
  LIST_CACHE_DIRECTIVE,
  SET_NEED_RESCAN,
  GET_NEED_RESCAN,
  LIST_CACHE_DIRECTIVES,
  ADD_CACHE_POOL,
  MODIFY_CACHE_POOL,
  REMOVE_CACHE_POOL,
  LIST_CACHE_POOL,
  PROCESS_CACHED_BLOCKS_REPORT,
  CLEAR_CACHED_BLOCKS,
  RESCAN_BLOCK_MAP,
  RESCAN_CACHE_DIRECTIVE,
  GET_PENDING_CACHED,
  GET_PENDING_UNCACHED,
  GET_ROLLING_UPGRADE_INFO,
  ADD_ROLLING_UPGRADE_INFO,
  SET_BLOCK_TOTAL,
  GET_BLOCK_TOTAL,
  GET_BLOCK_THRESHOLD,
  GET_BLOCK_REPLICATION_QUEUE_THRESHOLD,
  CHECK_BLOCK,
  GET_RETRY_CACHE_CLEANER_EPOCH,
  SET_RETRY_CACHE_CLEANER_EPOCH,

  //XAttr
  SET_XATTR,
  GET_XATTRS,
  REMOVE_XATTRS,
  REMOVE_XATTRS_FOR_INODE,
  LIST_XATTRS,
  
  //EZ
  CREATE_EZ,
  GET_EZ_PATH,
  GET_ALL_EZ,
  LIST_EZ,

  //FSCK
  FSCK_GET_NUM_REPLICAS,
  FSCK_GET_CORRUPT_REPLICAS,
  FSCK_GET_STORAGES
}