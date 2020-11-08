package com.gmail.benrcarver.serverlessnamenode.hdfs;

import com.gmail.benrcarver.serverlessnamenode.hdfs.net.Peer;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.security.token.Token;

import java.io.IOException;
import java.net.InetSocketAddress;

@InterfaceAudience.Private
public interface RemotePeerFactory {
    /**
     * @param addr          The address to connect to.
     * @param blockToken    Token used during optional SASL negotiation
     * @param datanodeId    ID of destination DataNode
     * @return              A new Peer connected to the address.
     *
     * @throws IOException  If there was an error connecting or creating
     *                      the remote socket, encrypted stream, etc.
     */
    Peer newConnectedPeer(InetSocketAddress addr, Token<BlockTokenIdentifier> blockToken, DatanodeID datanodeId)
            throws IOException;
}