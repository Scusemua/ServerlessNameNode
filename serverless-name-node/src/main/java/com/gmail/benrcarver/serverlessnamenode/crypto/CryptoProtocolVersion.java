package com.gmail.benrcarver.serverlessnamenode.crypto;

/**
 * Versions of the client/server protocol used for HDFS encryption.
 */
public enum CryptoProtocolVersion {
    UNKNOWN("Unknown", 1),
    ENCRYPTION_ZONES("Encryption zones", 2);

    private final String description;
    private final int version;
    private Integer unknownValue = null;

    private static CryptoProtocolVersion[] supported = {ENCRYPTION_ZONES};

    /**
     * @return Array of supported protocol versions.
     */
    public static CryptoProtocolVersion[] supported() {
        return supported;
    }

    CryptoProtocolVersion(String description, int version) {
        this.description = description;
        this.version = version;
    }

    /**
     * Returns if a given protocol version is supported.
     *
     * @param version version number
     * @return true if the version is supported, else false
     */
    public static boolean supports(CryptoProtocolVersion version) {
        if (version.getVersion() == UNKNOWN.getVersion()) {
            return false;
        }
        for (CryptoProtocolVersion v : CryptoProtocolVersion.values()) {
            if (v.getVersion() == version.getVersion()) {
                return true;
            }
        }
        return false;
    }

    public void setUnknownValue(int unknown) {
        this.unknownValue = unknown;
    }

    public int getUnknownValue() {
        return unknownValue;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }

    @Override
    public String toString() {
        return "CryptoProtocolVersion{" +
                "description='" + description + '\'' +
                ", version=" + version +
                ", unknownValue=" + unknownValue +
                '}';
    }
}
