package org.apache.hadoop.hdfs.protocol.datatransfer.sasl;

import com.gmail.benrcarver.serverlessnamenode.hdfs.net.Peer;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.DataTransferProtos.DataTransferEncryptorMessageProto;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.IOStreamPair;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.datatransfer.InvalidEncryptionKeyException;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocolPB.PBHelper;
import com.google.common.base.Charsets;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Maps;
import com.google.common.net.InetAddresses;
import com.google.protobuf.ByteString;
import org.apache.commons.codec.binary.Base64;
import org.apache.hadoop.classification.InterfaceAudience;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.crypto.CipherOption;
import org.apache.hadoop.crypto.CryptoCodec;
import org.apache.hadoop.crypto.CryptoInputStream;
import org.apache.hadoop.crypto.CryptoOutputStream;
import org.apache.hadoop.security.SaslRpcServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.sasl.Sasl;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static com.gmail.benrcarver.serverlessnamenode.hdfs.protocolPB.PBHelper.vintPrefixed;

/**
 * Utility methods implementing SASL negotiation for DataTransferProtocol.
 */
@InterfaceAudience.Private
public final class DataTransferSaslUtil {
    private static final Logger LOG = LoggerFactory.getLogger(
            DataTransferSaslUtil.class);

    /**
     * Delimiter for the three-part SASL username string.
     */
    public static final String NAME_DELIMITER = " ";

    /**
     * Sent by clients and validated by servers. We use a number that's unlikely
     * to ever be sent as the value of the DATA_TRANSFER_VERSION.
     */
    public static final int SASL_TRANSFER_MAGIC_NUMBER = 0xDEADBEEF;

    /**
     * Returns InetAddress from peer.  The getRemoteAddressString has the form
     * [host][/ip-address]:port.  The host may be missing.  The IP address (and
     * preceding '/') may be missing.  The port preceded by ':' is always present.
     *
     * @param peer
     * @return InetAddress from peer
     */
    public static InetAddress getPeerAddress(Peer peer) {
        String remoteAddr = peer.getRemoteAddressString().split(":")[0];
        int slashIdx = remoteAddr.indexOf('/');
        return InetAddresses.forString(slashIdx != -1 ?
                remoteAddr.substring(slashIdx + 1, remoteAddr.length()) :
                remoteAddr);
    }

    /**
     * Check whether requested SASL Qop contains privacy.
     *
     * @param saslProps properties of SASL negotiation
     * @return boolean true if privacy exists
     */
    public static boolean requestedQopContainsPrivacy(
            Map<String, String> saslProps) {
        Set<String> requestedQop = ImmutableSet.copyOf(Arrays.asList(
                saslProps.get(Sasl.QOP).split(",")));
        return requestedQop.contains("auth-conf");
    }

    /**
     * Sends a SASL negotiation message indicating an error.
     *
     * @param out stream to receive message
     * @param message to send
     * @throws IOException for any error
     */
    public static void sendGenericSaslErrorMessage(OutputStream out,
                                                   String message) throws IOException {
        sendSaslMessage(out, DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.ERROR, null, message);
    }

    /**
     * Sends a SASL negotiation message.
     *
     * @param out stream to receive message
     * @param payload to send
     * @throws IOException for any error
     */
    public static void sendSaslMessage(OutputStream out, byte[] payload)
            throws IOException {
        sendSaslMessage(out, DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.SUCCESS, payload, null);
    }

    /**
     * Sends a SASL negotiation message.
     *
     * @param out stream to receive message
     * @param status negotiation status
     * @param payload to send
     * @param message to send
     * @throws IOException for any error
     */
    public static void sendSaslMessage(OutputStream out,
                                       DataTransferEncryptorMessageProto.DataTransferEncryptorStatus status, byte[] payload, String message)
            throws IOException {
        DataTransferEncryptorMessageProto.Builder builder =
                DataTransferEncryptorMessageProto.newBuilder();

        builder.setStatus(status);
        if (payload != null) {
            builder.setPayload(ByteString.copyFrom(payload));
        }
        if (message != null) {
            builder.setMessage(message);
        }

        DataTransferEncryptorMessageProto proto = builder.build();
        proto.writeDelimitedTo(out);
        out.flush();
    }

