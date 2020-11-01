package com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Rolling upgrade status
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class RollingUpgradeStatus {
    private String blockPoolId;

    public RollingUpgradeStatus(String blockPoolId) {
        this.blockPoolId = blockPoolId;
    }

    public String getBlockPoolId() {
        return blockPoolId;
    }

    @Override
    public int hashCode() {
        return blockPoolId.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        } else if (obj == null || !(obj instanceof RollingUpgradeStatus)) {
            return false;
        }
        final RollingUpgradeStatus that = (RollingUpgradeStatus)obj;
        return this.blockPoolId.equals(that.blockPoolId);
    }

    @Override
    public String toString() {
        return "  Block Pool ID: " + blockPoolId;
    }
}
