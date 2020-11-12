package org.apache.hadoop.hdfs.net;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.IOStreamPair;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.net.unix.DomainSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.ReadableByteChannel;

/**
 * Represents a peer that we communicate with by using an encrypted
 * communications medium.
 */
@InterfaceAudience.Private
public class EncryptedPeer implements Peer {
    private final Peer enclosedPeer;

    /**
     * An encrypted InputStream.
     */
    private final InputStream in;

    /**
     * An encrypted OutputStream.
     */
    private final OutputStream out;

    /**
     * An encrypted ReadableByteChannel.
     */
    private final ReadableByteChannel channel;

    public EncryptedPeer(Peer enclosedPeer, IOStreamPair ios) {
        this.enclosedPeer = enclosedPeer;
        this.in = ios.in;
        this.out = ios.out;
        this.channel = ios.in instanceof ReadableByteChannel ?
                (ReadableByteChannel)ios.in : null;
    }

    @Override
    public ReadableByteChannel getInputStreamChannel() {
        return channel;
    }

    @Override
    public void setReadTimeout(int timeoutMs) throws IOException {
        enclosedPeer.setReadTimeout(timeoutMs);
    }

    @Override
    public int getReceiveBufferSize() throws IOException {
        return enclosedPeer.getReceiveBufferSize();
    }

    @Override
    public boolean getTcpNoDelay() throws IOException {
        return enclosedPeer.getTcpNoDelay();
    }

    @Override
    public void setWriteTimeout(int timeoutMs) throws IOException {
        enclosedPeer.setWriteTimeout(timeoutMs);
    }

    @Override
    public boolean isClosed() {
        return enclosedPeer.isClosed();
    }

    @Override
    public void close() throws IOException {
        try {
            in.close();
        } finally {
            try {
                out.close();
            } finally {
                enclosedPeer.close();
            }
        }
    }

    @Override
    public String getRemoteAddressString() {
        return enclosedPeer.getRemoteAddressString();
    }

    @Override
    public String getLocalAddressString() {
        return enclosedPeer.getLocalAddressString();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return in;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return out;
    }

    @Override
    public boolean isLocal() {
        return enclosedPeer.isLocal();
    }

    @Override
    public String toString() {
        return "EncryptedPeer(" + enclosedPeer + ")";
    }

    @Override
    public DomainSocket getDomainSocket() {
        return enclosedPeer.getDomainSocket();
    }

    @Override
    public boolean hasSecureChannel() {
        return true;
    }
}
