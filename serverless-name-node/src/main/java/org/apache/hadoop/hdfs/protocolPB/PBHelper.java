package org.apache.hadoop.hdfs.protocolPB;

import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.DatanodeProtocolProtos;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsConstants;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsProtos;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsProtos.DatanodeIDProto;
import com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.HdfsProtos.RollingUpgradeStatusProto;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol.BlockListAsLongs;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol.BlockReport;
import com.gmail.benrcarver.serverlessnamenode.hdfs.server.protocol.Bucket;
import com.gmail.benrcarver.serverlessnamenode.hdfs.util.ExactSizeInputStream;
import com.gmail.benrcarver.serverlessnamenode.protocol.ClientNamenodeProtocolProtos;
import com.gmail.benrcarver.serverlessnamenode.protocol.ClientNamenodeProtocolProtos.RollingUpgradeInfoProto;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import org.apache.hadoop.crypto.CipherOption;
import org.apache.hadoop.crypto.CipherSuite;
import org.apache.hadoop.crypto.CryptoProtocolVersion;
import org.apache.hadoop.fs.FileEncryptionInfo;
import org.apache.hadoop.fs.StorageType;
import org.apache.hadoop.hdfs.protocol.DatanodeID;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.hdfs.protocol.RollingUpgradeInfo;
import org.apache.hadoop.hdfs.protocol.RollingUpgradeStatus;
import org.apache.hadoop.hdfs.security.token.block.BlockTokenIdentifier;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.security.proto.SecurityProtos;
import org.apache.hadoop.security.token.Token;
import org.apache.hadoop.util.DataChecksum;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Utilities for converting protobuf classes to and from implementation classes
 * and other helper utilities to help in dealing with protobuf.
 * <p/>
 * Note that when converting from an internal type to protobuf type, the
 * converter never return null for protobuf type. The check for internal type
 * being null must be done before calling the convert() method.
 */
public class PBHelper {
    private PBHelper() {
        /** Hidden constructor */
    }

    static <T extends Enum<T>, U extends Enum<U>> U castEnum(T from, U[] to) {
        return to[from.ordinal()];
    }

    public static InputStream vintPrefixed(final InputStream input)
            throws IOException {
        final int firstByte = input.read();
        if (firstByte == -1) {
            throw new EOFException("Premature EOF: no length prefix available");
        }

        int size = CodedInputStream.readRawVarint32(firstByte, input);
        assert size >= 0;
        return new ExactSizeInputStream(input, size);
    }

    public static List<HdfsProtos.CipherOptionProto> convertCipherOptions(
            List<CipherOption> options) {
        if (options != null) {
            List<HdfsProtos.CipherOptionProto> protos =
                    Lists.newArrayListWithCapacity(options.size());
            for (CipherOption option : options) {
                protos.add(convert(option));
            }
            return protos;
        }
        return null;
    }

    public static List<CipherOption> convertCipherOptionProtos(
            List<HdfsProtos.CipherOptionProto> protos) {
        if (protos != null) {
            List<CipherOption> options =
                    Lists.newArrayListWithCapacity(protos.size());
            for (HdfsProtos.CipherOptionProto proto : protos) {
                options.add(convert(proto));
            }
            return options;
        }
        return null;
    }

    public static HdfsProtos.CipherOptionProto convert(CipherOption option) {
        if (option != null) {
            HdfsProtos.CipherOptionProto.Builder builder = HdfsProtos.CipherOptionProto.
                    newBuilder();
            if (option.getCipherSuite() != null) {
                builder.setSuite(convert(option.getCipherSuite()));
            }
            if (option.getInKey() != null) {
                builder.setInKey(ByteString.copyFrom(option.getInKey()));
            }
            if (option.getInIv() != null) {
                builder.setInIv(ByteString.copyFrom(option.getInIv()));
            }
            if (option.getOutKey() != null) {
                builder.setOutKey(ByteString.copyFrom(option.getOutKey()));
            }
            if (option.getOutIv() != null) {
                builder.setOutIv(ByteString.copyFrom(option.getOutIv()));
            }
            return builder.build();
        }
        return null;
    }

