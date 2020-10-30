package com.gmail.benrcarver.serverlessnamenode.hdfs.util;

import com.google.common.base.Preconditions;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;

import java.io.EOFException;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * An InputStream implementations which reads from some other InputStream
 * but expects an exact number of bytes. Any attempts to read past the
 * specified number of bytes will return as if the end of the stream
 * was reached. If the end of the underlying stream is reached prior to
 * the specified number of bytes, an EOFException is thrown.
 */
@InterfaceAudience.Private
@InterfaceStability.Evolving
public class ExactSizeInputStream extends FilterInputStream {
    private int remaining;

    /**
     * Construct an input stream that will read no more than
     * 'numBytes' bytes.
     * <p/>
     * If an EOF occurs on the underlying stream before numBytes
     * bytes have been read, an EOFException will be thrown.
     *
     * @param in
     *     the inputstream to wrap
     * @param numBytes
     *     the number of bytes to read
     */
    public ExactSizeInputStream(InputStream in, int numBytes) {
        super(in);
        Preconditions.checkArgument(numBytes >= 0, "Negative expected bytes: ", numBytes);
        this.remaining = numBytes;
    }

    @Override
    public int available() throws IOException {
        return Math.min(super.available(), remaining);
    }

    @Override
    public int read() throws IOException {
        // EOF if we reached our limit
        if (remaining <= 0) {
            return -1;
        }
        final int result = super.read();
        if (result >= 0) {
            --remaining;
        } else if (remaining > 0) {
            // Underlying stream reached EOF but we haven't read the expected
            // number of bytes.
            throw new EOFException(
                    "Premature EOF. Expected " + remaining + "more bytes");
        }
        return result;
    }

    @Override
    public int read(final byte[] b, final int off, int len) throws IOException {
        if (remaining <= 0) {
            return -1;
        }
        len = Math.min(len, remaining);
        final int result = super.read(b, off, len);
        if (result >= 0) {
            remaining -= result;
        } else if (remaining > 0) {
            // Underlying stream reached EOF but we haven't read the expected
            // number of bytes.
            throw new EOFException(
                    "Premature EOF. Expected " + remaining + "more bytes");
        }
        return result;
    }

    @Override
    public long skip(final long n) throws IOException {
        final long result = super.skip(Math.min(n, remaining));
        if (result > 0) {
            remaining -= result;
        } else if (remaining > 0) {
            // Underlying stream reached EOF but we haven't read the expected
            // number of bytes.
            throw new EOFException(
                    "Premature EOF. Expected " + remaining + "more bytes");
        }
        return result;
    }

    @Override
    public boolean markSupported() {
        return false;
    }

    @Override
    public void mark(int readlimit) {
        throw new UnsupportedOperationException();
    }

}
