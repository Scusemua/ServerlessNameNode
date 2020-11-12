package org.apache.hadoop.hdfs.net;

import org.apache.hadoop.classification.InterfaceAudience;

import java.io.Closeable;
import java.io.IOException;
import java.net.SocketTimeoutException;

@InterfaceAudience.Private
public interface PeerServer extends Closeable {
    /**
     * Set the receive buffer size of the PeerServer.
     *
     * @param size     The receive buffer size.
     */
    public void setReceiveBufferSize(int size) throws IOException;

    /**
     * Listens for a connection to be made to this server and accepts
     * it. The method blocks until a connection is made.
     *
     * @exception IOException  if an I/O error occurs when waiting for a
     *               connection.
     * @exception SecurityException  if a security manager exists and its
     *             <code>checkAccept</code> method doesn't allow the operation.
     * @exception SocketTimeoutException if a timeout was previously set and
     *             the timeout has been reached.
     */
    public Peer accept() throws IOException, SocketTimeoutException;

    /**
     * @return                 A string representation of the address we're
     *                         listening on.
     */
    public String getListeningString();

    /**
     * Free the resources associated with this peer server.
     * This normally includes sockets, etc.
     *
     * @throws IOException     If there is an error closing the PeerServer
     */
    public void close() throws IOException;
}