    public static HdfsProtos.CipherSuiteProto convert(CipherSuite suite) {
        switch (suite) {
            case UNKNOWN:
                return HdfsProtos.CipherSuiteProto.UNKNOWN;
            case AES_CTR_NOPADDING:
                return HdfsProtos.CipherSuiteProto.AES_CTR_NOPADDING;
            default:
                return null;
        }
    }

    public static CipherSuite convert(HdfsProtos.CipherSuiteProto proto) {
        switch (proto) {
            case AES_CTR_NOPADDING:
                return CipherSuite.AES_CTR_NOPADDING;
            default:
                // Set to UNKNOWN and stash the unknown enum value
                CipherSuite suite = CipherSuite.UNKNOWN;
                suite.setUnknownValue(proto.getNumber());
                return suite;
        }
    }

    public static CipherOption convert(HdfsProtos.CipherOptionProto proto) {
        if (proto != null) {
            CipherSuite suite = null;
            if (proto.getSuite() != null) {
                suite = convert(proto.getSuite());
            }
            byte[] inKey = null;
            if (proto.getInKey() != null) {
                inKey = proto.getInKey().toByteArray();
            }
            byte[] inIv = null;
            if (proto.getInIv() != null) {
                inIv = proto.getInIv().toByteArray();
            }
            byte[] outKey = null;
            if (proto.getOutKey() != null) {
                outKey = proto.getOutKey().toByteArray();
            }
            byte[] outIv = null;
            if (proto.getOutIv() != null) {
                outIv = proto.getOutIv().toByteArray();
            }
            return new CipherOption(suite, inKey, inIv, outKey, outIv);
        }
        return null;
    }

    public static HdfsProtos.ZoneEncryptionInfoProto convert(
            CipherSuite suite, CryptoProtocolVersion version, String keyName) {
        if (suite == null || version == null || keyName == null) {
            return null;
        }
        return HdfsProtos.ZoneEncryptionInfoProto.newBuilder()
                .setSuite(convert(suite))
                .setCryptoProtocolVersion(convert(version))
                .setKeyName(keyName)
                .build();
    }

    public static FileEncryptionInfo convert(
            HdfsProtos.FileEncryptionInfoProto proto) {
        if (proto == null) {
            return null;
        }
        CipherSuite suite = convert(proto.getSuite());
        CryptoProtocolVersion version = convert(proto.getCryptoProtocolVersion());
        byte[] key = proto.getKey().toByteArray();
        byte[] iv = proto.getIv().toByteArray();
        String ezKeyVersionName = proto.getEzKeyVersionName();
        String keyName = proto.getKeyName();
        return new FileEncryptionInfo(suite, version, key, iv, keyName,
                ezKeyVersionName);
    }

    public static CryptoProtocolVersion convert(HdfsProtos.CryptoProtocolVersionProto
                                                        proto) {
        switch(proto) {
            case ENCRYPTION_ZONES:
                return CryptoProtocolVersion.ENCRYPTION_ZONES;
            default:
                // Set to UNKNOWN and stash the unknown enum value
                CryptoProtocolVersion version = CryptoProtocolVersion.UNKNOWN;
                version.setUnknownValue(proto.getNumber());
                return version;
        }
    }

    public static RollingUpgradeStatusProto convertRollingUpgradeStatus(
            RollingUpgradeStatus status) {
        return RollingUpgradeStatusProto.newBuilder()
                .setBlockPoolId(status.getBlockPoolId())
                .build();
    }

    public static ClientNamenodeProtocolProtos.RollingUpgradeActionProto convert(HdfsConstants.RollingUpgradeAction a) {
        switch (a) {
            case QUERY:
                return ClientNamenodeProtocolProtos.RollingUpgradeActionProto.QUERY;
            case PREPARE:
                return ClientNamenodeProtocolProtos.RollingUpgradeActionProto.START;
            case FINALIZE:
                return ClientNamenodeProtocolProtos.RollingUpgradeActionProto.FINALIZE;
            default:
                throw new IllegalArgumentException("Unexpected value: " + a);
        }
    }

    public static HdfsConstants.RollingUpgradeAction convert(ClientNamenodeProtocolProtos.RollingUpgradeActionProto a) {
        switch (a) {
            case QUERY:
                return HdfsConstants.RollingUpgradeAction.QUERY;
            case START:
                return HdfsConstants.RollingUpgradeAction.PREPARE;
            case FINALIZE:
                return HdfsConstants.RollingUpgradeAction.FINALIZE;
            default:
                throw new IllegalArgumentException("Unexpected value: " + a);
        }
    }

