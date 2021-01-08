package org.apache.hadoop.hdfs.protocol.datatransfer;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * Encryption key verification failed.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class InvalidEncryptionKeyException extends IOException {
    private static final long serialVersionUID = 0l;

    public InvalidEncryptionKeyException() {
        super();
    }

    public InvalidEncryptionKeyException(String msg) {
        super(msg);
    }
}