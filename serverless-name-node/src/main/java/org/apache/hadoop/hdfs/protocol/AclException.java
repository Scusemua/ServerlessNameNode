package org.apache.hadoop.hdfs.protocol;

import org.apache.yetus.audience.InterfaceAudience;

import java.io.IOException;

/**
 * Indicates a failure manipulating an ACL.
 */
@InterfaceAudience.Private
public class AclException extends IOException {
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new AclException.
     *
     * @param message String message
     */
    public AclException(String message) {
        super(message);
    }
}