    public static RollingUpgradeStatus convert(RollingUpgradeStatusProto proto) {
        return new RollingUpgradeStatus(proto.getBlockPoolId());
    }

    public static RollingUpgradeInfoProto convert(RollingUpgradeInfo info) {
        return RollingUpgradeInfoProto.newBuilder()
                .setStatus(convertRollingUpgradeStatus(info))
                .setStartTime(info.getStartTime())
                .setFinalizeTime(info.getFinalizeTime())
                .build();
    }

    public static RollingUpgradeInfo convert(RollingUpgradeInfoProto proto) {
        RollingUpgradeStatusProto status = proto.getStatus();
        return new RollingUpgradeInfo(status.getBlockPoolId(),
                proto.getStartTime(), proto.getFinalizeTime());
    }

    public static RollingUpgradeInfoProto convert(RollingUpgradeInfo info) {
        return RollingUpgradeInfoProto.newBuilder()
                .setStatus(convertRollingUpgradeStatus(info))
                .setStartTime(info.getStartTime())
                .setFinalizeTime(info.getFinalizeTime())
                .build();
    }

    public static HdfsProtos.CryptoProtocolVersionProto convert(CryptoProtocolVersion
                                                             version) {
        switch(version) {
            case UNKNOWN:
                return HdfsProtos.CryptoProtocolVersionProto.UNKNOWN_PROTOCOL_VERSION;
            case ENCRYPTION_ZONES:
                return HdfsProtos.CryptoProtocolVersionProto.ENCRYPTION_ZONES;
            default:
                return null;
        }
    }

    public static HdfsProtos.DatanodeInfoProto convert(DatanodeInfo info) {
        HdfsProtos.DatanodeInfoProto.Builder builder = HdfsProtos.DatanodeInfoProto.newBuilder();
        if (info.getNetworkLocation() != null) {
            builder.setLocation(info.getNetworkLocation());
        }
        builder
                .setId(PBHelper.convert((DatanodeID) info))
                .setCapacity(info.getCapacity())
                .setDfsUsed(info.getDfsUsed())
                .setRemaining(info.getRemaining())
                .setBlockPoolUsed(info.getBlockPoolUsed())
                .setCacheCapacity(info.getCacheCapacity())
                .setCacheUsed(info.getCacheUsed())
                .setLastUpdate(info.getLastUpdate())
                .setLastUpdateMonotonic(info.getLastUpdateMonotonic())
                .setXceiverCount(info.getXceiverCount())
                .setAdminState(PBHelper.convert(info.getAdminState()))
                .build();
        return builder.build();
    }

    // DatanodeId
    public static DatanodeID convert(DatanodeIDProto dn) {
        return new DatanodeID(dn.getIpAddr(), dn.getHostName(), dn.getDatanodeUuid(),
                dn.getXferPort(), dn.getInfoPort(), dn.hasInfoSecurePort() ? dn
                .getInfoSecurePort() : 0, dn.getIpcPort());
    }

    public static DatanodeIDProto convert(DatanodeID dn) {
        // For wire compatibility with older versions we transmit the StorageID
        // which is the same as the DatanodeUuid. Since StorageID is a required
        // field we pass the empty string if the DatanodeUuid is not yet known.
        return DatanodeIDProto.newBuilder()
                .setIpAddr(dn.getIpAddr())
                .setHostName(dn.getHostName())
                .setDatanodeUuid(dn.getDatanodeUuid() != null ? dn.getDatanodeUuid() : "")
                .setXferPort(dn.getXferPort())
                .setInfoPort(dn.getInfoPort())
                .setInfoSecurePort(dn.getInfoSecurePort())
                .setIpcPort(dn.getIpcPort()).build();
    }

    // Arrays of DatanodeId
    public static DatanodeIDProto[] convert(DatanodeID[] did) {
        if (did == null) {
            return null;
        }
        final int len = did.length;
        DatanodeIDProto[] result = new DatanodeIDProto[len];
        for (int i = 0; i < len; ++i) {
            result[i] = convert(did[i]);
        }
        return result;
    }

