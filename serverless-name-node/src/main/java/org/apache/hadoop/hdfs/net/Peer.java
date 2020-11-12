package org.apache.hadoop.hdfs.net;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.net.unix.DomainSocket;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;

/**
 * Represents a connection to a peer.
 */
@InterfaceAudience.Private
public interface Peer extends Closeable {
    /**
     * @return                The input stream channel associated with this
     *                        peer, or null if it has none.
     */
    public ReadableByteChannel getInputStreamChannel();

    /**
     * Set the read timeout on this peer.
     *
     * @param timeoutMs       The timeout in milliseconds.
     */
    public void setReadTimeout(int timeoutMs) throws IOException;

    /**
     * @return                The receive buffer size.
     */
    public int getReceiveBufferSize() throws IOException;

    /**
     * @return                True if TCP_NODELAY is turned on.
     */
    public boolean getTcpNoDelay() throws IOException;

    /**
     * Set the write timeout on this peer.
     *
     * Note: this is not honored for BasicInetPeer.
     * See BasicSocketPeer.setWriteTimeout for details.
     *
     * @param timeoutMs       The timeout in milliseconds.
     */
    public void setWriteTimeout(int timeoutMs) throws IOException;

    /**
     * @return                true only if the peer is closed.
     */
    public boolean isClosed();

    /**
     * Close the peer.
     *
     * It's safe to re-close a Peer that is already closed.
     */
    public void close() throws IOException;

    /**
     * @return               A string representing the remote end of our
     *                       connection to the peer.
     */
    public String getRemoteAddressString();

    /**
     * @return               A string representing the local end of our
     *                       connection to the peer.
     */
    public String getLocalAddressString();

    /**
     * @return               An InputStream associated with the Peer.
     *                       This InputStream will be valid until you close
     *                       this peer with Peer#close.
     */
    public InputStream getInputStream() throws IOException;

    /**
     * @return               An OutputStream associated with the Peer.
     *                       This OutputStream will be valid until you close
     *                       this peer with Peer#close.
     */
    public OutputStream getOutputStream() throws IOException;

    /**
     * @return               True if the peer resides on the same
     *                       computer as we.
     */
    public boolean isLocal();

    /**
     * @return               The DomainSocket associated with the current
     *                       peer, or null if there is none.
     */
    public DomainSocket getDomainSocket();

    /**
     * Return true if the channel is secure.
     *
     * @return               True if our channel to this peer is not
     *                       susceptible to man-in-the-middle attacks.
     */
    public boolean hasSecureChannel();
}

