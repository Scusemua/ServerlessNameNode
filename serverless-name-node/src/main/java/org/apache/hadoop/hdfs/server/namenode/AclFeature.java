package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.fs.permission.AclEntry;

import java.util.Collections;
import java.util.List;

/**
 * Feature that represents the ACLs of the inode.
 */
@InterfaceAudience.Private
public class AclFeature {//implements INode.Feature {
    public static final List<AclEntry> EMPTY_ENTRY_LIST = Collections.emptyList();

    private final List<AclEntry> entries;

    public AclFeature(List<AclEntry> entries) {
        this.entries = entries;
    }

    public List<AclEntry> getEntries() {
        return entries;
    }
}