    public static DatanodeID[] convert(DatanodeIDProto[] did) {
        if (did == null) {
            return null;
        }
        final int len = did.length;
        DatanodeID[] result = new DatanodeID[len];
        for (int i = 0; i < len; ++i) {
            result[i] = convert(did[i]);
        }
        return result;
    }

    static public HdfsProtos.DatanodeInfoProto convertDatanodeInfo(DatanodeInfo di) {
        if (di == null) {
            return null;
        }
        return convert(di);
    }

    public static StorageType convertStorageType(HdfsProtos.StorageTypeProto type) {
        switch(type) {
            case DISK:
                return StorageType.DISK;
            case SSD:
                return StorageType.SSD;
            case RAID5:
                return StorageType.RAID5;
            case ARCHIVE:
                return StorageType.ARCHIVE;
            case DB:
                return StorageType.DB;
            case PROVIDED:
                return StorageType.PROVIDED;
            default:
                throw new IllegalStateException(
                        "BUG: StorageTypeProto not found, type=" + type);
        }
    }

    public static StorageType[] convertStorageTypes(
            List<HdfsProtos.StorageTypeProto> storageTypesList, int expectedSize) {
        final StorageType[] storageTypes = new StorageType[expectedSize];
        if (storageTypesList.size() != expectedSize) { // missing storage types
            Preconditions.checkState(storageTypesList.isEmpty());
            Arrays.fill(storageTypes, StorageType.DEFAULT);
        } else {
            for (int i = 0; i < storageTypes.length; ++i) {
                storageTypes[i] = convertStorageType(storageTypesList.get(i));
            }
        }
        return storageTypes;
    }

    public static SecurityProtos.TokenProto convert(Token<?> tok) {
        return SecurityProtos.TokenProto.newBuilder().
                setIdentifier(ByteString.copyFrom(tok.getIdentifier())).
                setPassword(ByteString.copyFrom(tok.getPassword())).
                setKind(tok.getKind().toString()).
                setService(tok.getService().toString()).build();
    }

    public static Token<BlockTokenIdentifier> convert(SecurityProtos.TokenProto blockToken) {
        return new Token<>(
                blockToken.getIdentifier().toByteArray(),
                blockToken.getPassword().toByteArray(), new Text(blockToken.getKind()),
                new Text(blockToken.getService()));
    }

    public static DataChecksum.Type convert(HdfsProtos.ChecksumTypeProto type) {
        return DataChecksum.Type.valueOf(type.getNumber());
    }

    public static DatanodeInfo.AdminStates convert(HdfsProtos.DatanodeInfoProto.AdminState adminState) {
        switch (adminState) {
            case DECOMMISSION_INPROGRESS:
                return DatanodeInfo.AdminStates.DECOMMISSION_INPROGRESS;
            case DECOMMISSIONED:
                return DatanodeInfo.AdminStates.DECOMMISSIONED;
            case NORMAL:
            default:
                return DatanodeInfo.AdminStates.NORMAL;
        }
    }

    public static DatanodeProtocolProtos.BlockReportProto convert(BlockReport report, boolean useBlocksBuffer) {

        List<DatanodeProtocolProtos.BlockReportBucketProto> bucketProtos = new
                ArrayList<>();
        for (Bucket bucket : report.getBuckets()) {
            DatanodeProtocolProtos.BlockReportBucketProto.Builder bucketBuilder =
                    DatanodeProtocolProtos.BlockReportBucketProto.newBuilder();

            bucketBuilder.setHash(ByteString.copyFrom(bucket.getHash()));
            bucketBuilder.setSkip(bucket.isSkip());

            BlockListAsLongs blocks = bucket.getBlocks();
            if (useBlocksBuffer) {
                bucketBuilder.setNumberOfBlocks(blocks.getNumberOfBlocks());
                bucketBuilder.addAllBlocksBuffers(blocks.getBlocksBuffers());
            }else {
                for (long value : blocks.getBlockListAsLongs()) {
                    bucketBuilder.addBlocks(value);
                }
            }
            bucketProtos.add(bucketBuilder.build());
        }

        return DatanodeProtocolProtos.BlockReportProto.newBuilder()
                .addAllBuckets(bucketProtos).build();
    }
}
