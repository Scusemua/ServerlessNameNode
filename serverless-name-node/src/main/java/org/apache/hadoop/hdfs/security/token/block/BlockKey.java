package org.apache.hadoop.hdfs.security.token.block;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.security.token.delegation.DelegationKey;

import javax.crypto.SecretKey;

/**
 * Key used for generating and verifying block tokens
 */
@InterfaceAudience.Private
public class BlockKey extends DelegationKey {

    public static enum KeyType {
        CurrKey,
        NextKey,
        SimpleKey
    }

    ;

    private KeyType keyType;


    public BlockKey() {
        super();
    }

    public BlockKey(int keyId, long expiryDate, SecretKey key, KeyType keyType) {
        super(keyId, expiryDate, key);
        this.keyType = keyType;
    }

    public BlockKey(int keyId, long expiryDate, SecretKey key) {
        super(keyId, expiryDate, key);
    }

    public BlockKey(int keyId, long expiryDate, byte[] encodedKey) {
        super(keyId, expiryDate, encodedKey);
    }

    public void setKeyType(KeyType keyType) {
        if (keyType == KeyType.CurrKey || keyType == KeyType.NextKey ||
                keyType == KeyType.SimpleKey) {
            this.keyType = keyType;
        } else {
            throw new IllegalArgumentException("Wrong key type " + keyType);
        }
    }

    public KeyType getKeyType() {
        return keyType;
    }

    public boolean isCurrKey() {
        return this.keyType == KeyType.CurrKey;
    }

    public boolean isNextKey() {
        return this.keyType == KeyType.NextKey;
    }

    public boolean isSimpleKey() {
        return this.keyType == KeyType.SimpleKey;
    }

}

