package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * This exception is thrown when an operation is not supported.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class UnsupportedActionException extends IOException {
    /**
     * for java.io.Serializable
     */
    private static final long serialVersionUID = 1L;

    public UnsupportedActionException(String msg) {
        super(msg);
    }
}
