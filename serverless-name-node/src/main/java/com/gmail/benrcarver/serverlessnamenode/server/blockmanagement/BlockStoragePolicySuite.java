package com.gmail.benrcarver.serverlessnamenode.server.blockmanagement;

import com.google.common.annotations.VisibleForTesting;
import org.apache.hadoop.fs.StorageType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/** A collection of block storage policies. */
public class BlockStoragePolicySuite {
    static final Logger LOG = LoggerFactory.getLogger(BlockStoragePolicySuite
            .class);

    public static final String STORAGE_POLICY_XATTR_NAME
            = "hsm.block.storage.policy.id";

    public static final int ID_BIT_LENGTH = 4;

    @VisibleForTesting
    public static BlockStoragePolicySuite createDefaultSuite() {
        final BlockStoragePolicy[] policies =
                new BlockStoragePolicy[1 << ID_BIT_LENGTH];

        final byte db = HdfsConstants.DB_STORAGE_POLICY_ID;
        policies[db] = new BlockStoragePolicy(db,
                HdfsConstants.DB_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.DB},
                new StorageType[]{StorageType.SSD, StorageType.DISK},
                new StorageType[]{StorageType.SSD, StorageType.DISK});
        final byte allssdId = HdfsConstants.ALLSSD_STORAGE_POLICY_ID;
        policies[allssdId] = new BlockStoragePolicy(allssdId,
                HdfsConstants.ALLSSD_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.SSD},
                new StorageType[]{StorageType.DISK},
                new StorageType[]{StorageType.DISK});
        final byte onessdId = HdfsConstants.ONESSD_STORAGE_POLICY_ID;
        policies[onessdId] = new BlockStoragePolicy(onessdId,
                HdfsConstants.ONESSD_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.SSD, StorageType.DISK},
                new StorageType[]{StorageType.SSD, StorageType.DISK},
                new StorageType[]{StorageType.SSD, StorageType.DISK});
        final byte hotId = HdfsConstants.HOT_STORAGE_POLICY_ID;
        policies[hotId] = new BlockStoragePolicy(hotId,
                HdfsConstants.HOT_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.DISK},
                StorageType.EMPTY_ARRAY,
                new StorageType[]{StorageType.ARCHIVE});
        final byte warmId = HdfsConstants.WARM_STORAGE_POLICY_ID;
        policies[warmId] = new BlockStoragePolicy(warmId,
                HdfsConstants.WARM_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.DISK, StorageType.ARCHIVE},
                new StorageType[]{StorageType.DISK, StorageType.ARCHIVE},
                new StorageType[]{StorageType.DISK, StorageType.ARCHIVE});
        final byte coldId = HdfsConstants.COLD_STORAGE_POLICY_ID;
        policies[coldId] = new BlockStoragePolicy(coldId,
                HdfsConstants.COLD_STORAGE_POLICY_NAME,
                new StorageType[]{StorageType.ARCHIVE}, StorageType.EMPTY_ARRAY,
                StorageType.EMPTY_ARRAY);
        return new BlockStoragePolicySuite(hotId, policies);
    }

    private final byte defaultPolicyID;
    private final BlockStoragePolicy[] policies;

    public BlockStoragePolicySuite(byte defaultPolicyID,
                                   BlockStoragePolicy[] policies) {
        this.defaultPolicyID = defaultPolicyID;
        this.policies = policies;
    }

    /** @return the corresponding policy. */
    public BlockStoragePolicy getPolicy(byte id) {
        // id == 0 means policy not specified.
        return id == 0? getDefaultPolicy(): policies[id];
    }

    /** @return the default policy. */
    public BlockStoragePolicy getDefaultPolicy() {
        return getPolicy(defaultPolicyID);
    }

    public BlockStoragePolicy getPolicy(String policyName) {
        Preconditions.checkNotNull(policyName);

        if (policies != null) {
            for (BlockStoragePolicy policy : policies) {
                if (policy != null && policy.getName().equalsIgnoreCase(policyName)) {
                    return policy;
                }
            }
        }
        return null;
    }

    public BlockStoragePolicy[] getAllPolicies() {
        List<BlockStoragePolicy> list = Lists.newArrayList();
        if (policies != null) {
            for (BlockStoragePolicy policy : policies) {
                if (policy != null) {
                    list.add(policy);
                }
            }
        }
        return list.toArray(new BlockStoragePolicy[list.size()]);
    }

}
