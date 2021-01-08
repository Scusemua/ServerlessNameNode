package org.apache.hadoop.hdfs.server.common;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.util.SequentialNumber;

/**
 * *************************************************************
 * A GenerationStamp is a Hadoop FS primitive, identified by a long.
 * **************************************************************
 */
@InterfaceAudience.Private
public class GenerationStamp extends SequentialNumber {
    /**
     * The last reserved generation stamp.
     */
    public static final long LAST_RESERVED_STAMP = 1000L;

    /**
     * Create a new instance, initialized to {@link #LAST_RESERVED_STAMP}.
     */
    public GenerationStamp() {
        super(LAST_RESERVED_STAMP);
    }
}
