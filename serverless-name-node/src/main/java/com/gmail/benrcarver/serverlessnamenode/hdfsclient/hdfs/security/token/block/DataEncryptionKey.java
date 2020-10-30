package com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.block;

import org.apache.hadoop.classification.InterfaceAudience;

/**
 * A little struct class to contain all fields required to perform encryption
 * of
 * the DataTransferProtocol.
 */
@InterfaceAudience.Private
public class DataEncryptionKey {
    public final int keyId;
    public final String blockPoolId;
    public final byte[] nonce;
    public final byte[] encryptionKey;
    public final long expiryDate;
    public final String encryptionAlgorithm;

    public DataEncryptionKey(int keyId, String blockPoolId, byte[] nonce,
                             byte[] encryptionKey, long expiryDate, String encryptionAlgorithm) {
        this.keyId = keyId;
        this.blockPoolId = blockPoolId;
        this.nonce = nonce;
        this.encryptionKey = encryptionKey;
        this.expiryDate = expiryDate;
        this.encryptionAlgorithm = encryptionAlgorithm;
    }

    @Override
    public String toString() {
        return keyId + "/" + blockPoolId + "/" + nonce.length + "/" +
                encryptionKey.length;
    }
}
