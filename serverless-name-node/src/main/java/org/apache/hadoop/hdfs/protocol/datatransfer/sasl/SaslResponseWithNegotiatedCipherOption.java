package org.apache.hadoop.hdfs.protocol.datatransfer.sasl;

import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.crypto.CipherOption;

@InterfaceAudience.Private
public class SaslResponseWithNegotiatedCipherOption {
    final byte[] payload;
    final CipherOption cipherOption;

    public SaslResponseWithNegotiatedCipherOption(byte[] payload,
                                                  CipherOption cipherOption) {
        this.payload = payload;
        this.cipherOption = cipherOption;
    }
}