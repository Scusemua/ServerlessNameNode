package org.apache.hadoop.hdfs.server.namenode;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * This exception is thrown when the name node is in safe mode.
 * Client cannot modified namespace until the safe mode is off.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class SafeModeException extends IOException {
    private static final long serialVersionUID = 1L;

    public SafeModeException(String text, FSNameSystem.SafeModeInfo mode) throws IOException {
        super(text + ". Name node is in safe mode.\n" + mode.getTurnOffTip());
    }
}