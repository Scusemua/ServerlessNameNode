package com.gmail.benrcarver.serverlessnamenode.protocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 **
 * Protocol used from datanode to the namenode
 * See the request and response for details of rpc call.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: DatanodeProtocol.proto")
public final class DatanodeProtocolServiceGrpc {

  private DatanodeProtocolServiceGrpc() {}

  public static final String SERVICE_NAME = "com.gmail.benrcarver.serverlessnamenode.datanode.DatanodeProtocolService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> getRegisterDatanodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "registerDatanode",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> getRegisterDatanodeMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> getRegisterDatanodeMethod;
    if ((getRegisterDatanodeMethod = DatanodeProtocolServiceGrpc.getRegisterDatanodeMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getRegisterDatanodeMethod = DatanodeProtocolServiceGrpc.getRegisterDatanodeMethod) == null) {
          DatanodeProtocolServiceGrpc.getRegisterDatanodeMethod = getRegisterDatanodeMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "registerDatanode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("registerDatanode"))
              .build();
        }
      }
    }
    return getRegisterDatanodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> getSendHeartbeatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "sendHeartbeat",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> getSendHeartbeatMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> getSendHeartbeatMethod;
    if ((getSendHeartbeatMethod = DatanodeProtocolServiceGrpc.getSendHeartbeatMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getSendHeartbeatMethod = DatanodeProtocolServiceGrpc.getSendHeartbeatMethod) == null) {
          DatanodeProtocolServiceGrpc.getSendHeartbeatMethod = getSendHeartbeatMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "sendHeartbeat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("sendHeartbeat"))
              .build();
        }
      }
    }
    return getSendHeartbeatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getBlockReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "blockReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getBlockReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getBlockReportMethod;
    if ((getBlockReportMethod = DatanodeProtocolServiceGrpc.getBlockReportMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getBlockReportMethod = DatanodeProtocolServiceGrpc.getBlockReportMethod) == null) {
          DatanodeProtocolServiceGrpc.getBlockReportMethod = getBlockReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "blockReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("blockReport"))
              .build();
        }
      }
    }
    return getBlockReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getReportHashesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reportHashes",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getReportHashesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> getReportHashesMethod;
    if ((getReportHashesMethod = DatanodeProtocolServiceGrpc.getReportHashesMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getReportHashesMethod = DatanodeProtocolServiceGrpc.getReportHashesMethod) == null) {
          DatanodeProtocolServiceGrpc.getReportHashesMethod = getReportHashesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reportHashes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("reportHashes"))
              .build();
        }
      }
    }
    return getReportHashesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> getCacheReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cacheReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> getCacheReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> getCacheReportMethod;
    if ((getCacheReportMethod = DatanodeProtocolServiceGrpc.getCacheReportMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getCacheReportMethod = DatanodeProtocolServiceGrpc.getCacheReportMethod) == null) {
          DatanodeProtocolServiceGrpc.getCacheReportMethod = getCacheReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cacheReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("cacheReport"))
              .build();
        }
      }
    }
    return getCacheReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> getBlockReceivedAndDeletedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "blockReceivedAndDeleted",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> getBlockReceivedAndDeletedMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> getBlockReceivedAndDeletedMethod;
    if ((getBlockReceivedAndDeletedMethod = DatanodeProtocolServiceGrpc.getBlockReceivedAndDeletedMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getBlockReceivedAndDeletedMethod = DatanodeProtocolServiceGrpc.getBlockReceivedAndDeletedMethod) == null) {
          DatanodeProtocolServiceGrpc.getBlockReceivedAndDeletedMethod = getBlockReceivedAndDeletedMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "blockReceivedAndDeleted"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("blockReceivedAndDeleted"))
              .build();
        }
      }
    }
    return getBlockReceivedAndDeletedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> getErrorReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "errorReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> getErrorReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> getErrorReportMethod;
    if ((getErrorReportMethod = DatanodeProtocolServiceGrpc.getErrorReportMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getErrorReportMethod = DatanodeProtocolServiceGrpc.getErrorReportMethod) == null) {
          DatanodeProtocolServiceGrpc.getErrorReportMethod = getErrorReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "errorReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("errorReport"))
              .build();
        }
      }
    }
    return getErrorReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "versionRequest",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod;
    if ((getVersionRequestMethod = DatanodeProtocolServiceGrpc.getVersionRequestMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getVersionRequestMethod = DatanodeProtocolServiceGrpc.getVersionRequestMethod) == null) {
          DatanodeProtocolServiceGrpc.getVersionRequestMethod = getVersionRequestMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "versionRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("versionRequest"))
              .build();
        }
      }
    }
    return getVersionRequestMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reportBadBlocks",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod;
    if ((getReportBadBlocksMethod = DatanodeProtocolServiceGrpc.getReportBadBlocksMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getReportBadBlocksMethod = DatanodeProtocolServiceGrpc.getReportBadBlocksMethod) == null) {
          DatanodeProtocolServiceGrpc.getReportBadBlocksMethod = getReportBadBlocksMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reportBadBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("reportBadBlocks"))
              .build();
        }
      }
    }
    return getReportBadBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> getCommitBlockSynchronizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "commitBlockSynchronization",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> getCommitBlockSynchronizationMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> getCommitBlockSynchronizationMethod;
    if ((getCommitBlockSynchronizationMethod = DatanodeProtocolServiceGrpc.getCommitBlockSynchronizationMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getCommitBlockSynchronizationMethod = DatanodeProtocolServiceGrpc.getCommitBlockSynchronizationMethod) == null) {
          DatanodeProtocolServiceGrpc.getCommitBlockSynchronizationMethod = getCommitBlockSynchronizationMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "commitBlockSynchronization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("commitBlockSynchronization"))
              .build();
        }
      }
    }
    return getCommitBlockSynchronizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getActiveNamenodes",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesMethod;
    if ((getGetActiveNamenodesMethod = DatanodeProtocolServiceGrpc.getGetActiveNamenodesMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getGetActiveNamenodesMethod = DatanodeProtocolServiceGrpc.getGetActiveNamenodesMethod) == null) {
          DatanodeProtocolServiceGrpc.getGetActiveNamenodesMethod = getGetActiveNamenodesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getActiveNamenodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("getActiveNamenodes"))
              .build();
        }
      }
    }
    return getGetActiveNamenodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto,
      io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> getGetNextNamenodeToSendBlockReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNextNamenodeToSendBlockReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto.class,
      responseType = io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto,
      io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> getGetNextNamenodeToSendBlockReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto, io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> getGetNextNamenodeToSendBlockReportMethod;
    if ((getGetNextNamenodeToSendBlockReportMethod = DatanodeProtocolServiceGrpc.getGetNextNamenodeToSendBlockReportMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getGetNextNamenodeToSendBlockReportMethod = DatanodeProtocolServiceGrpc.getGetNextNamenodeToSendBlockReportMethod) == null) {
          DatanodeProtocolServiceGrpc.getGetNextNamenodeToSendBlockReportMethod = getGetNextNamenodeToSendBlockReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto, io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNextNamenodeToSendBlockReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("getNextNamenodeToSendBlockReport"))
              .build();
        }
      }
    }
    return getGetNextNamenodeToSendBlockReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> getBlockReportCompletedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "blockReportCompleted",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> getBlockReportCompletedMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> getBlockReportCompletedMethod;
    if ((getBlockReportCompletedMethod = DatanodeProtocolServiceGrpc.getBlockReportCompletedMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getBlockReportCompletedMethod = DatanodeProtocolServiceGrpc.getBlockReportCompletedMethod) == null) {
          DatanodeProtocolServiceGrpc.getBlockReportCompletedMethod = getBlockReportCompletedMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "blockReportCompleted"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("blockReportCompleted"))
              .build();
        }
      }
    }
    return getBlockReportCompletedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> getGetSmallFileDataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getSmallFileData",
      requestType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto,
      com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> getGetSmallFileDataMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> getGetSmallFileDataMethod;
    if ((getGetSmallFileDataMethod = DatanodeProtocolServiceGrpc.getGetSmallFileDataMethod) == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        if ((getGetSmallFileDataMethod = DatanodeProtocolServiceGrpc.getGetSmallFileDataMethod) == null) {
          DatanodeProtocolServiceGrpc.getGetSmallFileDataMethod = getGetSmallFileDataMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto, com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getSmallFileData"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new DatanodeProtocolServiceMethodDescriptorSupplier("getSmallFileData"))
              .build();
        }
      }
    }
    return getGetSmallFileDataMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DatanodeProtocolServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceStub>() {
        @java.lang.Override
        public DatanodeProtocolServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatanodeProtocolServiceStub(channel, callOptions);
        }
      };
    return DatanodeProtocolServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DatanodeProtocolServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceBlockingStub>() {
        @java.lang.Override
        public DatanodeProtocolServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatanodeProtocolServiceBlockingStub(channel, callOptions);
        }
      };
    return DatanodeProtocolServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static DatanodeProtocolServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<DatanodeProtocolServiceFutureStub>() {
        @java.lang.Override
        public DatanodeProtocolServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new DatanodeProtocolServiceFutureStub(channel, callOptions);
        }
      };
    return DatanodeProtocolServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Protocol used from datanode to the namenode
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static abstract class DatanodeProtocolServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Register a datanode at a namenode
     * </pre>
     */
    public void registerDatanode(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterDatanodeMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Send heartbeat from datanode to namenode
     * </pre>
     */
    public void sendHeartbeat(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSendHeartbeatMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Report blocks at a given datanode to the namenode
     * </pre>
     */
    public void blockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockReportMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Report hashes at a given datanode to the namenode
     * </pre>
     */
    public void reportHashes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getReportHashesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Report cached blocks at a datanode to the namenode
     * </pre>
     */
    public void cacheReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCacheReportMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Incremental block report from the DN. This contains info about recently
     * received and deleted blocks, as well as when blocks start being
     * received.
     * </pre>
     */
    public void blockReceivedAndDeleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockReceivedAndDeletedMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Report from a datanode of an error to the active namenode.
     * Used for debugging.
     * </pre>
     */
    public void errorReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getErrorReportMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Request the version
     * </pre>
     */
    public void versionRequest(com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getVersionRequestMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Report corrupt blocks at the specified location
     * </pre>
     */
    public void reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getReportBadBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Commit block synchronization during lease recovery.
     * </pre>
     */
    public void commitBlockSynchronization(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCommitBlockSynchronizationMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * data node sends a request to name node to get list of all active
     * name nodes.
     * </pre>
     */
    public void getActiveNamenodes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetActiveNamenodesMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Ask the leader which namenode the datanode should report to.
     * </pre>
     */
    public void getNextNamenodeToSendBlockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto request,
        io.grpc.stub.StreamObserver<io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNextNamenodeToSendBlockReportMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Request leader NN to mark the block report completed.
     * </pre>
     */
    public void blockReportCompleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getBlockReportCompletedMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Ask a namenode to read the small file data
     * </pre>
     */
    public void getSmallFileData(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetSmallFileDataMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterDatanodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto>(
                  this, METHODID_REGISTER_DATANODE)))
          .addMethod(
            getSendHeartbeatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto>(
                  this, METHODID_SEND_HEARTBEAT)))
          .addMethod(
            getBlockReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>(
                  this, METHODID_BLOCK_REPORT)))
          .addMethod(
            getReportHashesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>(
                  this, METHODID_REPORT_HASHES)))
          .addMethod(
            getCacheReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto>(
                  this, METHODID_CACHE_REPORT)))
          .addMethod(
            getBlockReceivedAndDeletedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto>(
                  this, METHODID_BLOCK_RECEIVED_AND_DELETED)))
          .addMethod(
            getErrorReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto>(
                  this, METHODID_ERROR_REPORT)))
          .addMethod(
            getVersionRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto>(
                  this, METHODID_VERSION_REQUEST)))
          .addMethod(
            getReportBadBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto>(
                  this, METHODID_REPORT_BAD_BLOCKS)))
          .addMethod(
            getCommitBlockSynchronizationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto>(
                  this, METHODID_COMMIT_BLOCK_SYNCHRONIZATION)))
          .addMethod(
            getGetActiveNamenodesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto>(
                  this, METHODID_GET_ACTIVE_NAMENODES)))
          .addMethod(
            getGetNextNamenodeToSendBlockReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto,
                io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto>(
                  this, METHODID_GET_NEXT_NAMENODE_TO_SEND_BLOCK_REPORT)))
          .addMethod(
            getBlockReportCompletedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto>(
                  this, METHODID_BLOCK_REPORT_COMPLETED)))
          .addMethod(
            getGetSmallFileDataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto,
                com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto>(
                  this, METHODID_GET_SMALL_FILE_DATA)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Protocol used from datanode to the namenode
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class DatanodeProtocolServiceStub extends io.grpc.stub.AbstractAsyncStub<DatanodeProtocolServiceStub> {
    private DatanodeProtocolServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeProtocolServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatanodeProtocolServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Register a datanode at a namenode
     * </pre>
     */
    public void registerDatanode(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterDatanodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Send heartbeat from datanode to namenode
     * </pre>
     */
    public void sendHeartbeat(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendHeartbeatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Report blocks at a given datanode to the namenode
     * </pre>
     */
    public void blockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Report hashes at a given datanode to the namenode
     * </pre>
     */
    public void reportHashes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportHashesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Report cached blocks at a datanode to the namenode
     * </pre>
     */
    public void cacheReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCacheReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Incremental block report from the DN. This contains info about recently
     * received and deleted blocks, as well as when blocks start being
     * received.
     * </pre>
     */
    public void blockReceivedAndDeleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockReceivedAndDeletedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Report from a datanode of an error to the active namenode.
     * Used for debugging.
     * </pre>
     */
    public void errorReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getErrorReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Request the version
     * </pre>
     */
    public void versionRequest(com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVersionRequestMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Report corrupt blocks at the specified location
     * </pre>
     */
    public void reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportBadBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Commit block synchronization during lease recovery.
     * </pre>
     */
    public void commitBlockSynchronization(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCommitBlockSynchronizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * data node sends a request to name node to get list of all active
     * name nodes.
     * </pre>
     */
    public void getActiveNamenodes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetActiveNamenodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Ask the leader which namenode the datanode should report to.
     * </pre>
     */
    public void getNextNamenodeToSendBlockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto request,
        io.grpc.stub.StreamObserver<io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNextNamenodeToSendBlockReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Request leader NN to mark the block report completed.
     * </pre>
     */
    public void blockReportCompleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBlockReportCompletedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Ask a namenode to read the small file data
     * </pre>
     */
    public void getSmallFileData(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto request,
        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetSmallFileDataMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Protocol used from datanode to the namenode
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class DatanodeProtocolServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<DatanodeProtocolServiceBlockingStub> {
    private DatanodeProtocolServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeProtocolServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatanodeProtocolServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Register a datanode at a namenode
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto registerDatanode(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRegisterDatanodeMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Send heartbeat from datanode to namenode
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto sendHeartbeat(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSendHeartbeatMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Report blocks at a given datanode to the namenode
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto blockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getBlockReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Report hashes at a given datanode to the namenode
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto reportHashes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getReportHashesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Report cached blocks at a datanode to the namenode
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto cacheReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCacheReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Incremental block report from the DN. This contains info about recently
     * received and deleted blocks, as well as when blocks start being
     * received.
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto blockReceivedAndDeleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getBlockReceivedAndDeletedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Report from a datanode of an error to the active namenode.
     * Used for debugging.
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto errorReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getErrorReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Request the version
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto versionRequest(com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getVersionRequestMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Report corrupt blocks at the specified location
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getReportBadBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Commit block synchronization during lease recovery.
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto commitBlockSynchronization(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCommitBlockSynchronizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * data node sends a request to name node to get list of all active
     * name nodes.
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto getActiveNamenodes(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetActiveNamenodesMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Ask the leader which namenode the datanode should report to.
     * </pre>
     */
    public io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto getNextNamenodeToSendBlockReport(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto request) {
      return blockingUnaryCall(
          getChannel(), getGetNextNamenodeToSendBlockReportMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Request leader NN to mark the block report completed.
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto blockReportCompleted(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getBlockReportCompletedMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Ask a namenode to read the small file data
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto getSmallFileData(com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto request) {
      return blockingUnaryCall(
          getChannel(), getGetSmallFileDataMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Protocol used from datanode to the namenode
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class DatanodeProtocolServiceFutureStub extends io.grpc.stub.AbstractFutureStub<DatanodeProtocolServiceFutureStub> {
    private DatanodeProtocolServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DatanodeProtocolServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new DatanodeProtocolServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Register a datanode at a namenode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto> registerDatanode(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterDatanodeMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Send heartbeat from datanode to namenode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto> sendHeartbeat(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSendHeartbeatMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Report blocks at a given datanode to the namenode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> blockReport(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Report hashes at a given datanode to the namenode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto> reportHashes(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getReportHashesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Report cached blocks at a datanode to the namenode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto> cacheReport(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCacheReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Incremental block report from the DN. This contains info about recently
     * received and deleted blocks, as well as when blocks start being
     * received.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto> blockReceivedAndDeleted(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockReceivedAndDeletedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Report from a datanode of an error to the active namenode.
     * Used for debugging.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto> errorReport(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getErrorReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Request the version
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto> versionRequest(
        com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getVersionRequestMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Report corrupt blocks at the specified location
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto> reportBadBlocks(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getReportBadBlocksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Commit block synchronization during lease recovery.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto> commitBlockSynchronization(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCommitBlockSynchronizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * data node sends a request to name node to get list of all active
     * name nodes.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto> getActiveNamenodes(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetActiveNamenodesMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Ask the leader which namenode the datanode should report to.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto> getNextNamenodeToSendBlockReport(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNextNamenodeToSendBlockReportMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Request leader NN to mark the block report completed.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto> blockReportCompleted(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getBlockReportCompletedMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Ask a namenode to read the small file data
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto> getSmallFileData(
        com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetSmallFileDataMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_DATANODE = 0;
  private static final int METHODID_SEND_HEARTBEAT = 1;
  private static final int METHODID_BLOCK_REPORT = 2;
  private static final int METHODID_REPORT_HASHES = 3;
  private static final int METHODID_CACHE_REPORT = 4;
  private static final int METHODID_BLOCK_RECEIVED_AND_DELETED = 5;
  private static final int METHODID_ERROR_REPORT = 6;
  private static final int METHODID_VERSION_REQUEST = 7;
  private static final int METHODID_REPORT_BAD_BLOCKS = 8;
  private static final int METHODID_COMMIT_BLOCK_SYNCHRONIZATION = 9;
  private static final int METHODID_GET_ACTIVE_NAMENODES = 10;
  private static final int METHODID_GET_NEXT_NAMENODE_TO_SEND_BLOCK_REPORT = 11;
  private static final int METHODID_BLOCK_REPORT_COMPLETED = 12;
  private static final int METHODID_GET_SMALL_FILE_DATA = 13;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DatanodeProtocolServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(DatanodeProtocolServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_DATANODE:
          serviceImpl.registerDatanode((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.RegisterDatanodeResponseProto>) responseObserver);
          break;
        case METHODID_SEND_HEARTBEAT:
          serviceImpl.sendHeartbeat((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.HeartbeatResponseProto>) responseObserver);
          break;
        case METHODID_BLOCK_REPORT:
          serviceImpl.blockReport((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>) responseObserver);
          break;
        case METHODID_REPORT_HASHES:
          serviceImpl.reportHashes((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportResponseProto>) responseObserver);
          break;
        case METHODID_CACHE_REPORT:
          serviceImpl.cacheReport((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CacheReportResponseProto>) responseObserver);
          break;
        case METHODID_BLOCK_RECEIVED_AND_DELETED:
          serviceImpl.blockReceivedAndDeleted((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReceivedAndDeletedResponseProto>) responseObserver);
          break;
        case METHODID_ERROR_REPORT:
          serviceImpl.errorReport((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ErrorReportResponseProto>) responseObserver);
          break;
        case METHODID_VERSION_REQUEST:
          serviceImpl.versionRequest((com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.HdfsProtos.VersionResponseProto>) responseObserver);
          break;
        case METHODID_REPORT_BAD_BLOCKS:
          serviceImpl.reportBadBlocks((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ReportBadBlocksResponseProto>) responseObserver);
          break;
        case METHODID_COMMIT_BLOCK_SYNCHRONIZATION:
          serviceImpl.commitBlockSynchronization((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.CommitBlockSynchronizationResponseProto>) responseObserver);
          break;
        case METHODID_GET_ACTIVE_NAMENODES:
          serviceImpl.getActiveNamenodes((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.ActiveNamenodeListResponseProto>) responseObserver);
          break;
        case METHODID_GET_NEXT_NAMENODE_TO_SEND_BLOCK_REPORT:
          serviceImpl.getNextNamenodeToSendBlockReport((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.NameNodeAddressRequestForBlockReportingProto) request,
              (io.grpc.stub.StreamObserver<io.hops.leader_election.proto.ActiveNodeProtos.ActiveNodeProto>) responseObserver);
          break;
        case METHODID_BLOCK_REPORT_COMPLETED:
          serviceImpl.blockReportCompleted((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.BlockReportCompletedResponseProto>) responseObserver);
          break;
        case METHODID_GET_SMALL_FILE_DATA:
          serviceImpl.getSmallFileData((com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.GetSmallFileDataProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.SmallFileDataResponseProto>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class DatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    DatanodeProtocolServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gmail.benrcarver.serverlessnamenode.protocol.DatanodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("DatanodeProtocolService");
    }
  }

  private static final class DatanodeProtocolServiceFileDescriptorSupplier
      extends DatanodeProtocolServiceBaseDescriptorSupplier {
    DatanodeProtocolServiceFileDescriptorSupplier() {}
  }

  private static final class DatanodeProtocolServiceMethodDescriptorSupplier
      extends DatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    DatanodeProtocolServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (DatanodeProtocolServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new DatanodeProtocolServiceFileDescriptorSupplier())
              .addMethod(getRegisterDatanodeMethod())
              .addMethod(getSendHeartbeatMethod())
              .addMethod(getBlockReportMethod())
              .addMethod(getReportHashesMethod())
              .addMethod(getCacheReportMethod())
              .addMethod(getBlockReceivedAndDeletedMethod())
              .addMethod(getErrorReportMethod())
              .addMethod(getVersionRequestMethod())
              .addMethod(getReportBadBlocksMethod())
              .addMethod(getCommitBlockSynchronizationMethod())
              .addMethod(getGetActiveNamenodesMethod())
              .addMethod(getGetNextNamenodeToSendBlockReportMethod())
              .addMethod(getBlockReportCompletedMethod())
              .addMethod(getGetSmallFileDataMethod())
              .build();
        }
      }
    }
    return result;
  }
}