package io.hops.metadata.hdfs.dal;

import io.hops.exception.StorageException;
import io.hops.metadata.common.EntityDataAccess;

/**
 * Interface defining functions with which to get and remove DataNodes from the intermediate storage.
 */
public interface DataNodeDataAccess<T> extends EntityDataAccess {
    /**
     * Retrieve a given DataNode from the intermediate storage.
     * @param uuid The UUID of the DataNode to retrieve.
     */
    T getDataNode(String uuid) throws StorageException;

    /**
     * Remove a given DataNode from the intermediate storage.
     * @param uuid The UUID of the DataNode to remove.
     */
    void removeDataNode(String uuid) throws StorageException;

    /**
     * Add a DatNode to the intermediate storage.
     * @param dataNode This is expected to be of type {@link io.hops.metadata.hdfs.entity.DataNode}.
     */
    void addDataNode(T dataNode) throws StorageException;
}