    /**
     * Send SASL message and negotiated cipher option to client.
     *
     * @param out stream to receive message
     * @param payload to send
     * @param option negotiated cipher option
     * @throws IOException for any error
     */
    public static void sendSaslMessageAndNegotiatedCipherOption(
            OutputStream out, byte[] payload, CipherOption option)
            throws IOException {
        DataTransferEncryptorMessageProto.Builder builder =
                DataTransferEncryptorMessageProto.newBuilder();

        builder.setStatus(DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.SUCCESS);
        if (payload != null) {
            builder.setPayload(ByteString.copyFrom(payload));
        }
        if (option != null) {
            builder.addCipherOption(PBHelper.convert(option));
        }

        DataTransferEncryptorMessageProto proto = builder.build();
        proto.writeDelimitedTo(out);
        out.flush();
    }

    /**
     * Send a SASL negotiation message and negotiation cipher options to server.
     *
     * @param out stream to receive message
     * @param payload to send
     * @param options cipher options to negotiate
     * @throws IOException for any error
     */
    public static void sendSaslMessageAndNegotiationCipherOptions(
            OutputStream out, byte[] payload, List<CipherOption> options)
            throws IOException {
        DataTransferEncryptorMessageProto.Builder builder =
                DataTransferEncryptorMessageProto.newBuilder();

        builder.setStatus(DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.SUCCESS);
        if (payload != null) {
            builder.setPayload(ByteString.copyFrom(payload));
        }
        if (options != null) {
            builder.addAllCipherOption(PBHelper.convertCipherOptions(options));
        }

        DataTransferEncryptorMessageProto proto = builder.build();
        proto.writeDelimitedTo(out);
        out.flush();
    }

    /**
     * Creates SASL properties required for an encrypted SASL negotiation.
     *
     * @param encryptionAlgorithm to use for SASL negotation
     * @return properties of encrypted SASL negotiation
     */
    public static Map<String, String> createSaslPropertiesForEncryption(
            String encryptionAlgorithm) {
        Map<String, String> saslProps = Maps.newHashMapWithExpectedSize(3);
        saslProps.put(Sasl.QOP, SaslRpcServer.QualityOfProtection.PRIVACY.getSaslQop());
        saslProps.put(Sasl.SERVER_AUTH, "true");
        saslProps.put("com.sun.security.sasl.digest.cipher", encryptionAlgorithm);
        return saslProps;
    }

    /**
     * For an encrypted SASL negotiation, encodes an encryption key to a SASL
     * password.
     *
     * @param encryptionKey to encode
     * @return key encoded as SASL password
     */
    public static char[] encryptionKeyToPassword(byte[] encryptionKey) {
        return new String(Base64.encodeBase64(encryptionKey, false), Charsets.UTF_8)
                .toCharArray();
    }

    /**
     * Read SASL message and negotiated cipher option from server.
     *
     * @param in stream to read
     * @return SaslResponseWithNegotiatedCipherOption SASL message and
     * negotiated cipher option
     * @throws IOException for any error
     */
    public static SaslResponseWithNegotiatedCipherOption
    readSaslMessageAndNegotiatedCipherOption(InputStream in)
            throws IOException {
        DataTransferEncryptorMessageProto proto =
                DataTransferEncryptorMessageProto.parseFrom(vintPrefixed(in));
        if (proto.getStatus() == DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.ERROR_UNKNOWN_KEY) {
            throw new InvalidEncryptionKeyException(proto.getMessage());
        } else if (proto.getStatus() == DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.ERROR) {
            throw new IOException(proto.getMessage());
        } else {
            byte[] response = proto.getPayload().toByteArray();
            List<CipherOption> options = PBHelper.convertCipherOptionProtos(
                    proto.getCipherOptionList());
            CipherOption option = null;
            if (options != null && !options.isEmpty()) {
                option = options.get(0);
            }
            return new SaslResponseWithNegotiatedCipherOption(response, option);
        }
    }

