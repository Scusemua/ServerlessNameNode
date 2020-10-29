package com.gmail.benrcarver.serverlessnamenode.fs;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * Thrown when a symbolic link is encountered in a path.
 */
@InterfaceAudience.LimitedPrivate({"HDFS"})
@InterfaceStability.Stable
public class UnresolvedLinkException extends IOException {
    private static final long serialVersionUID = 1L;

    public UnresolvedLinkException() {
        super();
    }

    public UnresolvedLinkException(String msg) {
        super(msg);
    }
}
