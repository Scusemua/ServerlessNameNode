package org.apache.hadoop.hdfs.server.protocol;

import org.apache.hadoop.hdfs.protocol.DatanodeInfo;

/**
 * Class captures information of a datanode and its storages.
 */
public class DatanodeStorageReport {
    final DatanodeInfo datanodeInfo;
    final StorageReport[] storageReports;

    public DatanodeStorageReport(DatanodeInfo datanodeInfo,
                                 StorageReport[] storageReports) {
        this.datanodeInfo = datanodeInfo;
        this.storageReports = storageReports;
    }

    public DatanodeInfo getDatanodeInfo() {
        return datanodeInfo;
    }

    public StorageReport[] getStorageReports() {
        return storageReports;
    }
}