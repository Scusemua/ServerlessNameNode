package com.gmail.benrcarver.serverlessnamenode.hdfs.protocolPB;

import com.gmail.benrcarver.serverlessnamenode.hdfs.util.ExactSizeInputStream;
import com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos;
import com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos;
import com.gmail.benrcarver.serverlessnamenode.server.protocol.BlockListAsLongs;
import com.gmail.benrcarver.serverlessnamenode.server.protocol.BlockReport;
import com.gmail.benrcarver.serverlessnamenode.server.protocol.Bucket;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import org.apache.hadoop.crypto.CipherOption;
import org.apache.hadoop.crypto.CipherSuite;
import org.eclipse.jetty.io.ByteBufferPool;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
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
