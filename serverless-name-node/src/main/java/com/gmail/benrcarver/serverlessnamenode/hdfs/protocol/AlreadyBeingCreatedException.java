package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * The exception that happens when you ask to create a file that already
 * is being created, but is not closed yet.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class AlreadyBeingCreatedException extends IOException {
    static final long serialVersionUID = 0x12308AD009L;

    public AlreadyBeingCreatedException(String msg) {
        super(msg);
    }
}
