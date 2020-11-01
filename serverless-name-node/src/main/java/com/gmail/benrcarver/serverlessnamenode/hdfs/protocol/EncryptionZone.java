package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.classification.InterfaceStability;
import org.apache.hadoop.crypto.CipherSuite;
import org.apache.hadoop.crypto.CryptoProtocolVersion;

/**
 * A simple class for representing an encryption zone. Presently an encryption
 * zone only has a path (the root of the encryption zone), a key name, and a
 * unique id. The id is used to implement batched listing of encryption zones.
 */
@InterfaceAudience.Public
@InterfaceStability.Evolving
public class EncryptionZone {

    private final long id;
    private final String path;
    private final CipherSuite suite;
    private final CryptoProtocolVersion version;
    private final String keyName;

    public EncryptionZone(long id, String path, CipherSuite suite,
                          CryptoProtocolVersion version, String keyName) {
        this.id = id;
        this.path = path;
        this.suite = suite;
        this.version = version;
        this.keyName = keyName;
    }

    public long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public CipherSuite getSuite() {
        return suite;
    }

    public CryptoProtocolVersion getVersion() { return version; }

    public String getKeyName() {
        return keyName;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 31)
                .append(id)
                .append(path)
                .append(suite)
                .append(version)
                .append(keyName).
                        toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }

        EncryptionZone rhs = (EncryptionZone) obj;
        return new EqualsBuilder().
                append(id, rhs.id).
                append(path, rhs.path).
                append(suite, rhs.suite).
                append(version, rhs.version).
                append(keyName, rhs.keyName).
                isEquals();
    }

    @Override
    public String toString() {
        return "EncryptionZone [id=" + id +
                ", path=" + path +
                ", suite=" + suite +
                ", version=" + version +
                ", keyName=" + keyName + "]";
    }
}
