package org.apache.hadoop.hdfs.protocol.datatransfer;

import org.apache.hadoop.classification.InterfaceAudience;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * A little struct class to wrap an InputStream and an OutputStream.
 */
@InterfaceAudience.Private
public class IOStreamPair {
    public final InputStream in;
    public final OutputStream out;

    public IOStreamPair(InputStream in, OutputStream out) {
        this.in = in;
        this.out = out;
    }
}