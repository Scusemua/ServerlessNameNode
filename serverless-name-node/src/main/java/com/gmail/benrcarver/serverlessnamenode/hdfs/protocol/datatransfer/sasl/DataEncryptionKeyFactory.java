package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.sasl;

import com.gmail.benrcarver.serverlessnamenode.hdfsclient.hdfs.security.token.block.DataEncryptionKey;
import org.apache.hadoop.classification.InterfaceAudience;

import java.io.IOException;

/**
 * Creates a new {@link DataEncryptionKey} on demand.
 */
@InterfaceAudience.Private
public interface DataEncryptionKeyFactory {

    /**
     * Creates a new DataEncryptionKey.
     *
     * @return DataEncryptionKey newly created
     * @throws IOException for any error
     */
    DataEncryptionKey newDataEncryptionKey() throws IOException;
}