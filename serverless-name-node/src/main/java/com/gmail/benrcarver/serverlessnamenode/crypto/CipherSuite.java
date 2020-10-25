package com.gmail.benrcarver.serverlessnamenode.crypto;

import com.gmail.benrcarver.serverlessnamenode.util.StringUtils;

/**
 * Defines properties of a CipherSuite. Modeled after the ciphers in
 * {@link javax.crypto.Cipher}.
 */
public enum CipherSuite {
    UNKNOWN("Unknown", 0),
    AES_CTR_NOPADDING("AES/CTR/NoPadding", 16);

    private final String name;
    private final int algoBlockSize;

    private Integer unknownValue = null;

    CipherSuite(String name, int algoBlockSize) {
        this.name = name;
        this.algoBlockSize = algoBlockSize;
    }

    public void setUnknownValue(int unknown) {
        this.unknownValue = unknown;
    }

    public int getUnknownValue() {
        return unknownValue;
    }

    /**
     * @return name of cipher suite, as in {@link javax.crypto.Cipher}
     */
    public String getName() {
        return name;
    }

    /**
     * @return size of an algorithm block in bytes
     */
    public int getAlgorithmBlockSize() {
        return algoBlockSize;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("{");
        builder.append("name: " + name);
        builder.append(", algorithmBlockSize: " + algoBlockSize);
        if (unknownValue != null) {
            builder.append(", unknownValue: " + unknownValue);
        }
        builder.append("}");
        return builder.toString();
    }

    /**
     * Convert to CipherSuite from name, {@link #algoBlockSize} is fixed for
     * certain cipher suite, just need to compare the name.
     * @param name cipher suite name
     * @return CipherSuite cipher suite
     */
    public static CipherSuite convert(String name) {
        CipherSuite[] suites = CipherSuite.values();
        for (CipherSuite suite : suites) {
            if (suite.getName().equals(name)) {
                return suite;
            }
        }
        throw new IllegalArgumentException("Invalid cipher suite name: " + name);
    }

    /**
     * Returns suffix of cipher suite configuration.
     * @return String configuration suffix
     */
    public String getConfigSuffix() {
        String[] parts = name.split("/");
        StringBuilder suffix = new StringBuilder();
        for (String part : parts) {
            suffix.append(".").append(StringUtils.toLowerCase(part));
        }

        return suffix.toString();
    }
}