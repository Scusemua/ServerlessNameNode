package io.hops.metadata.hdfs.entity;

/**
 * Encapsulates a DataNode as stored in the intermediate storage.
 */
public final class DataNode {

    /**
     * UUID identifying a given DataNode. For upgraded DataNodes, this is the
     * same as the StorageID that was previously used by the DataNode.
     * For newly formatted DataNodes, it is a UUID.
     */
    private final String datanodeUuid;

    /**
     * The hostname claimed by DataNode.
     */
    private final String hostname;

    /**
     * IP address of the DataNode.
     */
    private final String ipAddress;

    /**
     * Data streaming port.
     */
    private final int xferPort;

    /**
     * Info server port.
     */
    private final int infoPort;

    /**
     * IPC server port.
     */
    private final int ipcPort;

    public DataNode(String datanodeUuid, String hostname, String ipAddress, int xferPort, int infoPort, int ipcPort) {
        this.datanodeUuid = datanodeUuid;
        this.hostname = hostname;
        this.ipAddress = ipAddress;
        this.xferPort = xferPort;
        this.infoPort = infoPort;
        this.ipcPort = ipcPort;
    }

    public String getDatanodeUuid() {
        return datanodeUuid;
    }

    public String getHostname() {
        return hostname;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public int getXferPort() {
        return xferPort;
    }

    public int getInfoPort() {
        return infoPort;
    }

    public int getIpcPort() {
        return ipcPort;
    }

    @Override
    public String toString() {
        return "Datanode <UUID = " + datanodeUuid + ", hostname = " + hostname + ", ipAddress = " + ipAddress
                + ", xferPort = " + xferPort + ", infoPort = " + infoPort + ", ipcPort = " + ipcPort;
    }
}