    /**
     * Checks that SASL negotiation has completed for the given participant, and
     * the negotiated quality of protection is included in the given SASL
     * properties and therefore acceptable.
     *
     * @param sasl participant to check
     * @param saslProps properties of SASL negotiation
     * @throws IOException for any error
     */
    public static void checkSaslComplete(SaslParticipant sasl,
                                         Map<String, String> saslProps) throws IOException {
        if (!sasl.isComplete()) {
            throw new IOException("Failed to complete SASL handshake");
        }
        Set<String> requestedQop = ImmutableSet.copyOf(Arrays.asList(
                saslProps.get(Sasl.QOP).split(",")));
        String negotiatedQop = sasl.getNegotiatedQop();
        LOG.debug("Verifying QOP, requested QOP = {}, negotiated QOP = {}",
                requestedQop, negotiatedQop);
        if (!requestedQop.contains(negotiatedQop)) {
            throw new IOException(String.format("SASL handshake completed, but " +
                    "channel does not have acceptable quality of protection, " +
                    "requested = %s, negotiated = %s", requestedQop, negotiatedQop));
        }
    }

    /**
     * Decrypt the key and iv of the negotiated cipher option.
     *
     * @param option negotiated cipher option
     * @param sasl SASL participant representing client
     * @return CipherOption negotiated cipher option which contains the
     * decrypted key and iv
     * @throws IOException for any error
     */
    public static CipherOption unwrap(CipherOption option, SaslParticipant sasl)
            throws IOException {
        if (option != null) {
            byte[] inKey = option.getInKey();
            if (inKey != null) {
                inKey = sasl.unwrap(inKey, 0, inKey.length);
            }
            byte[] outKey = option.getOutKey();
            if (outKey != null) {
                outKey = sasl.unwrap(outKey, 0, outKey.length);
            }
            return new CipherOption(option.getCipherSuite(), inKey, option.getInIv(),
                    outKey, option.getOutIv());
        }

        return null;
    }

    /**
     * Create IOStreamPair of {@link CryptoInputStream}
     * and {@link CryptoOutputStream}
     *
     * @param conf the configuration
     * @param cipherOption negotiated cipher option
     * @param out underlying output stream
     * @param in underlying input stream
     * @param isServer is server side
     * @return IOStreamPair the stream pair
     * @throws IOException for any error
     */
    public static IOStreamPair createStreamPair(Configuration conf,
                                                CipherOption cipherOption, OutputStream out, InputStream in,
                                                boolean isServer) throws IOException {
        if (LOG.isDebugEnabled()) {
            LOG.debug("Creating IOStreamPair of CryptoInputStream and " +
                    "CryptoOutputStream.");
        }
        CryptoCodec codec = CryptoCodec.getInstance(conf,
                cipherOption.getCipherSuite());
        byte[] inKey = cipherOption.getInKey();
        byte[] inIv = cipherOption.getInIv();
        byte[] outKey = cipherOption.getOutKey();
        byte[] outIv = cipherOption.getOutIv();
        InputStream cIn = new CryptoInputStream(in, codec,
                isServer ? inKey : outKey, isServer ? inIv : outIv);
        OutputStream cOut = new CryptoOutputStream(out, codec,
                isServer ? outKey : inKey, isServer ? outIv : inIv);
        return new IOStreamPair(cIn, cOut);
    }

    /**
     * Reads a SASL negotiation message.
     *
     * @param in stream to read
     * @return bytes of SASL negotiation messsage
     * @throws IOException for any error
     */
    public static byte[] readSaslMessage(InputStream in) throws IOException {
        DataTransferEncryptorMessageProto proto =
                DataTransferEncryptorMessageProto.parseFrom(vintPrefixed(in));
        if (proto.getStatus() == DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.ERROR_UNKNOWN_KEY) {
            throw new InvalidEncryptionKeyException(proto.getMessage());
        } else if (proto.getStatus() == DataTransferEncryptorMessageProto.DataTransferEncryptorStatus.ERROR) {
            throw new IOException(proto.getMessage());
        } else {
            return proto.getPayload().toByteArray();
        }
    }
}
