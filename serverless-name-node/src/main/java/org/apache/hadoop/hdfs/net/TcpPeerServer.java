package org.apache.hadoop.hdfs.net;

import org.apache.hadoop.hdfs.net.Peer;
import org.apache.hadoop.hdfs.net.PeerServer;
import org.apache.hadoop.hdfs.protocol.DatanodeID;
import org.apache.hadoop.hdfs.protocol.datatransfer.sasl.DataEncryptionKeyFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.hdfs.protocol.datatransfer.sasl.SaslDataTransferClient;
import org.apache.hadoop.hdfs.security.token.block.BlockTokenIdentifier;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.ipc.Server;
import org.apache.hadoop.security.token.Token;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

@InterfaceAudience.Private
public class TcpPeerServer implements PeerServer {
    static Log LOG = LogFactory.getLog(TcpPeerServer.class);

    private final ServerSocket serverSocket;

    public static org.apache.hadoop.hdfs.net.Peer peerFromSocket(Socket socket)
            throws IOException {
        org.apache.hadoop.hdfs.net.Peer peer = null;
        boolean success = false;
        try {
            // TCP_NODELAY is crucial here because of bad interactions between
            // Nagle's Algorithm and Delayed ACKs. With connection keepalive
            // between the client and DN, the conversation looks like:
            //   1. Client -> DN: Read block X
            //   2. DN -> Client: data for block X
            //   3. Client -> DN: Status OK (successful read)
            //   4. Client -> DN: Read block Y
            // The fact that step #3 and #4 are both in the client->DN direction
            // triggers Nagling. If the DN is using delayed ACKs, this results
            // in a delay of 40ms or more.
            //
            // TCP_NODELAY disables nagling and thus avoids this performance
            // disaster.
            socket.setTcpNoDelay(true);
            SocketChannel channel = socket.getChannel();
            if (channel == null) {
                peer = new org.apache.hadoop.hdfs.net.BasicInetPeer(socket);
            } else {
                peer = new org.apache.hadoop.hdfs.net.NioInetPeer(socket);
            }
            success = true;
            return peer;
        } finally {
            if (!success) {
                if (peer != null) peer.close();
                socket.close();
            }
        }
    }

    public static org.apache.hadoop.hdfs.net.Peer peerFromSocketAndKey(
            SaslDataTransferClient saslClient, Socket s,
            DataEncryptionKeyFactory keyFactory,
            Token<BlockTokenIdentifier> blockToken, DatanodeID datanodeId)
            throws IOException {
        org.apache.hadoop.hdfs.net.Peer peer = null;
        boolean success = false;
        try {
            peer = peerFromSocket(s);
            peer = saslClient.peerSend(peer, keyFactory, blockToken, datanodeId);
            success = true;
            return peer;
        } finally {
            if (!success) {
                IOUtils.cleanup(null, peer);
            }
        }
    }

    /**
     * Create a non-secure TcpPeerServer.
     *
     * @param socketWriteTimeout    The Socket write timeout in ms.
     * @param bindAddr              The address to bind to.
     * @throws IOException
     */
    public TcpPeerServer(int socketWriteTimeout,
                         InetSocketAddress bindAddr) throws IOException {
        this.serverSocket = (socketWriteTimeout > 0) ?
                ServerSocketChannel.open().socket() : new ServerSocket();
        Server.bind(serverSocket, bindAddr, 0);
    }

    /**
     * Create a secure TcpPeerServer.
     *
     * @param secureResources   Security resources.
     */
    /*public TcpPeerServer(SecureResources secureResources) {
        this.serverSocket = secureResources.getStreamingSocket();
    }*/

    /**
     * @return     the IP address which this TcpPeerServer is listening on.
     */
    public InetSocketAddress getStreamingAddr() {
        return new InetSocketAddress(
                serverSocket.getInetAddress().getHostAddress(),
                serverSocket.getLocalPort());
    }

    @Override
    public void setReceiveBufferSize(int size) throws IOException {
        this.serverSocket.setReceiveBufferSize(size);
    }

    @Override
    public org.apache.hadoop.hdfs.net.Peer accept() throws IOException, SocketTimeoutException {
        Peer peer = peerFromSocket(serverSocket.accept());
        return peer;
    }

    @Override
    public String getListeningString() {
        return serverSocket.getLocalSocketAddress().toString();
    }

    @Override
    public void close() throws IOException {
        try {
            serverSocket.close();
        } catch(IOException e) {
            LOG.error("error closing TcpPeerServer: ", e);
        }
    }

    @Override
    public String toString() {
        return "TcpPeerServer(" + getListeningString() + ")";
    }
}
