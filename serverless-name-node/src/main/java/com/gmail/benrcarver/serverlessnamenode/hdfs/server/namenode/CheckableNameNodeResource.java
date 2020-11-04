package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;

/**
 * Implementers of this class represent a NN resource whose availability can be
 * checked. A resource can be either "required" or "redundant". All required
 * resources must be available for the NN to continue operating. The NN will
 * continue to operate as long as *any* redundant resource is available.
 */
@InterfaceAudience.Private
interface CheckableNameNodeResource {

    /**
     * Is this resource currently available.
     *
     * @return true if and only if the resource in question is available.
     */
    public boolean isResourceAvailable();

    /**
     * Is this resource required.
     *
     * @return true if and only if the resource in question is required for NN
     * operation.
     */
    public boolean isRequired();

}
