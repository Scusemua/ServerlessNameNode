package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.hadoop.fs.StorageType;
import org.apache.yetus.audience.InterfaceAudience;
import org.apache.yetus.audience.InterfaceStability;

@InterfaceAudience.Private
@InterfaceStability.Evolving
public class DatanodeInfoWithStorage extends DatanodeInfo {
    private final String storageID;
    private final StorageType storageType;

    public DatanodeInfoWithStorage(DatanodeInfo from, String storageID,
                                   StorageType storageType) {
        super(from);
        this.storageID = storageID;
        this.storageType = storageType;
        setSoftwareVersion(from.getSoftwareVersion());
        setDependentHostNames(from.getDependentHostNames());
        setLevel(from.getLevel());
        setParent(from.getParent());
    }

    public String getStorageID() {
        return storageID;
    }

    public StorageType getStorageType() {
        return storageType;
    }

    @Override
    public boolean equals(Object o) {
        // allows this class to be used interchangeably with DatanodeInfo
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        // allows this class to be used interchangeably with DatanodeInfo
        return super.hashCode();
    }

    @Override
    public String toString() {
        return "DatanodeInfoWithStorage[" + super.toString() + "," + storageID +
                "," + storageType + "]";
    }
}
