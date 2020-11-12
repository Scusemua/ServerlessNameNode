package org.apache.hadoop.hdfs.protocol;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.IOException;

/**
 * Exception indicating that a replica is already being recovery.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class RecoveryInProgressException extends IOException {
    private static final long serialVersionUID = 1L;

    public static class NonAbortingRecoveryInProgressException
            extends RecoveryInProgressException {
        public NonAbortingRecoveryInProgressException(String msg) {
            super(msg);
        }
    }

    public RecoveryInProgressException(String msg) {
        super(msg);
    }
}