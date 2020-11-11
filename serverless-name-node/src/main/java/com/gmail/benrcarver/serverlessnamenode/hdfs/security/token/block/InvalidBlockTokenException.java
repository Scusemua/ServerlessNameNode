package com.gmail.benrcarver.serverlessnamenode.hdfs.security.token.block;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * Access token verification failed.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class InvalidBlockTokenException extends IOException {
    private static final long serialVersionUID = 168L;

    public InvalidBlockTokenException() {
        super();
    }

    public InvalidBlockTokenException(String msg) {
        super(msg);
    }
}
