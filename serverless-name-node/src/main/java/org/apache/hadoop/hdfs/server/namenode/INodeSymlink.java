package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.hdfs.DFSUtil;
import org.apache.hadoop.hdfs.server.blockmanagement.BlockStoragePolicySuite;
import org.apache.hadoop.hdfs.server.namenode.AclFeature;
import org.apache.hadoop.hdfs.server.namenode.XAttrFeature;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.fs.permission.PermissionStatus;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * An {@link INode} representing a symbolic link.
 */
@InterfaceAudience.Private
public class INodeSymlink extends INodeWithAdditionalFields {
    private final byte[] symlink; // The target URI

    public INodeSymlink(long id, String value, long mtime, long atime, PermissionStatus permissions) throws IOException {
        this(id, value, mtime, atime, permissions, false);
    }

    public INodeSymlink(long id, String value, long mtime, long atime,
                        PermissionStatus permissions, boolean inTree) throws IOException {
        super(id, permissions, mtime, atime, inTree);
        this.symlink = DFSUtil.string2Bytes(value);
    }

    public INodeSymlink(INodeSymlink other) throws IOException{
        super(other);
        this.symlink = Arrays.copyOf(other.symlink, other.symlink.length);
    }

    @Override
    public boolean isSymlink() {
        return true;
    }

    /**
     * @return this object.
     */
    @Override
    public INodeSymlink asSymlink() {
        return this;
    }

    public String getSymlinkString() {
        return DFSUtil.bytes2String(symlink);
    }

    public byte[] getSymlink() {
        return symlink;
    }

    @Override
    QuotaCounts computeQuotaUsage(BlockStoragePolicySuite bsps, byte storagePolicyId, QuotaCounts counts) {
        counts.addNameSpace(1);
        return counts;
    }

    @Override
    public void destroyAndCollectBlocks(final BlockStoragePolicySuite bsps,
                                        BlocksMapUpdateInfo collectedBlocks,
                                        final List<INode> removedINodes) {
        removedINodes.add(this);
    }

    @Override
    public ContentSummaryComputationContext computeContentSummary(
            final ContentSummaryComputationContext summary) {
        summary.getCounts().addContent(Content.SYMLINK, 1);
        return summary;
    }

    @Override
    public byte getStoragePolicyID() {
        throw new UnsupportedOperationException(
                "Storage policy are not supported on symlinks");
    }

    @Override
    public byte getLocalStoragePolicyID() {
        throw new UnsupportedOperationException(
                "Storage policy are not supported on symlinks");
    }

    @Override
    public INode cloneInode () throws IOException{
        return new INodeSymlink(this);
    }

    /**
     * getAclFeature is not overridden because it is needed for resolving
     * symlinks.
     @Override
     final AclFeature getAclFeature(int snapshotId) {
     throw new UnsupportedOperationException("ACLs are not supported on symlinks");
     }
     */
    @Override
    public void removeAclFeature() {
        throw new UnsupportedOperationException("ACLs are not supported on symlinks");
    }
    @Override
    public void addAclFeature(AclFeature f) {
        throw new UnsupportedOperationException("ACLs are not supported on symlinks");
    }

    @Override
    final org.apache.hadoop.hdfs.server.namenode.XAttrFeature getXAttrFeature() {
        throw new UnsupportedOperationException("XAttrs are not supported on symlinks");
    }

    @Override
    public void removeXAttrFeature() {
        throw new UnsupportedOperationException("XAttrs are not supported on symlinks");
    }

    @Override
    public void addXAttrFeature(XAttrFeature f) {
        throw new UnsupportedOperationException("XAttrs are not supported on symlinks");
    }
}
