package com.gmail.benrcarver.serverlessnamenode.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * The file has not finished being written to enough datanodes yet.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class NotReplicatedYetException extends IOException {
    private static final long serialVersionUID = 1L;

    public NotReplicatedYetException(String msg) {
        super(msg);
    }
}
