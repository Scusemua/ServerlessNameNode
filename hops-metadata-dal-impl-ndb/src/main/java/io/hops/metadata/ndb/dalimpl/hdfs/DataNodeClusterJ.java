package io.hops.metadata.ndb.dalimpl.hdfs;

import com.mysql.clusterj.annotation.Column;
import com.mysql.clusterj.annotation.PersistenceCapable;
import com.mysql.clusterj.annotation.PrimaryKey;
import io.hops.exception.StorageException;
import io.hops.metadata.hdfs.TablesDef;
import io.hops.metadata.hdfs.dal.DataNodeDataAccess;
import io.hops.metadata.hdfs.entity.DataNode;
import io.hops.metadata.ndb.ClusterjConnector;
import io.hops.metadata.ndb.wrapper.HopsSession;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class DataNodeClusterJ implements TablesDef.DataNodesTableDef, DataNodeDataAccess<DataNode> {
    private static final Log LOG = LogFactory.getLog(EncodingStatusClusterj.class);

    @PersistenceCapable(table = TABLE_NAME)
    public interface DataNodeDTO {
        @PrimaryKey
        @Column(name = DATANODE_UUID)
        String getDatanodeUuid();
        void setDatanodeUuid(String datanodeUuid);

        @Column(name = HOSTNAME)
        String getHostname();
        void setHostname(String hostname);

        @Column(name = IP_ADDR)
        String getIpAddress();
        void setIpAddr(String ipAddr);

        @Column(name = XFER_PORT)
        int getXferPort();
        void setXferPort(int xferPort);

        @Column(name = INFO_PORT)
        int getInfoPort();
        void setInfoPort(int infoPort);

        @Column(name = IPC_PORT)
        int getIpcPort();
        void setIpcPort(int ipcPort);
    }

    private ClusterjConnector connector = ClusterjConnector.getInstance();

    @Override
    public DataNode getDataNode(String uuid) throws StorageException {
        return null;
    }

    @Override
    public void removeDataNode(String uuid) throws StorageException {

    }

    /**
     * Add a DatNode to the intermediate storage.
     * @param dataNode The DataNode to add to intermediate storage.
     */
    @Override
    public void addDataNode(DataNode dataNode) throws StorageException {
        LOG.info("ADD " + dataNode.toString());
        DataNodeDTO dataNodeDTO = null;
        HopsSession session = connector.obtainSession();

        try {
            dataNodeDTO = session.newInstance(DataNodeDTO.class);
            copyState(dataNodeDTO, dataNode);
            session.savePersistent(dataNodeDTO);
        } finally {
            session.release(dataNodeDTO);
        }
    }

    /**
     * Copy the state from the given {@link io.hops.metadata.hdfs.entity.DataNode} instance to the given
     * {@link io.hops.metadata.ndb.dalimpl.hdfs.DataNodeClusterJ.DataNodeDTO} instance.
     * @param dataNodeDTO The destination object.
     * @param dataNode The source object.
     */
    private void copyState(DataNodeDTO dataNodeDTO, DataNode dataNode) {
        dataNodeDTO.setDatanodeUuid(dataNode.getDatanodeUuid());
        dataNodeDTO.setHostname(dataNode.getHostname());
        dataNodeDTO.setIpAddr(dataNode.getIpAddress());
        dataNodeDTO.setXferPort(dataNode.getXferPort());
        dataNodeDTO.setInfoPort(dataNode.getInfoPort());
        dataNodeDTO.setIpcPort(dataNode.getIpcPort());
    }
}
