package org.apache.hadoop.hdfs.protocol;


import java.io.IOException;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

/**
 * Exception related to rolling upgrade.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class RollingUpgradeException extends IOException {
    private static final long serialVersionUID = 1L;

    public RollingUpgradeException(String msg) {
        super(msg);
    }
}