package org.apache.hadoop.hdfs.protocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: ClientNamenodeProtocol.proto")
public final class ClientNamenodeProtocolGrpc {

  private ClientNamenodeProtocolGrpc() {}

  public static final String SERVICE_NAME = "com.gmail.benrcarver.serverlessnamenode.ClientNamenodeProtocol";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> getGetBlockLocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockLocations",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> getGetBlockLocationsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> getGetBlockLocationsMethod;
    if ((getGetBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetBlockLocationsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetBlockLocationsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetBlockLocationsMethod = getGetBlockLocationsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlockLocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getBlockLocations"))
              .build();
        }
      }
    }
    return getGetBlockLocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> getGetMissingBlockLocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getMissingBlockLocations",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> getGetMissingBlockLocationsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> getGetMissingBlockLocationsMethod;
    if ((getGetMissingBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetMissingBlockLocationsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetMissingBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetMissingBlockLocationsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetMissingBlockLocationsMethod = getGetMissingBlockLocationsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getMissingBlockLocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getMissingBlockLocations"))
              .build();
        }
      }
    }
    return getGetMissingBlockLocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> getAddBlockChecksumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addBlockChecksum",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> getAddBlockChecksumMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> getAddBlockChecksumMethod;
    if ((getAddBlockChecksumMethod = ClientNamenodeProtocolGrpc.getAddBlockChecksumMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddBlockChecksumMethod = ClientNamenodeProtocolGrpc.getAddBlockChecksumMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddBlockChecksumMethod = getAddBlockChecksumMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addBlockChecksum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addBlockChecksum"))
              .build();
        }
      }
    }
    return getAddBlockChecksumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> getGetBlockChecksumMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockChecksum",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> getGetBlockChecksumMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> getGetBlockChecksumMethod;
    if ((getGetBlockChecksumMethod = ClientNamenodeProtocolGrpc.getGetBlockChecksumMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetBlockChecksumMethod = ClientNamenodeProtocolGrpc.getGetBlockChecksumMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetBlockChecksumMethod = getGetBlockChecksumMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlockChecksum"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getBlockChecksum"))
              .build();
        }
      }
    }
    return getGetBlockChecksumMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> getGetServerDefaultsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getServerDefaults",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> getGetServerDefaultsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> getGetServerDefaultsMethod;
    if ((getGetServerDefaultsMethod = ClientNamenodeProtocolGrpc.getGetServerDefaultsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetServerDefaultsMethod = ClientNamenodeProtocolGrpc.getGetServerDefaultsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetServerDefaultsMethod = getGetServerDefaultsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getServerDefaults"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getServerDefaults"))
              .build();
        }
      }
    }
    return getGetServerDefaultsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> getCreateMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> getCreateMethod;
    if ((getCreateMethod = ClientNamenodeProtocolGrpc.getCreateMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCreateMethod = ClientNamenodeProtocolGrpc.getCreateMethod) == null) {
          ClientNamenodeProtocolGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> getAppendMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "append",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> getAppendMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> getAppendMethod;
    if ((getAppendMethod = ClientNamenodeProtocolGrpc.getAppendMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAppendMethod = ClientNamenodeProtocolGrpc.getAppendMethod) == null) {
          ClientNamenodeProtocolGrpc.getAppendMethod = getAppendMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "append"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("append"))
              .build();
        }
      }
    }
    return getAppendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> getSetReplicationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setReplication",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> getSetReplicationMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> getSetReplicationMethod;
    if ((getSetReplicationMethod = ClientNamenodeProtocolGrpc.getSetReplicationMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetReplicationMethod = ClientNamenodeProtocolGrpc.getSetReplicationMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetReplicationMethod = getSetReplicationMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setReplication"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setReplication"))
              .build();
        }
      }
    }
    return getSetReplicationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> getSetStoragePolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setStoragePolicy",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> getSetStoragePolicyMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> getSetStoragePolicyMethod;
    if ((getSetStoragePolicyMethod = ClientNamenodeProtocolGrpc.getSetStoragePolicyMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetStoragePolicyMethod = ClientNamenodeProtocolGrpc.getSetStoragePolicyMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetStoragePolicyMethod = getSetStoragePolicyMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setStoragePolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setStoragePolicy"))
              .build();
        }
      }
    }
    return getSetStoragePolicyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> getGetStoragePolicyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStoragePolicy",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> getGetStoragePolicyMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> getGetStoragePolicyMethod;
    if ((getGetStoragePolicyMethod = ClientNamenodeProtocolGrpc.getGetStoragePolicyMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetStoragePolicyMethod = ClientNamenodeProtocolGrpc.getGetStoragePolicyMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetStoragePolicyMethod = getGetStoragePolicyMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getStoragePolicy"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getStoragePolicy"))
              .build();
        }
      }
    }
    return getGetStoragePolicyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> getGetStoragePoliciesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getStoragePolicies",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> getGetStoragePoliciesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> getGetStoragePoliciesMethod;
    if ((getGetStoragePoliciesMethod = ClientNamenodeProtocolGrpc.getGetStoragePoliciesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetStoragePoliciesMethod = ClientNamenodeProtocolGrpc.getGetStoragePoliciesMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetStoragePoliciesMethod = getGetStoragePoliciesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getStoragePolicies"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getStoragePolicies"))
              .build();
        }
      }
    }
    return getGetStoragePoliciesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> getSetMetaStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setMetaStatus",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> getSetMetaStatusMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> getSetMetaStatusMethod;
    if ((getSetMetaStatusMethod = ClientNamenodeProtocolGrpc.getSetMetaStatusMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetMetaStatusMethod = ClientNamenodeProtocolGrpc.getSetMetaStatusMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetMetaStatusMethod = getSetMetaStatusMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setMetaStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setMetaStatus"))
              .build();
        }
      }
    }
    return getSetMetaStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> getSetPermissionMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setPermission",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> getSetPermissionMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> getSetPermissionMethod;
    if ((getSetPermissionMethod = ClientNamenodeProtocolGrpc.getSetPermissionMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetPermissionMethod = ClientNamenodeProtocolGrpc.getSetPermissionMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetPermissionMethod = getSetPermissionMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setPermission"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setPermission"))
              .build();
        }
      }
    }
    return getSetPermissionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> getSetOwnerMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setOwner",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> getSetOwnerMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> getSetOwnerMethod;
    if ((getSetOwnerMethod = ClientNamenodeProtocolGrpc.getSetOwnerMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetOwnerMethod = ClientNamenodeProtocolGrpc.getSetOwnerMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetOwnerMethod = getSetOwnerMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setOwner"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setOwner"))
              .build();
        }
      }
    }
    return getSetOwnerMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> getAbandonBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "abandonBlock",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> getAbandonBlockMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> getAbandonBlockMethod;
    if ((getAbandonBlockMethod = ClientNamenodeProtocolGrpc.getAbandonBlockMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAbandonBlockMethod = ClientNamenodeProtocolGrpc.getAbandonBlockMethod) == null) {
          ClientNamenodeProtocolGrpc.getAbandonBlockMethod = getAbandonBlockMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "abandonBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("abandonBlock"))
              .build();
        }
      }
    }
    return getAbandonBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> getAddBlockMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addBlock",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> getAddBlockMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> getAddBlockMethod;
    if ((getAddBlockMethod = ClientNamenodeProtocolGrpc.getAddBlockMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddBlockMethod = ClientNamenodeProtocolGrpc.getAddBlockMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddBlockMethod = getAddBlockMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addBlock"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addBlock"))
              .build();
        }
      }
    }
    return getAddBlockMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> getGetAdditionalDatanodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAdditionalDatanode",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> getGetAdditionalDatanodeMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> getGetAdditionalDatanodeMethod;
    if ((getGetAdditionalDatanodeMethod = ClientNamenodeProtocolGrpc.getGetAdditionalDatanodeMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetAdditionalDatanodeMethod = ClientNamenodeProtocolGrpc.getGetAdditionalDatanodeMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetAdditionalDatanodeMethod = getGetAdditionalDatanodeMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAdditionalDatanode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getAdditionalDatanode"))
              .build();
        }
      }
    }
    return getGetAdditionalDatanodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> getCompleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "complete",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> getCompleteMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> getCompleteMethod;
    if ((getCompleteMethod = ClientNamenodeProtocolGrpc.getCompleteMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCompleteMethod = ClientNamenodeProtocolGrpc.getCompleteMethod) == null) {
          ClientNamenodeProtocolGrpc.getCompleteMethod = getCompleteMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "complete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("complete"))
              .build();
        }
      }
    }
    return getCompleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "reportBadBlocks",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> getReportBadBlocksMethod;
    if ((getReportBadBlocksMethod = ClientNamenodeProtocolGrpc.getReportBadBlocksMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getReportBadBlocksMethod = ClientNamenodeProtocolGrpc.getReportBadBlocksMethod) == null) {
          ClientNamenodeProtocolGrpc.getReportBadBlocksMethod = getReportBadBlocksMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "reportBadBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("reportBadBlocks"))
              .build();
        }
      }
    }
    return getReportBadBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> getConcatMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "concat",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> getConcatMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> getConcatMethod;
    if ((getConcatMethod = ClientNamenodeProtocolGrpc.getConcatMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getConcatMethod = ClientNamenodeProtocolGrpc.getConcatMethod) == null) {
          ClientNamenodeProtocolGrpc.getConcatMethod = getConcatMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "concat"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("concat"))
              .build();
        }
      }
    }
    return getConcatMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> getTruncateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "truncate",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> getTruncateMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> getTruncateMethod;
    if ((getTruncateMethod = ClientNamenodeProtocolGrpc.getTruncateMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getTruncateMethod = ClientNamenodeProtocolGrpc.getTruncateMethod) == null) {
          ClientNamenodeProtocolGrpc.getTruncateMethod = getTruncateMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "truncate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("truncate"))
              .build();
        }
      }
    }
    return getTruncateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> getRenameMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rename",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> getRenameMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> getRenameMethod;
    if ((getRenameMethod = ClientNamenodeProtocolGrpc.getRenameMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRenameMethod = ClientNamenodeProtocolGrpc.getRenameMethod) == null) {
          ClientNamenodeProtocolGrpc.getRenameMethod = getRenameMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rename"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("rename"))
              .build();
        }
      }
    }
    return getRenameMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> getRename2Method;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rename2",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> getRename2Method() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> getRename2Method;
    if ((getRename2Method = ClientNamenodeProtocolGrpc.getRename2Method) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRename2Method = ClientNamenodeProtocolGrpc.getRename2Method) == null) {
          ClientNamenodeProtocolGrpc.getRename2Method = getRename2Method =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rename2"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("rename2"))
              .build();
        }
      }
    }
    return getRename2Method;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> getDeleteMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> getDeleteMethod;
    if ((getDeleteMethod = ClientNamenodeProtocolGrpc.getDeleteMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getDeleteMethod = ClientNamenodeProtocolGrpc.getDeleteMethod) == null) {
          ClientNamenodeProtocolGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> getMkdirsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "mkdirs",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> getMkdirsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> getMkdirsMethod;
    if ((getMkdirsMethod = ClientNamenodeProtocolGrpc.getMkdirsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getMkdirsMethod = ClientNamenodeProtocolGrpc.getMkdirsMethod) == null) {
          ClientNamenodeProtocolGrpc.getMkdirsMethod = getMkdirsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "mkdirs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("mkdirs"))
              .build();
        }
      }
    }
    return getMkdirsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> getGetListingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getListing",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> getGetListingMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> getGetListingMethod;
    if ((getGetListingMethod = ClientNamenodeProtocolGrpc.getGetListingMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetListingMethod = ClientNamenodeProtocolGrpc.getGetListingMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetListingMethod = getGetListingMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getListing"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getListing"))
              .build();
        }
      }
    }
    return getGetListingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> getRenewLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "renewLease",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> getRenewLeaseMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> getRenewLeaseMethod;
    if ((getRenewLeaseMethod = ClientNamenodeProtocolGrpc.getRenewLeaseMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRenewLeaseMethod = ClientNamenodeProtocolGrpc.getRenewLeaseMethod) == null) {
          ClientNamenodeProtocolGrpc.getRenewLeaseMethod = getRenewLeaseMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "renewLease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("renewLease"))
              .build();
        }
      }
    }
    return getRenewLeaseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> getRecoverLeaseMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "recoverLease",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> getRecoverLeaseMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> getRecoverLeaseMethod;
    if ((getRecoverLeaseMethod = ClientNamenodeProtocolGrpc.getRecoverLeaseMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRecoverLeaseMethod = ClientNamenodeProtocolGrpc.getRecoverLeaseMethod) == null) {
          ClientNamenodeProtocolGrpc.getRecoverLeaseMethod = getRecoverLeaseMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "recoverLease"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("recoverLease"))
              .build();
        }
      }
    }
    return getRecoverLeaseMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> getGetFsStatsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFsStats",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> getGetFsStatsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> getGetFsStatsMethod;
    if ((getGetFsStatsMethod = ClientNamenodeProtocolGrpc.getGetFsStatsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetFsStatsMethod = ClientNamenodeProtocolGrpc.getGetFsStatsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetFsStatsMethod = getGetFsStatsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFsStats"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getFsStats"))
              .build();
        }
      }
    }
    return getGetFsStatsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> getGetDatanodeReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDatanodeReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> getGetDatanodeReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> getGetDatanodeReportMethod;
    if ((getGetDatanodeReportMethod = ClientNamenodeProtocolGrpc.getGetDatanodeReportMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetDatanodeReportMethod = ClientNamenodeProtocolGrpc.getGetDatanodeReportMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetDatanodeReportMethod = getGetDatanodeReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDatanodeReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getDatanodeReport"))
              .build();
        }
      }
    }
    return getGetDatanodeReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> getGetDatanodeStorageReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDatanodeStorageReport",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> getGetDatanodeStorageReportMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> getGetDatanodeStorageReportMethod;
    if ((getGetDatanodeStorageReportMethod = ClientNamenodeProtocolGrpc.getGetDatanodeStorageReportMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetDatanodeStorageReportMethod = ClientNamenodeProtocolGrpc.getGetDatanodeStorageReportMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetDatanodeStorageReportMethod = getGetDatanodeStorageReportMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDatanodeStorageReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getDatanodeStorageReport"))
              .build();
        }
      }
    }
    return getGetDatanodeStorageReportMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> getGetPreferredBlockSizeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getPreferredBlockSize",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> getGetPreferredBlockSizeMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> getGetPreferredBlockSizeMethod;
    if ((getGetPreferredBlockSizeMethod = ClientNamenodeProtocolGrpc.getGetPreferredBlockSizeMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetPreferredBlockSizeMethod = ClientNamenodeProtocolGrpc.getGetPreferredBlockSizeMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetPreferredBlockSizeMethod = getGetPreferredBlockSizeMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getPreferredBlockSize"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getPreferredBlockSize"))
              .build();
        }
      }
    }
    return getGetPreferredBlockSizeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> getSetSafeModeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setSafeMode",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> getSetSafeModeMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> getSetSafeModeMethod;
    if ((getSetSafeModeMethod = ClientNamenodeProtocolGrpc.getSetSafeModeMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetSafeModeMethod = ClientNamenodeProtocolGrpc.getSetSafeModeMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetSafeModeMethod = getSetSafeModeMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setSafeMode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setSafeMode"))
              .build();
        }
      }
    }
    return getSetSafeModeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> getRefreshNodesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "refreshNodes",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> getRefreshNodesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> getRefreshNodesMethod;
    if ((getRefreshNodesMethod = ClientNamenodeProtocolGrpc.getRefreshNodesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRefreshNodesMethod = ClientNamenodeProtocolGrpc.getRefreshNodesMethod) == null) {
          ClientNamenodeProtocolGrpc.getRefreshNodesMethod = getRefreshNodesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "refreshNodes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("refreshNodes"))
              .build();
        }
      }
    }
    return getRefreshNodesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> getRollingUpgradeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "rollingUpgrade",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> getRollingUpgradeMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> getRollingUpgradeMethod;
    if ((getRollingUpgradeMethod = ClientNamenodeProtocolGrpc.getRollingUpgradeMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRollingUpgradeMethod = ClientNamenodeProtocolGrpc.getRollingUpgradeMethod) == null) {
          ClientNamenodeProtocolGrpc.getRollingUpgradeMethod = getRollingUpgradeMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "rollingUpgrade"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("rollingUpgrade"))
              .build();
        }
      }
    }
    return getRollingUpgradeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> getListCorruptFileBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listCorruptFileBlocks",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> getListCorruptFileBlocksMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> getListCorruptFileBlocksMethod;
    if ((getListCorruptFileBlocksMethod = ClientNamenodeProtocolGrpc.getListCorruptFileBlocksMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getListCorruptFileBlocksMethod = ClientNamenodeProtocolGrpc.getListCorruptFileBlocksMethod) == null) {
          ClientNamenodeProtocolGrpc.getListCorruptFileBlocksMethod = getListCorruptFileBlocksMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listCorruptFileBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("listCorruptFileBlocks"))
              .build();
        }
      }
    }
    return getListCorruptFileBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> getGetFileInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFileInfo",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> getGetFileInfoMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> getGetFileInfoMethod;
    if ((getGetFileInfoMethod = ClientNamenodeProtocolGrpc.getGetFileInfoMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetFileInfoMethod = ClientNamenodeProtocolGrpc.getGetFileInfoMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetFileInfoMethod = getGetFileInfoMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFileInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getFileInfo"))
              .build();
        }
      }
    }
    return getGetFileInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> getAddCacheDirectiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addCacheDirective",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> getAddCacheDirectiveMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> getAddCacheDirectiveMethod;
    if ((getAddCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getAddCacheDirectiveMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getAddCacheDirectiveMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddCacheDirectiveMethod = getAddCacheDirectiveMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addCacheDirective"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addCacheDirective"))
              .build();
        }
      }
    }
    return getAddCacheDirectiveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> getModifyCacheDirectiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "modifyCacheDirective",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> getModifyCacheDirectiveMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> getModifyCacheDirectiveMethod;
    if ((getModifyCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getModifyCacheDirectiveMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getModifyCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getModifyCacheDirectiveMethod) == null) {
          ClientNamenodeProtocolGrpc.getModifyCacheDirectiveMethod = getModifyCacheDirectiveMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "modifyCacheDirective"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("modifyCacheDirective"))
              .build();
        }
      }
    }
    return getModifyCacheDirectiveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> getRemoveCacheDirectiveMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeCacheDirective",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> getRemoveCacheDirectiveMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> getRemoveCacheDirectiveMethod;
    if ((getRemoveCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getRemoveCacheDirectiveMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveCacheDirectiveMethod = ClientNamenodeProtocolGrpc.getRemoveCacheDirectiveMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveCacheDirectiveMethod = getRemoveCacheDirectiveMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeCacheDirective"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeCacheDirective"))
              .build();
        }
      }
    }
    return getRemoveCacheDirectiveMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> getListCacheDirectivesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listCacheDirectives",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> getListCacheDirectivesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> getListCacheDirectivesMethod;
    if ((getListCacheDirectivesMethod = ClientNamenodeProtocolGrpc.getListCacheDirectivesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getListCacheDirectivesMethod = ClientNamenodeProtocolGrpc.getListCacheDirectivesMethod) == null) {
          ClientNamenodeProtocolGrpc.getListCacheDirectivesMethod = getListCacheDirectivesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listCacheDirectives"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("listCacheDirectives"))
              .build();
        }
      }
    }
    return getListCacheDirectivesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> getAddCachePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addCachePool",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> getAddCachePoolMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> getAddCachePoolMethod;
    if ((getAddCachePoolMethod = ClientNamenodeProtocolGrpc.getAddCachePoolMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddCachePoolMethod = ClientNamenodeProtocolGrpc.getAddCachePoolMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddCachePoolMethod = getAddCachePoolMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addCachePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addCachePool"))
              .build();
        }
      }
    }
    return getAddCachePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> getModifyCachePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "modifyCachePool",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> getModifyCachePoolMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> getModifyCachePoolMethod;
    if ((getModifyCachePoolMethod = ClientNamenodeProtocolGrpc.getModifyCachePoolMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getModifyCachePoolMethod = ClientNamenodeProtocolGrpc.getModifyCachePoolMethod) == null) {
          ClientNamenodeProtocolGrpc.getModifyCachePoolMethod = getModifyCachePoolMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "modifyCachePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("modifyCachePool"))
              .build();
        }
      }
    }
    return getModifyCachePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> getRemoveCachePoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeCachePool",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> getRemoveCachePoolMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> getRemoveCachePoolMethod;
    if ((getRemoveCachePoolMethod = ClientNamenodeProtocolGrpc.getRemoveCachePoolMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveCachePoolMethod = ClientNamenodeProtocolGrpc.getRemoveCachePoolMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveCachePoolMethod = getRemoveCachePoolMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeCachePool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeCachePool"))
              .build();
        }
      }
    }
    return getRemoveCachePoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> getListCachePoolsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listCachePools",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> getListCachePoolsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> getListCachePoolsMethod;
    if ((getListCachePoolsMethod = ClientNamenodeProtocolGrpc.getListCachePoolsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getListCachePoolsMethod = ClientNamenodeProtocolGrpc.getListCachePoolsMethod) == null) {
          ClientNamenodeProtocolGrpc.getListCachePoolsMethod = getListCachePoolsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listCachePools"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("listCachePools"))
              .build();
        }
      }
    }
    return getListCachePoolsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> getGetFileLinkInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getFileLinkInfo",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> getGetFileLinkInfoMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> getGetFileLinkInfoMethod;
    if ((getGetFileLinkInfoMethod = ClientNamenodeProtocolGrpc.getGetFileLinkInfoMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetFileLinkInfoMethod = ClientNamenodeProtocolGrpc.getGetFileLinkInfoMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetFileLinkInfoMethod = getGetFileLinkInfoMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getFileLinkInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getFileLinkInfo"))
              .build();
        }
      }
    }
    return getGetFileLinkInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> getGetContentSummaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getContentSummary",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> getGetContentSummaryMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> getGetContentSummaryMethod;
    if ((getGetContentSummaryMethod = ClientNamenodeProtocolGrpc.getGetContentSummaryMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetContentSummaryMethod = ClientNamenodeProtocolGrpc.getGetContentSummaryMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetContentSummaryMethod = getGetContentSummaryMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getContentSummary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getContentSummary"))
              .build();
        }
      }
    }
    return getGetContentSummaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> getSetQuotaMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setQuota",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> getSetQuotaMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> getSetQuotaMethod;
    if ((getSetQuotaMethod = ClientNamenodeProtocolGrpc.getSetQuotaMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetQuotaMethod = ClientNamenodeProtocolGrpc.getSetQuotaMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetQuotaMethod = getSetQuotaMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setQuota"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setQuota"))
              .build();
        }
      }
    }
    return getSetQuotaMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> getFsyncMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "fsync",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> getFsyncMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> getFsyncMethod;
    if ((getFsyncMethod = ClientNamenodeProtocolGrpc.getFsyncMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getFsyncMethod = ClientNamenodeProtocolGrpc.getFsyncMethod) == null) {
          ClientNamenodeProtocolGrpc.getFsyncMethod = getFsyncMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "fsync"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("fsync"))
              .build();
        }
      }
    }
    return getFsyncMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> getSetTimesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setTimes",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> getSetTimesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> getSetTimesMethod;
    if ((getSetTimesMethod = ClientNamenodeProtocolGrpc.getSetTimesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetTimesMethod = ClientNamenodeProtocolGrpc.getSetTimesMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetTimesMethod = getSetTimesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setTimes"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setTimes"))
              .build();
        }
      }
    }
    return getSetTimesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> getCreateSymlinkMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createSymlink",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> getCreateSymlinkMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> getCreateSymlinkMethod;
    if ((getCreateSymlinkMethod = ClientNamenodeProtocolGrpc.getCreateSymlinkMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCreateSymlinkMethod = ClientNamenodeProtocolGrpc.getCreateSymlinkMethod) == null) {
          ClientNamenodeProtocolGrpc.getCreateSymlinkMethod = getCreateSymlinkMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createSymlink"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("createSymlink"))
              .build();
        }
      }
    }
    return getCreateSymlinkMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> getGetLinkTargetMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getLinkTarget",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> getGetLinkTargetMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> getGetLinkTargetMethod;
    if ((getGetLinkTargetMethod = ClientNamenodeProtocolGrpc.getGetLinkTargetMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetLinkTargetMethod = ClientNamenodeProtocolGrpc.getGetLinkTargetMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetLinkTargetMethod = getGetLinkTargetMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getLinkTarget"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getLinkTarget"))
              .build();
        }
      }
    }
    return getGetLinkTargetMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> getUpdateBlockForPipelineMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateBlockForPipeline",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> getUpdateBlockForPipelineMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> getUpdateBlockForPipelineMethod;
    if ((getUpdateBlockForPipelineMethod = ClientNamenodeProtocolGrpc.getUpdateBlockForPipelineMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getUpdateBlockForPipelineMethod = ClientNamenodeProtocolGrpc.getUpdateBlockForPipelineMethod) == null) {
          ClientNamenodeProtocolGrpc.getUpdateBlockForPipelineMethod = getUpdateBlockForPipelineMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateBlockForPipeline"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("updateBlockForPipeline"))
              .build();
        }
      }
    }
    return getUpdateBlockForPipelineMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> getUpdatePipelineMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updatePipeline",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> getUpdatePipelineMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> getUpdatePipelineMethod;
    if ((getUpdatePipelineMethod = ClientNamenodeProtocolGrpc.getUpdatePipelineMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getUpdatePipelineMethod = ClientNamenodeProtocolGrpc.getUpdatePipelineMethod) == null) {
          ClientNamenodeProtocolGrpc.getUpdatePipelineMethod = getUpdatePipelineMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updatePipeline"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("updatePipeline"))
              .build();
        }
      }
    }
    return getUpdatePipelineMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> getGetDelegationTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDelegationToken",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> getGetDelegationTokenMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> getGetDelegationTokenMethod;
    if ((getGetDelegationTokenMethod = ClientNamenodeProtocolGrpc.getGetDelegationTokenMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetDelegationTokenMethod = ClientNamenodeProtocolGrpc.getGetDelegationTokenMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetDelegationTokenMethod = getGetDelegationTokenMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDelegationToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getDelegationToken"))
              .build();
        }
      }
    }
    return getGetDelegationTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> getRenewDelegationTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "renewDelegationToken",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> getRenewDelegationTokenMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> getRenewDelegationTokenMethod;
    if ((getRenewDelegationTokenMethod = ClientNamenodeProtocolGrpc.getRenewDelegationTokenMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRenewDelegationTokenMethod = ClientNamenodeProtocolGrpc.getRenewDelegationTokenMethod) == null) {
          ClientNamenodeProtocolGrpc.getRenewDelegationTokenMethod = getRenewDelegationTokenMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "renewDelegationToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("renewDelegationToken"))
              .build();
        }
      }
    }
    return getRenewDelegationTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> getCancelDelegationTokenMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cancelDelegationToken",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> getCancelDelegationTokenMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> getCancelDelegationTokenMethod;
    if ((getCancelDelegationTokenMethod = ClientNamenodeProtocolGrpc.getCancelDelegationTokenMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCancelDelegationTokenMethod = ClientNamenodeProtocolGrpc.getCancelDelegationTokenMethod) == null) {
          ClientNamenodeProtocolGrpc.getCancelDelegationTokenMethod = getCancelDelegationTokenMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cancelDelegationToken"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("cancelDelegationToken"))
              .build();
        }
      }
    }
    return getCancelDelegationTokenMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> getSetBalancerBandwidthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setBalancerBandwidth",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> getSetBalancerBandwidthMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> getSetBalancerBandwidthMethod;
    if ((getSetBalancerBandwidthMethod = ClientNamenodeProtocolGrpc.getSetBalancerBandwidthMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetBalancerBandwidthMethod = ClientNamenodeProtocolGrpc.getSetBalancerBandwidthMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetBalancerBandwidthMethod = getSetBalancerBandwidthMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setBalancerBandwidth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setBalancerBandwidth"))
              .build();
        }
      }
    }
    return getSetBalancerBandwidthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> getGetDataEncryptionKeyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDataEncryptionKey",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> getGetDataEncryptionKeyMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> getGetDataEncryptionKeyMethod;
    if ((getGetDataEncryptionKeyMethod = ClientNamenodeProtocolGrpc.getGetDataEncryptionKeyMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetDataEncryptionKeyMethod = ClientNamenodeProtocolGrpc.getGetDataEncryptionKeyMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetDataEncryptionKeyMethod = getGetDataEncryptionKeyMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDataEncryptionKey"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getDataEncryptionKey"))
              .build();
        }
      }
    }
    return getGetDataEncryptionKeyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> getIsFileClosedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "isFileClosed",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> getIsFileClosedMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> getIsFileClosedMethod;
    if ((getIsFileClosedMethod = ClientNamenodeProtocolGrpc.getIsFileClosedMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getIsFileClosedMethod = ClientNamenodeProtocolGrpc.getIsFileClosedMethod) == null) {
          ClientNamenodeProtocolGrpc.getIsFileClosedMethod = getIsFileClosedMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "isFileClosed"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("isFileClosed"))
              .build();
        }
      }
    }
    return getIsFileClosedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> getModifyAclEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "modifyAclEntries",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> getModifyAclEntriesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> getModifyAclEntriesMethod;
    if ((getModifyAclEntriesMethod = ClientNamenodeProtocolGrpc.getModifyAclEntriesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getModifyAclEntriesMethod = ClientNamenodeProtocolGrpc.getModifyAclEntriesMethod) == null) {
          ClientNamenodeProtocolGrpc.getModifyAclEntriesMethod = getModifyAclEntriesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "modifyAclEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("modifyAclEntries"))
              .build();
        }
      }
    }
    return getModifyAclEntriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> getRemoveAclEntriesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeAclEntries",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> getRemoveAclEntriesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> getRemoveAclEntriesMethod;
    if ((getRemoveAclEntriesMethod = ClientNamenodeProtocolGrpc.getRemoveAclEntriesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveAclEntriesMethod = ClientNamenodeProtocolGrpc.getRemoveAclEntriesMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveAclEntriesMethod = getRemoveAclEntriesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeAclEntries"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeAclEntries"))
              .build();
        }
      }
    }
    return getRemoveAclEntriesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> getRemoveDefaultAclMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeDefaultAcl",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> getRemoveDefaultAclMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> getRemoveDefaultAclMethod;
    if ((getRemoveDefaultAclMethod = ClientNamenodeProtocolGrpc.getRemoveDefaultAclMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveDefaultAclMethod = ClientNamenodeProtocolGrpc.getRemoveDefaultAclMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveDefaultAclMethod = getRemoveDefaultAclMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeDefaultAcl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeDefaultAcl"))
              .build();
        }
      }
    }
    return getRemoveDefaultAclMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> getRemoveAclMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeAcl",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> getRemoveAclMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> getRemoveAclMethod;
    if ((getRemoveAclMethod = ClientNamenodeProtocolGrpc.getRemoveAclMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveAclMethod = ClientNamenodeProtocolGrpc.getRemoveAclMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveAclMethod = getRemoveAclMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeAcl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeAcl"))
              .build();
        }
      }
    }
    return getRemoveAclMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> getSetAclMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setAcl",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> getSetAclMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> getSetAclMethod;
    if ((getSetAclMethod = ClientNamenodeProtocolGrpc.getSetAclMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetAclMethod = ClientNamenodeProtocolGrpc.getSetAclMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetAclMethod = getSetAclMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setAcl"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setAcl"))
              .build();
        }
      }
    }
    return getSetAclMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> getGetAclStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getAclStatus",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> getGetAclStatusMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> getGetAclStatusMethod;
    if ((getGetAclStatusMethod = ClientNamenodeProtocolGrpc.getGetAclStatusMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetAclStatusMethod = ClientNamenodeProtocolGrpc.getGetAclStatusMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetAclStatusMethod = getGetAclStatusMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getAclStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getAclStatus"))
              .build();
        }
      }
    }
    return getGetAclStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> getSetXAttrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "setXAttr",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> getSetXAttrMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> getSetXAttrMethod;
    if ((getSetXAttrMethod = ClientNamenodeProtocolGrpc.getSetXAttrMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getSetXAttrMethod = ClientNamenodeProtocolGrpc.getSetXAttrMethod) == null) {
          ClientNamenodeProtocolGrpc.getSetXAttrMethod = getSetXAttrMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "setXAttr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("setXAttr"))
              .build();
        }
      }
    }
    return getSetXAttrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> getGetXAttrsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getXAttrs",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> getGetXAttrsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> getGetXAttrsMethod;
    if ((getGetXAttrsMethod = ClientNamenodeProtocolGrpc.getGetXAttrsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetXAttrsMethod = ClientNamenodeProtocolGrpc.getGetXAttrsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetXAttrsMethod = getGetXAttrsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getXAttrs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getXAttrs"))
              .build();
        }
      }
    }
    return getGetXAttrsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> getListXAttrsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listXAttrs",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> getListXAttrsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> getListXAttrsMethod;
    if ((getListXAttrsMethod = ClientNamenodeProtocolGrpc.getListXAttrsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getListXAttrsMethod = ClientNamenodeProtocolGrpc.getListXAttrsMethod) == null) {
          ClientNamenodeProtocolGrpc.getListXAttrsMethod = getListXAttrsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listXAttrs"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("listXAttrs"))
              .build();
        }
      }
    }
    return getListXAttrsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> getRemoveXAttrMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeXAttr",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> getRemoveXAttrMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> getRemoveXAttrMethod;
    if ((getRemoveXAttrMethod = ClientNamenodeProtocolGrpc.getRemoveXAttrMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveXAttrMethod = ClientNamenodeProtocolGrpc.getRemoveXAttrMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveXAttrMethod = getRemoveXAttrMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeXAttr"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeXAttr"))
              .build();
        }
      }
    }
    return getRemoveXAttrMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesForClientMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getActiveNamenodesForClient",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesForClientMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> getGetActiveNamenodesForClientMethod;
    if ((getGetActiveNamenodesForClientMethod = ClientNamenodeProtocolGrpc.getGetActiveNamenodesForClientMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetActiveNamenodesForClientMethod = ClientNamenodeProtocolGrpc.getGetActiveNamenodesForClientMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetActiveNamenodesForClientMethod = getGetActiveNamenodesForClientMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getActiveNamenodesForClient"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getActiveNamenodesForClient"))
              .build();
        }
      }
    }
    return getGetActiveNamenodesForClientMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> getPingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ping",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> getPingMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> getPingMethod;
    if ((getPingMethod = ClientNamenodeProtocolGrpc.getPingMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getPingMethod = ClientNamenodeProtocolGrpc.getPingMethod) == null) {
          ClientNamenodeProtocolGrpc.getPingMethod = getPingMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ping"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("ping"))
              .build();
        }
      }
    }
    return getPingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> getGetEncodingStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getEncodingStatus",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> getGetEncodingStatusMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> getGetEncodingStatusMethod;
    if ((getGetEncodingStatusMethod = ClientNamenodeProtocolGrpc.getGetEncodingStatusMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetEncodingStatusMethod = ClientNamenodeProtocolGrpc.getGetEncodingStatusMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetEncodingStatusMethod = getGetEncodingStatusMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getEncodingStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getEncodingStatus"))
              .build();
        }
      }
    }
    return getGetEncodingStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> getEncodeFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "encodeFile",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> getEncodeFileMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> getEncodeFileMethod;
    if ((getEncodeFileMethod = ClientNamenodeProtocolGrpc.getEncodeFileMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getEncodeFileMethod = ClientNamenodeProtocolGrpc.getEncodeFileMethod) == null) {
          ClientNamenodeProtocolGrpc.getEncodeFileMethod = getEncodeFileMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "encodeFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("encodeFile"))
              .build();
        }
      }
    }
    return getEncodeFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> getRevokeEncodingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "revokeEncoding",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> getRevokeEncodingMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> getRevokeEncodingMethod;
    if ((getRevokeEncodingMethod = ClientNamenodeProtocolGrpc.getRevokeEncodingMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRevokeEncodingMethod = ClientNamenodeProtocolGrpc.getRevokeEncodingMethod) == null) {
          ClientNamenodeProtocolGrpc.getRevokeEncodingMethod = getRevokeEncodingMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "revokeEncoding"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("revokeEncoding"))
              .build();
        }
      }
    }
    return getRevokeEncodingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> getGetRepairedBlockLocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getRepairedBlockLocations",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> getGetRepairedBlockLocationsMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> getGetRepairedBlockLocationsMethod;
    if ((getGetRepairedBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetRepairedBlockLocationsMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetRepairedBlockLocationsMethod = ClientNamenodeProtocolGrpc.getGetRepairedBlockLocationsMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetRepairedBlockLocationsMethod = getGetRepairedBlockLocationsMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getRepairedBlockLocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getRepairedBlockLocations"))
              .build();
        }
      }
    }
    return getGetRepairedBlockLocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> getChangeConfMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "changeConf",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> getChangeConfMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> getChangeConfMethod;
    if ((getChangeConfMethod = ClientNamenodeProtocolGrpc.getChangeConfMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getChangeConfMethod = ClientNamenodeProtocolGrpc.getChangeConfMethod) == null) {
          ClientNamenodeProtocolGrpc.getChangeConfMethod = getChangeConfMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "changeConf"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("changeConf"))
              .build();
        }
      }
    }
    return getChangeConfMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> getCheckAccessMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "checkAccess",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> getCheckAccessMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> getCheckAccessMethod;
    if ((getCheckAccessMethod = ClientNamenodeProtocolGrpc.getCheckAccessMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCheckAccessMethod = ClientNamenodeProtocolGrpc.getCheckAccessMethod) == null) {
          ClientNamenodeProtocolGrpc.getCheckAccessMethod = getCheckAccessMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "checkAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("checkAccess"))
              .build();
        }
      }
    }
    return getCheckAccessMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> getGetLastUpdatedContentSummaryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getLastUpdatedContentSummary",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> getGetLastUpdatedContentSummaryMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> getGetLastUpdatedContentSummaryMethod;
    if ((getGetLastUpdatedContentSummaryMethod = ClientNamenodeProtocolGrpc.getGetLastUpdatedContentSummaryMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetLastUpdatedContentSummaryMethod = ClientNamenodeProtocolGrpc.getGetLastUpdatedContentSummaryMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetLastUpdatedContentSummaryMethod = getGetLastUpdatedContentSummaryMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getLastUpdatedContentSummary"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getLastUpdatedContentSummary"))
              .build();
        }
      }
    }
    return getGetLastUpdatedContentSummaryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> getAddUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addUser",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> getAddUserMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> getAddUserMethod;
    if ((getAddUserMethod = ClientNamenodeProtocolGrpc.getAddUserMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddUserMethod = ClientNamenodeProtocolGrpc.getAddUserMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddUserMethod = getAddUserMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addUser"))
              .build();
        }
      }
    }
    return getAddUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> getAddGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> getAddGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> getAddGroupMethod;
    if ((getAddGroupMethod = ClientNamenodeProtocolGrpc.getAddGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddGroupMethod = ClientNamenodeProtocolGrpc.getAddGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddGroupMethod = getAddGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addGroup"))
              .build();
        }
      }
    }
    return getAddGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> getAddUserToGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addUserToGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> getAddUserToGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> getAddUserToGroupMethod;
    if ((getAddUserToGroupMethod = ClientNamenodeProtocolGrpc.getAddUserToGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getAddUserToGroupMethod = ClientNamenodeProtocolGrpc.getAddUserToGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getAddUserToGroupMethod = getAddUserToGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addUserToGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("addUserToGroup"))
              .build();
        }
      }
    }
    return getAddUserToGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> getRemoveUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeUser",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> getRemoveUserMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> getRemoveUserMethod;
    if ((getRemoveUserMethod = ClientNamenodeProtocolGrpc.getRemoveUserMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveUserMethod = ClientNamenodeProtocolGrpc.getRemoveUserMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveUserMethod = getRemoveUserMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeUser"))
              .build();
        }
      }
    }
    return getRemoveUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> getRemoveGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> getRemoveGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> getRemoveGroupMethod;
    if ((getRemoveGroupMethod = ClientNamenodeProtocolGrpc.getRemoveGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveGroupMethod = ClientNamenodeProtocolGrpc.getRemoveGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveGroupMethod = getRemoveGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeGroup"))
              .build();
        }
      }
    }
    return getRemoveGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> getRemoveUserFromGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "removeUserFromGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> getRemoveUserFromGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> getRemoveUserFromGroupMethod;
    if ((getRemoveUserFromGroupMethod = ClientNamenodeProtocolGrpc.getRemoveUserFromGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getRemoveUserFromGroupMethod = ClientNamenodeProtocolGrpc.getRemoveUserFromGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getRemoveUserFromGroupMethod = getRemoveUserFromGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "removeUserFromGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("removeUserFromGroup"))
              .build();
        }
      }
    }
    return getRemoveUserFromGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> getInvCachesUserRemovedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "invCachesUserRemoved",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> getInvCachesUserRemovedMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> getInvCachesUserRemovedMethod;
    if ((getInvCachesUserRemovedMethod = ClientNamenodeProtocolGrpc.getInvCachesUserRemovedMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getInvCachesUserRemovedMethod = ClientNamenodeProtocolGrpc.getInvCachesUserRemovedMethod) == null) {
          ClientNamenodeProtocolGrpc.getInvCachesUserRemovedMethod = getInvCachesUserRemovedMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "invCachesUserRemoved"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("invCachesUserRemoved"))
              .build();
        }
      }
    }
    return getInvCachesUserRemovedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> getInvCachesGroupRemovedMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "invCachesGroupRemoved",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> getInvCachesGroupRemovedMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> getInvCachesGroupRemovedMethod;
    if ((getInvCachesGroupRemovedMethod = ClientNamenodeProtocolGrpc.getInvCachesGroupRemovedMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getInvCachesGroupRemovedMethod = ClientNamenodeProtocolGrpc.getInvCachesGroupRemovedMethod) == null) {
          ClientNamenodeProtocolGrpc.getInvCachesGroupRemovedMethod = getInvCachesGroupRemovedMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "invCachesGroupRemoved"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("invCachesGroupRemoved"))
              .build();
        }
      }
    }
    return getInvCachesGroupRemovedMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> getInvCachesUserRemovedFromGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "invCachesUserRemovedFromGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> getInvCachesUserRemovedFromGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> getInvCachesUserRemovedFromGroupMethod;
    if ((getInvCachesUserRemovedFromGroupMethod = ClientNamenodeProtocolGrpc.getInvCachesUserRemovedFromGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getInvCachesUserRemovedFromGroupMethod = ClientNamenodeProtocolGrpc.getInvCachesUserRemovedFromGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getInvCachesUserRemovedFromGroupMethod = getInvCachesUserRemovedFromGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "invCachesUserRemovedFromGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("invCachesUserRemovedFromGroup"))
              .build();
        }
      }
    }
    return getInvCachesUserRemovedFromGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> getInvCachesUserAddedToGroupMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "invCachesUserAddedToGroup",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> getInvCachesUserAddedToGroupMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> getInvCachesUserAddedToGroupMethod;
    if ((getInvCachesUserAddedToGroupMethod = ClientNamenodeProtocolGrpc.getInvCachesUserAddedToGroupMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getInvCachesUserAddedToGroupMethod = ClientNamenodeProtocolGrpc.getInvCachesUserAddedToGroupMethod) == null) {
          ClientNamenodeProtocolGrpc.getInvCachesUserAddedToGroupMethod = getInvCachesUserAddedToGroupMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "invCachesUserAddedToGroup"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("invCachesUserAddedToGroup"))
              .build();
        }
      }
    }
    return getInvCachesUserAddedToGroupMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> getCreateEncryptionZoneMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "createEncryptionZone",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> getCreateEncryptionZoneMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> getCreateEncryptionZoneMethod;
    if ((getCreateEncryptionZoneMethod = ClientNamenodeProtocolGrpc.getCreateEncryptionZoneMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getCreateEncryptionZoneMethod = ClientNamenodeProtocolGrpc.getCreateEncryptionZoneMethod) == null) {
          ClientNamenodeProtocolGrpc.getCreateEncryptionZoneMethod = getCreateEncryptionZoneMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "createEncryptionZone"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("createEncryptionZone"))
              .build();
        }
      }
    }
    return getCreateEncryptionZoneMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> getListEncryptionZonesMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "listEncryptionZones",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> getListEncryptionZonesMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> getListEncryptionZonesMethod;
    if ((getListEncryptionZonesMethod = ClientNamenodeProtocolGrpc.getListEncryptionZonesMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getListEncryptionZonesMethod = ClientNamenodeProtocolGrpc.getListEncryptionZonesMethod) == null) {
          ClientNamenodeProtocolGrpc.getListEncryptionZonesMethod = getListEncryptionZonesMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "listEncryptionZones"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("listEncryptionZones"))
              .build();
        }
      }
    }
    return getListEncryptionZonesMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> getGetEZForPathMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getEZForPath",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> getGetEZForPathMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> getGetEZForPathMethod;
    if ((getGetEZForPathMethod = ClientNamenodeProtocolGrpc.getGetEZForPathMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetEZForPathMethod = ClientNamenodeProtocolGrpc.getGetEZForPathMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetEZForPathMethod = getGetEZForPathMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getEZForPath"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getEZForPath"))
              .build();
        }
      }
    }
    return getGetEZForPathMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> getGetNNEpochMSMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getNNEpochMS",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> getGetNNEpochMSMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> getGetNNEpochMSMethod;
    if ((getGetNNEpochMSMethod = ClientNamenodeProtocolGrpc.getGetNNEpochMSMethod) == null) {
      synchronized (ClientNamenodeProtocolGrpc.class) {
        if ((getGetNNEpochMSMethod = ClientNamenodeProtocolGrpc.getGetNNEpochMSMethod) == null) {
          ClientNamenodeProtocolGrpc.getGetNNEpochMSMethod = getGetNNEpochMSMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getNNEpochMS"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientNamenodeProtocolMethodDescriptorSupplier("getNNEpochMS"))
              .build();
        }
      }
    }
    return getGetNNEpochMSMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientNamenodeProtocolStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolStub>() {
        @java.lang.Override
        public ClientNamenodeProtocolStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientNamenodeProtocolStub(channel, callOptions);
        }
      };
    return ClientNamenodeProtocolStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientNamenodeProtocolBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolBlockingStub>() {
        @java.lang.Override
        public ClientNamenodeProtocolBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientNamenodeProtocolBlockingStub(channel, callOptions);
        }
      };
    return ClientNamenodeProtocolBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientNamenodeProtocolFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientNamenodeProtocolFutureStub>() {
        @java.lang.Override
        public ClientNamenodeProtocolFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientNamenodeProtocolFutureStub(channel, callOptions);
        }
      };
    return ClientNamenodeProtocolFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ClientNamenodeProtocolImplBase implements io.grpc.BindableService {

    /**
     */
    public void getBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockLocationsMethod(), responseObserver);
    }

    /**
     */
    public void getMissingBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto request,
                                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetMissingBlockLocationsMethod(), responseObserver);
    }

    /**
     */
    public void addBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddBlockChecksumMethod(), responseObserver);
    }

    /**
     */
    public void getBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockChecksumMethod(), responseObserver);
    }

    /**
     */
    public void getServerDefaults(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetServerDefaultsMethod(), responseObserver);
    }

    /**
     */
    public void create(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void append(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAppendMethod(), responseObserver);
    }

    /**
     */
    public void setReplication(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetReplicationMethod(), responseObserver);
    }

    /**
     */
    public void setStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetStoragePolicyMethod(), responseObserver);
    }

    /**
     */
    public void getStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStoragePolicyMethod(), responseObserver);
    }

    /**
     */
    public void getStoragePolicies(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto request,
                                   io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetStoragePoliciesMethod(), responseObserver);
    }

    /**
     */
    public void setMetaStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMetaStatusMethod(), responseObserver);
    }

    /**
     */
    public void setPermission(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetPermissionMethod(), responseObserver);
    }

    /**
     */
    public void setOwner(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetOwnerMethod(), responseObserver);
    }

    /**
     */
    public void abandonBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAbandonBlockMethod(), responseObserver);
    }

    /**
     */
    public void addBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddBlockMethod(), responseObserver);
    }

    /**
     */
    public void getAdditionalDatanode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAdditionalDatanodeMethod(), responseObserver);
    }

    /**
     */
    public void complete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCompleteMethod(), responseObserver);
    }

    /**
     */
    public void reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getReportBadBlocksMethod(), responseObserver);
    }

    /**
     */
    public void concat(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getConcatMethod(), responseObserver);
    }

    /**
     */
    public void truncate(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getTruncateMethod(), responseObserver);
    }

    /**
     */
    public void rename(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRenameMethod(), responseObserver);
    }

    /**
     */
    public void rename2(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto request,
                        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRename2Method(), responseObserver);
    }

    /**
     */
    public void delete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void mkdirs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getMkdirsMethod(), responseObserver);
    }

    /**
     */
    public void getListing(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetListingMethod(), responseObserver);
    }

    /**
     */
    public void renewLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRenewLeaseMethod(), responseObserver);
    }

    /**
     */
    public void recoverLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRecoverLeaseMethod(), responseObserver);
    }

    /**
     */
    public void getFsStats(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFsStatsMethod(), responseObserver);
    }

    /**
     */
    public void getDatanodeReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDatanodeReportMethod(), responseObserver);
    }

    /**
     */
    public void getDatanodeStorageReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto request,
                                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDatanodeStorageReportMethod(), responseObserver);
    }

    /**
     */
    public void getPreferredBlockSize(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetPreferredBlockSizeMethod(), responseObserver);
    }

    /**
     */
    public void setSafeMode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetSafeModeMethod(), responseObserver);
    }

    /**
     */
    public void refreshNodes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRefreshNodesMethod(), responseObserver);
    }

    /**
     */
    public void rollingUpgrade(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRollingUpgradeMethod(), responseObserver);
    }

    /**
     */
    public void listCorruptFileBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getListCorruptFileBlocksMethod(), responseObserver);
    }

    /**
     */
    public void getFileInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileInfoMethod(), responseObserver);
    }

    /**
     */
    public void addCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddCacheDirectiveMethod(), responseObserver);
    }

    /**
     */
    public void modifyCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getModifyCacheDirectiveMethod(), responseObserver);
    }

    /**
     */
    public void removeCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveCacheDirectiveMethod(), responseObserver);
    }

    /**
     */
    public void listCacheDirectives(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getListCacheDirectivesMethod(), responseObserver);
    }

    /**
     */
    public void addCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddCachePoolMethod(), responseObserver);
    }

    /**
     */
    public void modifyCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getModifyCachePoolMethod(), responseObserver);
    }

    /**
     */
    public void removeCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveCachePoolMethod(), responseObserver);
    }

    /**
     */
    public void listCachePools(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getListCachePoolsMethod(), responseObserver);
    }

    /**
     */
    public void getFileLinkInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetFileLinkInfoMethod(), responseObserver);
    }

    /**
     */
    public void getContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetContentSummaryMethod(), responseObserver);
    }

    /**
     */
    public void setQuota(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetQuotaMethod(), responseObserver);
    }

    /**
     */
    public void fsync(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto request,
                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getFsyncMethod(), responseObserver);
    }

    /**
     */
    public void setTimes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetTimesMethod(), responseObserver);
    }

    /**
     */
    public void createSymlink(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateSymlinkMethod(), responseObserver);
    }

    /**
     */
    public void getLinkTarget(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLinkTargetMethod(), responseObserver);
    }

    /**
     */
    public void updateBlockForPipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto request,
                                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateBlockForPipelineMethod(), responseObserver);
    }

    /**
     */
    public void updatePipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdatePipelineMethod(), responseObserver);
    }

    /**
     */
    public void getDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto request,
                                   io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDelegationTokenMethod(), responseObserver);
    }

    /**
     */
    public void renewDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRenewDelegationTokenMethod(), responseObserver);
    }

    /**
     */
    public void cancelDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelDelegationTokenMethod(), responseObserver);
    }

    /**
     */
    public void setBalancerBandwidth(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetBalancerBandwidthMethod(), responseObserver);
    }

    /**
     */
    public void getDataEncryptionKey(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDataEncryptionKeyMethod(), responseObserver);
    }

    /**
     */
    public void isFileClosed(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getIsFileClosedMethod(), responseObserver);
    }

    /**
     */
    public void modifyAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getModifyAclEntriesMethod(), responseObserver);
    }

    /**
     */
    public void removeAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveAclEntriesMethod(), responseObserver);
    }

    /**
     */
    public void removeDefaultAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveDefaultAclMethod(), responseObserver);
    }

    /**
     */
    public void removeAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto request,
                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveAclMethod(), responseObserver);
    }

    /**
     */
    public void setAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAclMethod(), responseObserver);
    }

    /**
     */
    public void getAclStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAclStatusMethod(), responseObserver);
    }

    /**
     */
    public void setXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getSetXAttrMethod(), responseObserver);
    }

    /**
     */
    public void getXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto request,
                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetXAttrsMethod(), responseObserver);
    }

    /**
     */
    public void listXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getListXAttrsMethod(), responseObserver);
    }

    /**
     */
    public void removeXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveXAttrMethod(), responseObserver);
    }

    /**
     */
    public void getActiveNamenodesForClient(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto request,
                                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetActiveNamenodesForClientMethod(), responseObserver);
    }

    /**
     */
    public void ping(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto request,
                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getPingMethod(), responseObserver);
    }

    /**
     */
    public void getEncodingStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEncodingStatusMethod(), responseObserver);
    }

    /**
     */
    public void encodeFile(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getEncodeFileMethod(), responseObserver);
    }

    /**
     */
    public void revokeEncoding(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeEncodingMethod(), responseObserver);
    }

    /**
     */
    public void getRepairedBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto request,
                                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetRepairedBlockLocationsMethod(), responseObserver);
    }

    /**
     */
    public void changeConf(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeConfMethod(), responseObserver);
    }

    /**
     */
    public void checkAccess(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckAccessMethod(), responseObserver);
    }

    /**
     */
    public void getLastUpdatedContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto request,
                                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLastUpdatedContentSummaryMethod(), responseObserver);
    }

    /**
     */
    public void addUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto request,
                        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddUserMethod(), responseObserver);
    }

    /**
     */
    public void addGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddGroupMethod(), responseObserver);
    }

    /**
     */
    public void addUserToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getAddUserToGroupMethod(), responseObserver);
    }

    /**
     */
    public void removeUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveUserMethod(), responseObserver);
    }

    /**
     */
    public void removeGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveGroupMethod(), responseObserver);
    }

    /**
     */
    public void removeUserFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveUserFromGroupMethod(), responseObserver);
    }

    /**
     */
    public void invCachesUserRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInvCachesUserRemovedMethod(), responseObserver);
    }

    /**
     */
    public void invCachesGroupRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInvCachesGroupRemovedMethod(), responseObserver);
    }

    /**
     */
    public void invCachesUserRemovedFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto request,
                                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInvCachesUserRemovedFromGroupMethod(), responseObserver);
    }

    /**
     */
    public void invCachesUserAddedToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto request,
                                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInvCachesUserAddedToGroupMethod(), responseObserver);
    }

    /**
     */
    public void createEncryptionZone(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getCreateEncryptionZoneMethod(), responseObserver);
    }

    /**
     */
    public void listEncryptionZones(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getListEncryptionZonesMethod(), responseObserver);
    }

    /**
     */
    public void getEZForPath(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetEZForPathMethod(), responseObserver);
    }

    /**
     */
    public void getNNEpochMS(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetNNEpochMSMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBlockLocationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto>(
                  this, METHODID_GET_BLOCK_LOCATIONS)))
          .addMethod(
            getGetMissingBlockLocationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto>(
                  this, METHODID_GET_MISSING_BLOCK_LOCATIONS)))
          .addMethod(
            getAddBlockChecksumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto>(
                  this, METHODID_ADD_BLOCK_CHECKSUM)))
          .addMethod(
            getGetBlockChecksumMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto>(
                  this, METHODID_GET_BLOCK_CHECKSUM)))
          .addMethod(
            getGetServerDefaultsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto>(
                  this, METHODID_GET_SERVER_DEFAULTS)))
          .addMethod(
            getCreateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto>(
                  this, METHODID_CREATE)))
          .addMethod(
            getAppendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto>(
                  this, METHODID_APPEND)))
          .addMethod(
            getSetReplicationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto>(
                  this, METHODID_SET_REPLICATION)))
          .addMethod(
            getSetStoragePolicyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto>(
                  this, METHODID_SET_STORAGE_POLICY)))
          .addMethod(
            getGetStoragePolicyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto>(
                  this, METHODID_GET_STORAGE_POLICY)))
          .addMethod(
            getGetStoragePoliciesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto>(
                  this, METHODID_GET_STORAGE_POLICIES)))
          .addMethod(
            getSetMetaStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto>(
                  this, METHODID_SET_META_STATUS)))
          .addMethod(
            getSetPermissionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto>(
                  this, METHODID_SET_PERMISSION)))
          .addMethod(
            getSetOwnerMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto>(
                  this, METHODID_SET_OWNER)))
          .addMethod(
            getAbandonBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto>(
                  this, METHODID_ABANDON_BLOCK)))
          .addMethod(
            getAddBlockMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto>(
                  this, METHODID_ADD_BLOCK)))
          .addMethod(
            getGetAdditionalDatanodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto>(
                  this, METHODID_GET_ADDITIONAL_DATANODE)))
          .addMethod(
            getCompleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto>(
                  this, METHODID_COMPLETE)))
          .addMethod(
            getReportBadBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto>(
                  this, METHODID_REPORT_BAD_BLOCKS)))
          .addMethod(
            getConcatMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto>(
                  this, METHODID_CONCAT)))
          .addMethod(
            getTruncateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto>(
                  this, METHODID_TRUNCATE)))
          .addMethod(
            getRenameMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto>(
                  this, METHODID_RENAME)))
          .addMethod(
            getRename2Method(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto>(
                  this, METHODID_RENAME2)))
          .addMethod(
            getDeleteMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto>(
                  this, METHODID_DELETE)))
          .addMethod(
            getMkdirsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto>(
                  this, METHODID_MKDIRS)))
          .addMethod(
            getGetListingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto>(
                  this, METHODID_GET_LISTING)))
          .addMethod(
            getRenewLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto>(
                  this, METHODID_RENEW_LEASE)))
          .addMethod(
            getRecoverLeaseMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto>(
                  this, METHODID_RECOVER_LEASE)))
          .addMethod(
            getGetFsStatsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto>(
                  this, METHODID_GET_FS_STATS)))
          .addMethod(
            getGetDatanodeReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto>(
                  this, METHODID_GET_DATANODE_REPORT)))
          .addMethod(
            getGetDatanodeStorageReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto>(
                  this, METHODID_GET_DATANODE_STORAGE_REPORT)))
          .addMethod(
            getGetPreferredBlockSizeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto>(
                  this, METHODID_GET_PREFERRED_BLOCK_SIZE)))
          .addMethod(
            getSetSafeModeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto>(
                  this, METHODID_SET_SAFE_MODE)))
          .addMethod(
            getRefreshNodesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto>(
                  this, METHODID_REFRESH_NODES)))
          .addMethod(
            getRollingUpgradeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto>(
                  this, METHODID_ROLLING_UPGRADE)))
          .addMethod(
            getListCorruptFileBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto>(
                  this, METHODID_LIST_CORRUPT_FILE_BLOCKS)))
          .addMethod(
            getGetFileInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto>(
                  this, METHODID_GET_FILE_INFO)))
          .addMethod(
            getAddCacheDirectiveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto>(
                  this, METHODID_ADD_CACHE_DIRECTIVE)))
          .addMethod(
            getModifyCacheDirectiveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto>(
                  this, METHODID_MODIFY_CACHE_DIRECTIVE)))
          .addMethod(
            getRemoveCacheDirectiveMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto>(
                  this, METHODID_REMOVE_CACHE_DIRECTIVE)))
          .addMethod(
            getListCacheDirectivesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto>(
                  this, METHODID_LIST_CACHE_DIRECTIVES)))
          .addMethod(
            getAddCachePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto>(
                  this, METHODID_ADD_CACHE_POOL)))
          .addMethod(
            getModifyCachePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto>(
                  this, METHODID_MODIFY_CACHE_POOL)))
          .addMethod(
            getRemoveCachePoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto>(
                  this, METHODID_REMOVE_CACHE_POOL)))
          .addMethod(
            getListCachePoolsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto>(
                  this, METHODID_LIST_CACHE_POOLS)))
          .addMethod(
            getGetFileLinkInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto>(
                  this, METHODID_GET_FILE_LINK_INFO)))
          .addMethod(
            getGetContentSummaryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto>(
                  this, METHODID_GET_CONTENT_SUMMARY)))
          .addMethod(
            getSetQuotaMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto>(
                  this, METHODID_SET_QUOTA)))
          .addMethod(
            getFsyncMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto>(
                  this, METHODID_FSYNC)))
          .addMethod(
            getSetTimesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto>(
                  this, METHODID_SET_TIMES)))
          .addMethod(
            getCreateSymlinkMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto>(
                  this, METHODID_CREATE_SYMLINK)))
          .addMethod(
            getGetLinkTargetMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto>(
                  this, METHODID_GET_LINK_TARGET)))
          .addMethod(
            getUpdateBlockForPipelineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto>(
                  this, METHODID_UPDATE_BLOCK_FOR_PIPELINE)))
          .addMethod(
            getUpdatePipelineMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto>(
                  this, METHODID_UPDATE_PIPELINE)))
          .addMethod(
            getGetDelegationTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto>(
                  this, METHODID_GET_DELEGATION_TOKEN)))
          .addMethod(
            getRenewDelegationTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto>(
                  this, METHODID_RENEW_DELEGATION_TOKEN)))
          .addMethod(
            getCancelDelegationTokenMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto>(
                  this, METHODID_CANCEL_DELEGATION_TOKEN)))
          .addMethod(
            getSetBalancerBandwidthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto>(
                  this, METHODID_SET_BALANCER_BANDWIDTH)))
          .addMethod(
            getGetDataEncryptionKeyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto>(
                  this, METHODID_GET_DATA_ENCRYPTION_KEY)))
          .addMethod(
            getIsFileClosedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto>(
                  this, METHODID_IS_FILE_CLOSED)))
          .addMethod(
            getModifyAclEntriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto>(
                  this, METHODID_MODIFY_ACL_ENTRIES)))
          .addMethod(
            getRemoveAclEntriesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto>(
                  this, METHODID_REMOVE_ACL_ENTRIES)))
          .addMethod(
            getRemoveDefaultAclMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto>(
                  this, METHODID_REMOVE_DEFAULT_ACL)))
          .addMethod(
            getRemoveAclMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto>(
                  this, METHODID_REMOVE_ACL)))
          .addMethod(
            getSetAclMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto>(
                  this, METHODID_SET_ACL)))
          .addMethod(
            getGetAclStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto>(
                  this, METHODID_GET_ACL_STATUS)))
          .addMethod(
            getSetXAttrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto>(
                  this, METHODID_SET_XATTR)))
          .addMethod(
            getGetXAttrsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto>(
                  this, METHODID_GET_XATTRS)))
          .addMethod(
            getListXAttrsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto>(
                  this, METHODID_LIST_XATTRS)))
          .addMethod(
            getRemoveXAttrMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto>(
                  this, METHODID_REMOVE_XATTR)))
          .addMethod(
            getGetActiveNamenodesForClientMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto>(
                  this, METHODID_GET_ACTIVE_NAMENODES_FOR_CLIENT)))
          .addMethod(
            getPingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto>(
                  this, METHODID_PING)))
          .addMethod(
            getGetEncodingStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto>(
                  this, METHODID_GET_ENCODING_STATUS)))
          .addMethod(
            getEncodeFileMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto>(
                  this, METHODID_ENCODE_FILE)))
          .addMethod(
            getRevokeEncodingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto>(
                  this, METHODID_REVOKE_ENCODING)))
          .addMethod(
            getGetRepairedBlockLocationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto>(
                  this, METHODID_GET_REPAIRED_BLOCK_LOCATIONS)))
          .addMethod(
            getChangeConfMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto>(
                  this, METHODID_CHANGE_CONF)))
          .addMethod(
            getCheckAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto>(
                  this, METHODID_CHECK_ACCESS)))
          .addMethod(
            getGetLastUpdatedContentSummaryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto>(
                  this, METHODID_GET_LAST_UPDATED_CONTENT_SUMMARY)))
          .addMethod(
            getAddUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto>(
                  this, METHODID_ADD_USER)))
          .addMethod(
            getAddGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto>(
                  this, METHODID_ADD_GROUP)))
          .addMethod(
            getAddUserToGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto>(
                  this, METHODID_ADD_USER_TO_GROUP)))
          .addMethod(
            getRemoveUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto>(
                  this, METHODID_REMOVE_USER)))
          .addMethod(
            getRemoveGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto>(
                  this, METHODID_REMOVE_GROUP)))
          .addMethod(
            getRemoveUserFromGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto>(
                  this, METHODID_REMOVE_USER_FROM_GROUP)))
          .addMethod(
            getInvCachesUserRemovedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto>(
                  this, METHODID_INV_CACHES_USER_REMOVED)))
          .addMethod(
            getInvCachesGroupRemovedMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto>(
                  this, METHODID_INV_CACHES_GROUP_REMOVED)))
          .addMethod(
            getInvCachesUserRemovedFromGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto>(
                  this, METHODID_INV_CACHES_USER_REMOVED_FROM_GROUP)))
          .addMethod(
            getInvCachesUserAddedToGroupMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto>(
                  this, METHODID_INV_CACHES_USER_ADDED_TO_GROUP)))
          .addMethod(
            getCreateEncryptionZoneMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto>(
                  this, METHODID_CREATE_ENCRYPTION_ZONE)))
          .addMethod(
            getListEncryptionZonesMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto>(
                  this, METHODID_LIST_ENCRYPTION_ZONES)))
          .addMethod(
            getGetEZForPathMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto>(
                  this, METHODID_GET_EZFOR_PATH)))
          .addMethod(
            getGetNNEpochMSMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto>(
                  this, METHODID_GET_NNEPOCH_MS)))
          .build();
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolStub extends io.grpc.stub.AbstractAsyncStub<ClientNamenodeProtocolStub> {
    private ClientNamenodeProtocolStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolStub(channel, callOptions);
    }

    /**
     */
    public void getBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockLocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getMissingBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto request,
                                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetMissingBlockLocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddBlockChecksumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockChecksumMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getServerDefaults(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetServerDefaultsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void create(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void append(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAppendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setReplication(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetReplicationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetStoragePolicyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStoragePolicyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getStoragePolicies(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto request,
                                   io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetStoragePoliciesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setMetaStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMetaStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setPermission(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetPermissionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setOwner(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetOwnerMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void abandonBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAbandonBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddBlockMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAdditionalDatanode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAdditionalDatanodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void complete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCompleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReportBadBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void concat(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getConcatMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void truncate(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTruncateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rename(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rename2(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto request,
                        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRename2Method(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void mkdirs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getMkdirsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getListing(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetListingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void renewLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenewLeaseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void recoverLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecoverLeaseMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFsStats(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFsStatsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDatanodeReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDatanodeReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDatanodeStorageReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto request,
                                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDatanodeStorageReportMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getPreferredBlockSize(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetPreferredBlockSizeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setSafeMode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetSafeModeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void refreshNodes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRefreshNodesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void rollingUpgrade(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRollingUpgradeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listCorruptFileBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListCorruptFileBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddCacheDirectiveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modifyCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModifyCacheDirectiveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveCacheDirectiveMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listCacheDirectives(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListCacheDirectivesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddCachePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modifyCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModifyCachePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveCachePoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listCachePools(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListCachePoolsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getFileLinkInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto request,
                                io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetFileLinkInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetContentSummaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setQuota(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetQuotaMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void fsync(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto request,
                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getFsyncMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setTimes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetTimesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createSymlink(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateSymlinkMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLinkTarget(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto request,
                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLinkTargetMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBlockForPipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto request,
                                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateBlockForPipelineMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updatePipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdatePipelineMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto request,
                                   io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDelegationTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void renewDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRenewDelegationTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void cancelDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelDelegationTokenMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setBalancerBandwidth(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetBalancerBandwidthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDataEncryptionKey(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDataEncryptionKeyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void isFileClosed(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getIsFileClosedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void modifyAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getModifyAclEntriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveAclEntriesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeDefaultAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto request,
                                 io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveDefaultAclMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto request,
                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveAclMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto request,
                       io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAclMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getAclStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAclStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void setXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetXAttrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto request,
                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetXAttrsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListXAttrsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveXAttrMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getActiveNamenodesForClient(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto request,
                                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetActiveNamenodesForClientMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void ping(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto request,
                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEncodingStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto request,
                                  io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEncodingStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void encodeFile(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEncodeFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void revokeEncoding(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeEncodingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getRepairedBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto request,
                                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetRepairedBlockLocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void changeConf(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeConfMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void checkAccess(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckAccessMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getLastUpdatedContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto request,
                                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLastUpdatedContentSummaryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto request,
                        io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto request,
                         io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void addUserToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto request,
                               io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddUserToGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto request,
                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto request,
                            io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void removeUserFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveUserFromGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void invCachesUserRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInvCachesUserRemovedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void invCachesGroupRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto request,
                                      io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInvCachesGroupRemovedMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void invCachesUserRemovedFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto request,
                                              io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInvCachesUserRemovedFromGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void invCachesUserAddedToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto request,
                                          io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInvCachesUserAddedToGroupMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void createEncryptionZone(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto request,
                                     io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCreateEncryptionZoneMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listEncryptionZones(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListEncryptionZonesMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getEZForPath(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetEZForPathMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getNNEpochMS(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto request,
                             io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetNNEpochMSMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClientNamenodeProtocolBlockingStub> {
    private ClientNamenodeProtocolBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto getBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockLocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto getMissingBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetMissingBlockLocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto addBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddBlockChecksumMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto getBlockChecksum(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockChecksumMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto getServerDefaults(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetServerDefaultsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto create(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto append(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAppendMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto setReplication(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetReplicationMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto setStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetStoragePolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto getStoragePolicy(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetStoragePolicyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto getStoragePolicies(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetStoragePoliciesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto setMetaStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetMetaStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto setPermission(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetPermissionMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto setOwner(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetOwnerMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto abandonBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAbandonBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto addBlock(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddBlockMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto getAdditionalDatanode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetAdditionalDatanodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto complete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCompleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto reportBadBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getReportBadBlocksMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto concat(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getConcatMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto truncate(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getTruncateMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto rename(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRenameMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto rename2(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRename2Method(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto delete(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto mkdirs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getMkdirsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto getListing(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetListingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto renewLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRenewLeaseMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto recoverLease(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRecoverLeaseMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto getFsStats(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetFsStatsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto getDatanodeReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetDatanodeReportMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto getDatanodeStorageReport(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetDatanodeStorageReportMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto getPreferredBlockSize(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetPreferredBlockSizeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto setSafeMode(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetSafeModeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto refreshNodes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRefreshNodesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto rollingUpgrade(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRollingUpgradeMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto listCorruptFileBlocks(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getListCorruptFileBlocksMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto getFileInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetFileInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto addCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddCacheDirectiveMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto modifyCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getModifyCacheDirectiveMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto removeCacheDirective(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveCacheDirectiveMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto listCacheDirectives(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getListCacheDirectivesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto addCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddCachePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto modifyCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getModifyCachePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto removeCachePool(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveCachePoolMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto listCachePools(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getListCachePoolsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto getFileLinkInfo(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetFileLinkInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto getContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetContentSummaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto setQuota(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetQuotaMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto fsync(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getFsyncMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto setTimes(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetTimesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto createSymlink(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCreateSymlinkMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto getLinkTarget(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetLinkTargetMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto updateBlockForPipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getUpdateBlockForPipelineMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto updatePipeline(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getUpdatePipelineMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto getDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetDelegationTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto renewDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRenewDelegationTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto cancelDelegationToken(com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCancelDelegationTokenMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto setBalancerBandwidth(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetBalancerBandwidthMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto getDataEncryptionKey(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetDataEncryptionKeyMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto isFileClosed(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getIsFileClosedMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto modifyAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getModifyAclEntriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto removeAclEntries(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveAclEntriesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto removeDefaultAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveDefaultAclMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto removeAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveAclMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto setAcl(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetAclMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto getAclStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetAclStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto setXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getSetXAttrMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto getXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetXAttrsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto listXAttrs(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getListXAttrsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto removeXAttr(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveXAttrMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto getActiveNamenodesForClient(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetActiveNamenodesForClientMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto ping(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getPingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto getEncodingStatus(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetEncodingStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto encodeFile(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getEncodeFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto revokeEncoding(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRevokeEncodingMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto getRepairedBlockLocations(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetRepairedBlockLocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto changeConf(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto request) {
      return blockingUnaryCall(
          getChannel(), getChangeConfMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto checkAccess(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCheckAccessMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto getLastUpdatedContentSummary(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetLastUpdatedContentSummaryMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto addUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto addGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto addUserToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getAddUserToGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto removeUser(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveUserMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto removeGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto removeUserFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getRemoveUserFromGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto invCachesUserRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInvCachesUserRemovedMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto invCachesGroupRemoved(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInvCachesGroupRemovedMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto invCachesUserRemovedFromGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInvCachesUserRemovedFromGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto invCachesUserAddedToGroup(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInvCachesUserAddedToGroupMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto createEncryptionZone(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getCreateEncryptionZoneMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto listEncryptionZones(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getListEncryptionZonesMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto getEZForPath(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetEZForPathMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto getNNEpochMS(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetNNEpochMSMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ClientNamenodeProtocolFutureStub extends io.grpc.stub.AbstractFutureStub<ClientNamenodeProtocolFutureStub> {
    private ClientNamenodeProtocolFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientNamenodeProtocolFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientNamenodeProtocolFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto> getBlockLocations(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockLocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto> getMissingBlockLocations(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetMissingBlockLocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto> addBlockChecksum(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddBlockChecksumMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto> getBlockChecksum(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockChecksumMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto> getServerDefaults(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetServerDefaultsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto> create(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto> append(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAppendMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto> setReplication(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetReplicationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto> setStoragePolicy(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetStoragePolicyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto> getStoragePolicy(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStoragePolicyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto> getStoragePolicies(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetStoragePoliciesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto> setMetaStatus(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMetaStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto> setPermission(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetPermissionMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto> setOwner(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetOwnerMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto> abandonBlock(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAbandonBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto> addBlock(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddBlockMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto> getAdditionalDatanode(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAdditionalDatanodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto> complete(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCompleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto> reportBadBlocks(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getReportBadBlocksMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto> concat(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getConcatMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto> truncate(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getTruncateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto> rename(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRenameMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto> rename2(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRename2Method(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto> delete(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto> mkdirs(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getMkdirsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto> getListing(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetListingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto> renewLease(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRenewLeaseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto> recoverLease(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRecoverLeaseMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto> getFsStats(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFsStatsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto> getDatanodeReport(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDatanodeReportMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto> getDatanodeStorageReport(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDatanodeStorageReportMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto> getPreferredBlockSize(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetPreferredBlockSizeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto> setSafeMode(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetSafeModeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto> refreshNodes(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRefreshNodesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto> rollingUpgrade(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRollingUpgradeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto> listCorruptFileBlocks(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getListCorruptFileBlocksMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto> getFileInfo(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto> addCacheDirective(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddCacheDirectiveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto> modifyCacheDirective(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getModifyCacheDirectiveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto> removeCacheDirective(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveCacheDirectiveMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto> listCacheDirectives(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getListCacheDirectivesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto> addCachePool(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddCachePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto> modifyCachePool(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getModifyCachePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto> removeCachePool(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveCachePoolMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto> listCachePools(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getListCachePoolsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto> getFileLinkInfo(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetFileLinkInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto> getContentSummary(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetContentSummaryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto> setQuota(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetQuotaMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto> fsync(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getFsyncMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto> setTimes(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetTimesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto> createSymlink(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateSymlinkMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto> getLinkTarget(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLinkTargetMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto> updateBlockForPipeline(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateBlockForPipelineMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto> updatePipeline(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdatePipelineMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto> getDelegationToken(
        com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDelegationTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto> renewDelegationToken(
        com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRenewDelegationTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto> cancelDelegationToken(
        com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelDelegationTokenMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto> setBalancerBandwidth(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetBalancerBandwidthMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto> getDataEncryptionKey(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDataEncryptionKeyMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto> isFileClosed(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getIsFileClosedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto> modifyAclEntries(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getModifyAclEntriesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto> removeAclEntries(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveAclEntriesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto> removeDefaultAcl(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveDefaultAclMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto> removeAcl(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveAclMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto> setAcl(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAclMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto> getAclStatus(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAclStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto> setXAttr(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getSetXAttrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto> getXAttrs(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetXAttrsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto> listXAttrs(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getListXAttrsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto> removeXAttr(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveXAttrMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto> getActiveNamenodesForClient(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetActiveNamenodesForClientMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto> ping(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getPingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto> getEncodingStatus(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEncodingStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto> encodeFile(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getEncodeFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto> revokeEncoding(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeEncodingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto> getRepairedBlockLocations(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetRepairedBlockLocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto> changeConf(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeConfMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto> checkAccess(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckAccessMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto> getLastUpdatedContentSummary(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLastUpdatedContentSummaryMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto> addUser(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto> addGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto> addUserToGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getAddUserToGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto> removeUser(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveUserMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto> removeGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto> removeUserFromGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveUserFromGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto> invCachesUserRemoved(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInvCachesUserRemovedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto> invCachesGroupRemoved(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInvCachesGroupRemovedMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto> invCachesUserRemovedFromGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInvCachesUserRemovedFromGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto> invCachesUserAddedToGroup(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInvCachesUserAddedToGroupMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto> createEncryptionZone(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getCreateEncryptionZoneMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto> listEncryptionZones(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getListEncryptionZonesMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto> getEZForPath(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetEZForPathMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto> getNNEpochMS(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetNNEpochMSMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BLOCK_LOCATIONS = 0;
  private static final int METHODID_GET_MISSING_BLOCK_LOCATIONS = 1;
  private static final int METHODID_ADD_BLOCK_CHECKSUM = 2;
  private static final int METHODID_GET_BLOCK_CHECKSUM = 3;
  private static final int METHODID_GET_SERVER_DEFAULTS = 4;
  private static final int METHODID_CREATE = 5;
  private static final int METHODID_APPEND = 6;
  private static final int METHODID_SET_REPLICATION = 7;
  private static final int METHODID_SET_STORAGE_POLICY = 8;
  private static final int METHODID_GET_STORAGE_POLICY = 9;
  private static final int METHODID_GET_STORAGE_POLICIES = 10;
  private static final int METHODID_SET_META_STATUS = 11;
  private static final int METHODID_SET_PERMISSION = 12;
  private static final int METHODID_SET_OWNER = 13;
  private static final int METHODID_ABANDON_BLOCK = 14;
  private static final int METHODID_ADD_BLOCK = 15;
  private static final int METHODID_GET_ADDITIONAL_DATANODE = 16;
  private static final int METHODID_COMPLETE = 17;
  private static final int METHODID_REPORT_BAD_BLOCKS = 18;
  private static final int METHODID_CONCAT = 19;
  private static final int METHODID_TRUNCATE = 20;
  private static final int METHODID_RENAME = 21;
  private static final int METHODID_RENAME2 = 22;
  private static final int METHODID_DELETE = 23;
  private static final int METHODID_MKDIRS = 24;
  private static final int METHODID_GET_LISTING = 25;
  private static final int METHODID_RENEW_LEASE = 26;
  private static final int METHODID_RECOVER_LEASE = 27;
  private static final int METHODID_GET_FS_STATS = 28;
  private static final int METHODID_GET_DATANODE_REPORT = 29;
  private static final int METHODID_GET_DATANODE_STORAGE_REPORT = 30;
  private static final int METHODID_GET_PREFERRED_BLOCK_SIZE = 31;
  private static final int METHODID_SET_SAFE_MODE = 32;
  private static final int METHODID_REFRESH_NODES = 33;
  private static final int METHODID_ROLLING_UPGRADE = 34;
  private static final int METHODID_LIST_CORRUPT_FILE_BLOCKS = 35;
  private static final int METHODID_GET_FILE_INFO = 36;
  private static final int METHODID_ADD_CACHE_DIRECTIVE = 37;
  private static final int METHODID_MODIFY_CACHE_DIRECTIVE = 38;
  private static final int METHODID_REMOVE_CACHE_DIRECTIVE = 39;
  private static final int METHODID_LIST_CACHE_DIRECTIVES = 40;
  private static final int METHODID_ADD_CACHE_POOL = 41;
  private static final int METHODID_MODIFY_CACHE_POOL = 42;
  private static final int METHODID_REMOVE_CACHE_POOL = 43;
  private static final int METHODID_LIST_CACHE_POOLS = 44;
  private static final int METHODID_GET_FILE_LINK_INFO = 45;
  private static final int METHODID_GET_CONTENT_SUMMARY = 46;
  private static final int METHODID_SET_QUOTA = 47;
  private static final int METHODID_FSYNC = 48;
  private static final int METHODID_SET_TIMES = 49;
  private static final int METHODID_CREATE_SYMLINK = 50;
  private static final int METHODID_GET_LINK_TARGET = 51;
  private static final int METHODID_UPDATE_BLOCK_FOR_PIPELINE = 52;
  private static final int METHODID_UPDATE_PIPELINE = 53;
  private static final int METHODID_GET_DELEGATION_TOKEN = 54;
  private static final int METHODID_RENEW_DELEGATION_TOKEN = 55;
  private static final int METHODID_CANCEL_DELEGATION_TOKEN = 56;
  private static final int METHODID_SET_BALANCER_BANDWIDTH = 57;
  private static final int METHODID_GET_DATA_ENCRYPTION_KEY = 58;
  private static final int METHODID_IS_FILE_CLOSED = 59;
  private static final int METHODID_MODIFY_ACL_ENTRIES = 60;
  private static final int METHODID_REMOVE_ACL_ENTRIES = 61;
  private static final int METHODID_REMOVE_DEFAULT_ACL = 62;
  private static final int METHODID_REMOVE_ACL = 63;
  private static final int METHODID_SET_ACL = 64;
  private static final int METHODID_GET_ACL_STATUS = 65;
  private static final int METHODID_SET_XATTR = 66;
  private static final int METHODID_GET_XATTRS = 67;
  private static final int METHODID_LIST_XATTRS = 68;
  private static final int METHODID_REMOVE_XATTR = 69;
  private static final int METHODID_GET_ACTIVE_NAMENODES_FOR_CLIENT = 70;
  private static final int METHODID_PING = 71;
  private static final int METHODID_GET_ENCODING_STATUS = 72;
  private static final int METHODID_ENCODE_FILE = 73;
  private static final int METHODID_REVOKE_ENCODING = 74;
  private static final int METHODID_GET_REPAIRED_BLOCK_LOCATIONS = 75;
  private static final int METHODID_CHANGE_CONF = 76;
  private static final int METHODID_CHECK_ACCESS = 77;
  private static final int METHODID_GET_LAST_UPDATED_CONTENT_SUMMARY = 78;
  private static final int METHODID_ADD_USER = 79;
  private static final int METHODID_ADD_GROUP = 80;
  private static final int METHODID_ADD_USER_TO_GROUP = 81;
  private static final int METHODID_REMOVE_USER = 82;
  private static final int METHODID_REMOVE_GROUP = 83;
  private static final int METHODID_REMOVE_USER_FROM_GROUP = 84;
  private static final int METHODID_INV_CACHES_USER_REMOVED = 85;
  private static final int METHODID_INV_CACHES_GROUP_REMOVED = 86;
  private static final int METHODID_INV_CACHES_USER_REMOVED_FROM_GROUP = 87;
  private static final int METHODID_INV_CACHES_USER_ADDED_TO_GROUP = 88;
  private static final int METHODID_CREATE_ENCRYPTION_ZONE = 89;
  private static final int METHODID_LIST_ENCRYPTION_ZONES = 90;
  private static final int METHODID_GET_EZFOR_PATH = 91;
  private static final int METHODID_GET_NNEPOCH_MS = 92;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientNamenodeProtocolImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientNamenodeProtocolImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BLOCK_LOCATIONS:
          serviceImpl.getBlockLocations((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockLocationsResponseProto>) responseObserver);
          break;
        case METHODID_GET_MISSING_BLOCK_LOCATIONS:
          serviceImpl.getMissingBlockLocations((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetMissingBlockLocationsResponseProto>) responseObserver);
          break;
        case METHODID_ADD_BLOCK_CHECKSUM:
          serviceImpl.addBlockChecksum((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockChecksumResponseProto>) responseObserver);
          break;
        case METHODID_GET_BLOCK_CHECKSUM:
          serviceImpl.getBlockChecksum((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetBlockChecksumResponseProto>) responseObserver);
          break;
        case METHODID_GET_SERVER_DEFAULTS:
          serviceImpl.getServerDefaults((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetServerDefaultsResponseProto>) responseObserver);
          break;
        case METHODID_CREATE:
          serviceImpl.create((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateResponseProto>) responseObserver);
          break;
        case METHODID_APPEND:
          serviceImpl.append((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AppendResponseProto>) responseObserver);
          break;
        case METHODID_SET_REPLICATION:
          serviceImpl.setReplication((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetReplicationResponseProto>) responseObserver);
          break;
        case METHODID_SET_STORAGE_POLICY:
          serviceImpl.setStoragePolicy((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetStoragePolicyResponseProto>) responseObserver);
          break;
        case METHODID_GET_STORAGE_POLICY:
          serviceImpl.getStoragePolicy((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePolicyResponseProto>) responseObserver);
          break;
        case METHODID_GET_STORAGE_POLICIES:
          serviceImpl.getStoragePolicies((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetStoragePoliciesResponseProto>) responseObserver);
          break;
        case METHODID_SET_META_STATUS:
          serviceImpl.setMetaStatus((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetMetaStatusResponseProto>) responseObserver);
          break;
        case METHODID_SET_PERMISSION:
          serviceImpl.setPermission((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetPermissionResponseProto>) responseObserver);
          break;
        case METHODID_SET_OWNER:
          serviceImpl.setOwner((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetOwnerResponseProto>) responseObserver);
          break;
        case METHODID_ABANDON_BLOCK:
          serviceImpl.abandonBlock((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AbandonBlockResponseProto>) responseObserver);
          break;
        case METHODID_ADD_BLOCK:
          serviceImpl.addBlock((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddBlockResponseProto>) responseObserver);
          break;
        case METHODID_GET_ADDITIONAL_DATANODE:
          serviceImpl.getAdditionalDatanode((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetAdditionalDatanodeResponseProto>) responseObserver);
          break;
        case METHODID_COMPLETE:
          serviceImpl.complete((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CompleteResponseProto>) responseObserver);
          break;
        case METHODID_REPORT_BAD_BLOCKS:
          serviceImpl.reportBadBlocks((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ReportBadBlocksResponseProto>) responseObserver);
          break;
        case METHODID_CONCAT:
          serviceImpl.concat((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ConcatResponseProto>) responseObserver);
          break;
        case METHODID_TRUNCATE:
          serviceImpl.truncate((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.TruncateResponseProto>) responseObserver);
          break;
        case METHODID_RENAME:
          serviceImpl.rename((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenameResponseProto>) responseObserver);
          break;
        case METHODID_RENAME2:
          serviceImpl.rename2((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2RequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.Rename2ResponseProto>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.DeleteResponseProto>) responseObserver);
          break;
        case METHODID_MKDIRS:
          serviceImpl.mkdirs((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.MkdirsResponseProto>) responseObserver);
          break;
        case METHODID_GET_LISTING:
          serviceImpl.getListing((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetListingResponseProto>) responseObserver);
          break;
        case METHODID_RENEW_LEASE:
          serviceImpl.renewLease((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RenewLeaseResponseProto>) responseObserver);
          break;
        case METHODID_RECOVER_LEASE:
          serviceImpl.recoverLease((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RecoverLeaseResponseProto>) responseObserver);
          break;
        case METHODID_GET_FS_STATS:
          serviceImpl.getFsStats((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFsStatsResponseProto>) responseObserver);
          break;
        case METHODID_GET_DATANODE_REPORT:
          serviceImpl.getDatanodeReport((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeReportResponseProto>) responseObserver);
          break;
        case METHODID_GET_DATANODE_STORAGE_REPORT:
          serviceImpl.getDatanodeStorageReport((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDatanodeStorageReportResponseProto>) responseObserver);
          break;
        case METHODID_GET_PREFERRED_BLOCK_SIZE:
          serviceImpl.getPreferredBlockSize((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetPreferredBlockSizeResponseProto>) responseObserver);
          break;
        case METHODID_SET_SAFE_MODE:
          serviceImpl.setSafeMode((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetSafeModeResponseProto>) responseObserver);
          break;
        case METHODID_REFRESH_NODES:
          serviceImpl.refreshNodes((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RefreshNodesResponseProto>) responseObserver);
          break;
        case METHODID_ROLLING_UPGRADE:
          serviceImpl.rollingUpgrade((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RollingUpgradeResponseProto>) responseObserver);
          break;
        case METHODID_LIST_CORRUPT_FILE_BLOCKS:
          serviceImpl.listCorruptFileBlocks((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCorruptFileBlocksResponseProto>) responseObserver);
          break;
        case METHODID_GET_FILE_INFO:
          serviceImpl.getFileInfo((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileInfoResponseProto>) responseObserver);
          break;
        case METHODID_ADD_CACHE_DIRECTIVE:
          serviceImpl.addCacheDirective((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCacheDirectiveResponseProto>) responseObserver);
          break;
        case METHODID_MODIFY_CACHE_DIRECTIVE:
          serviceImpl.modifyCacheDirective((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCacheDirectiveResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_CACHE_DIRECTIVE:
          serviceImpl.removeCacheDirective((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCacheDirectiveResponseProto>) responseObserver);
          break;
        case METHODID_LIST_CACHE_DIRECTIVES:
          serviceImpl.listCacheDirectives((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCacheDirectivesResponseProto>) responseObserver);
          break;
        case METHODID_ADD_CACHE_POOL:
          serviceImpl.addCachePool((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddCachePoolResponseProto>) responseObserver);
          break;
        case METHODID_MODIFY_CACHE_POOL:
          serviceImpl.modifyCachePool((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ModifyCachePoolResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_CACHE_POOL:
          serviceImpl.removeCachePool((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveCachePoolResponseProto>) responseObserver);
          break;
        case METHODID_LIST_CACHE_POOLS:
          serviceImpl.listCachePools((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ListCachePoolsResponseProto>) responseObserver);
          break;
        case METHODID_GET_FILE_LINK_INFO:
          serviceImpl.getFileLinkInfo((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetFileLinkInfoResponseProto>) responseObserver);
          break;
        case METHODID_GET_CONTENT_SUMMARY:
          serviceImpl.getContentSummary((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetContentSummaryResponseProto>) responseObserver);
          break;
        case METHODID_SET_QUOTA:
          serviceImpl.setQuota((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetQuotaResponseProto>) responseObserver);
          break;
        case METHODID_FSYNC:
          serviceImpl.fsync((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.FsyncResponseProto>) responseObserver);
          break;
        case METHODID_SET_TIMES:
          serviceImpl.setTimes((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetTimesResponseProto>) responseObserver);
          break;
        case METHODID_CREATE_SYMLINK:
          serviceImpl.createSymlink((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CreateSymlinkResponseProto>) responseObserver);
          break;
        case METHODID_GET_LINK_TARGET:
          serviceImpl.getLinkTarget((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLinkTargetResponseProto>) responseObserver);
          break;
        case METHODID_UPDATE_BLOCK_FOR_PIPELINE:
          serviceImpl.updateBlockForPipeline((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdateBlockForPipelineResponseProto>) responseObserver);
          break;
        case METHODID_UPDATE_PIPELINE:
          serviceImpl.updatePipeline((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.UpdatePipelineResponseProto>) responseObserver);
          break;
        case METHODID_GET_DELEGATION_TOKEN:
          serviceImpl.getDelegationToken((com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.GetDelegationTokenResponseProto>) responseObserver);
          break;
        case METHODID_RENEW_DELEGATION_TOKEN:
          serviceImpl.renewDelegationToken((com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.RenewDelegationTokenResponseProto>) responseObserver);
          break;
        case METHODID_CANCEL_DELEGATION_TOKEN:
          serviceImpl.cancelDelegationToken((com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.security.proto.SecurityProtos.CancelDelegationTokenResponseProto>) responseObserver);
          break;
        case METHODID_SET_BALANCER_BANDWIDTH:
          serviceImpl.setBalancerBandwidth((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.SetBalancerBandwidthResponseProto>) responseObserver);
          break;
        case METHODID_GET_DATA_ENCRYPTION_KEY:
          serviceImpl.getDataEncryptionKey((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetDataEncryptionKeyResponseProto>) responseObserver);
          break;
        case METHODID_IS_FILE_CLOSED:
          serviceImpl.isFileClosed((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.IsFileClosedResponseProto>) responseObserver);
          break;
        case METHODID_MODIFY_ACL_ENTRIES:
          serviceImpl.modifyAclEntries((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.ModifyAclEntriesResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_ACL_ENTRIES:
          serviceImpl.removeAclEntries((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclEntriesResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_DEFAULT_ACL:
          serviceImpl.removeDefaultAcl((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveDefaultAclResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_ACL:
          serviceImpl.removeAcl((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.RemoveAclResponseProto>) responseObserver);
          break;
        case METHODID_SET_ACL:
          serviceImpl.setAcl((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.SetAclResponseProto>) responseObserver);
          break;
        case METHODID_GET_ACL_STATUS:
          serviceImpl.getAclStatus((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.proto.AclProtos.GetAclStatusResponseProto>) responseObserver);
          break;
        case METHODID_SET_XATTR:
          serviceImpl.setXAttr((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.SetXAttrResponseProto>) responseObserver);
          break;
        case METHODID_GET_XATTRS:
          serviceImpl.getXAttrs((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.GetXAttrsResponseProto>) responseObserver);
          break;
        case METHODID_LIST_XATTRS:
          serviceImpl.listXAttrs((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.ListXAttrsResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_XATTR:
          serviceImpl.removeXAttr((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.XAttrProtos.RemoveXAttrResponseProto>) responseObserver);
          break;
        case METHODID_GET_ACTIVE_NAMENODES_FOR_CLIENT:
          serviceImpl.getActiveNamenodesForClient((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ActiveNamenodeListResponseProto>) responseObserver);
          break;
        case METHODID_PING:
          serviceImpl.ping((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.PingResponseProto>) responseObserver);
          break;
        case METHODID_GET_ENCODING_STATUS:
          serviceImpl.getEncodingStatus((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEncodingStatusResponseProto>) responseObserver);
          break;
        case METHODID_ENCODE_FILE:
          serviceImpl.encodeFile((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.EncodeFileResponseProto>) responseObserver);
          break;
        case METHODID_REVOKE_ENCODING:
          serviceImpl.revokeEncoding((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RevokeEncodingResponseProto>) responseObserver);
          break;
        case METHODID_GET_REPAIRED_BLOCK_LOCATIONS:
          serviceImpl.getRepairedBlockLocations((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsRequsestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetRepairedBlockLocationsResponseProto>) responseObserver);
          break;
        case METHODID_CHANGE_CONF:
          serviceImpl.changeConf((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.ChangeConfResponseProto>) responseObserver);
          break;
        case METHODID_CHECK_ACCESS:
          serviceImpl.checkAccess((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.CheckAccessResponseProto>) responseObserver);
          break;
        case METHODID_GET_LAST_UPDATED_CONTENT_SUMMARY:
          serviceImpl.getLastUpdatedContentSummary((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetLastUpdatedContentSummaryResponseProto>) responseObserver);
          break;
        case METHODID_ADD_USER:
          serviceImpl.addUser((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserResponseProto>) responseObserver);
          break;
        case METHODID_ADD_GROUP:
          serviceImpl.addGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddGroupResponseProto>) responseObserver);
          break;
        case METHODID_ADD_USER_TO_GROUP:
          serviceImpl.addUserToGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.AddUserToGroupResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_USER:
          serviceImpl.removeUser((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_GROUP:
          serviceImpl.removeGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveGroupResponseProto>) responseObserver);
          break;
        case METHODID_REMOVE_USER_FROM_GROUP:
          serviceImpl.removeUserFromGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.RemoveUserFromGroupResponseProto>) responseObserver);
          break;
        case METHODID_INV_CACHES_USER_REMOVED:
          serviceImpl.invCachesUserRemoved((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedResponseProto>) responseObserver);
          break;
        case METHODID_INV_CACHES_GROUP_REMOVED:
          serviceImpl.invCachesGroupRemoved((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesGroupRemovedResponseProto>) responseObserver);
          break;
        case METHODID_INV_CACHES_USER_REMOVED_FROM_GROUP:
          serviceImpl.invCachesUserRemovedFromGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserRemovedFromGroupResponseProto>) responseObserver);
          break;
        case METHODID_INV_CACHES_USER_ADDED_TO_GROUP:
          serviceImpl.invCachesUserAddedToGroup((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.InvCachesUserAddedToGroupResponseProto>) responseObserver);
          break;
        case METHODID_CREATE_ENCRYPTION_ZONE:
          serviceImpl.createEncryptionZone((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.CreateEncryptionZoneResponseProto>) responseObserver);
          break;
        case METHODID_LIST_ENCRYPTION_ZONES:
          serviceImpl.listEncryptionZones((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.ListEncryptionZonesResponseProto>) responseObserver);
          break;
        case METHODID_GET_EZFOR_PATH:
          serviceImpl.getEZForPath((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.EncryptionZonesProtos.GetEZForPathResponseProto>) responseObserver);
          break;
        case METHODID_GET_NNEPOCH_MS:
          serviceImpl.getNNEpochMS((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.GetEpochMSResponseProto>) responseObserver);
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

  private static abstract class ClientNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientNamenodeProtocolBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.ClientNamenodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientNamenodeProtocol");
    }
  }

  private static final class ClientNamenodeProtocolFileDescriptorSupplier
      extends ClientNamenodeProtocolBaseDescriptorSupplier {
    ClientNamenodeProtocolFileDescriptorSupplier() {}
  }

  private static final class ClientNamenodeProtocolMethodDescriptorSupplier
      extends ClientNamenodeProtocolBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientNamenodeProtocolMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientNamenodeProtocolGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientNamenodeProtocolFileDescriptorSupplier())
              .addMethod(getGetBlockLocationsMethod())
              .addMethod(getGetMissingBlockLocationsMethod())
              .addMethod(getAddBlockChecksumMethod())
              .addMethod(getGetBlockChecksumMethod())
              .addMethod(getGetServerDefaultsMethod())
              .addMethod(getCreateMethod())
              .addMethod(getAppendMethod())
              .addMethod(getSetReplicationMethod())
              .addMethod(getSetStoragePolicyMethod())
              .addMethod(getGetStoragePolicyMethod())
              .addMethod(getGetStoragePoliciesMethod())
              .addMethod(getSetMetaStatusMethod())
              .addMethod(getSetPermissionMethod())
              .addMethod(getSetOwnerMethod())
              .addMethod(getAbandonBlockMethod())
              .addMethod(getAddBlockMethod())
              .addMethod(getGetAdditionalDatanodeMethod())
              .addMethod(getCompleteMethod())
              .addMethod(getReportBadBlocksMethod())
              .addMethod(getConcatMethod())
              .addMethod(getTruncateMethod())
              .addMethod(getRenameMethod())
              .addMethod(getRename2Method())
              .addMethod(getDeleteMethod())
              .addMethod(getMkdirsMethod())
              .addMethod(getGetListingMethod())
              .addMethod(getRenewLeaseMethod())
              .addMethod(getRecoverLeaseMethod())
              .addMethod(getGetFsStatsMethod())
              .addMethod(getGetDatanodeReportMethod())
              .addMethod(getGetDatanodeStorageReportMethod())
              .addMethod(getGetPreferredBlockSizeMethod())
              .addMethod(getSetSafeModeMethod())
              .addMethod(getRefreshNodesMethod())
              .addMethod(getRollingUpgradeMethod())
              .addMethod(getListCorruptFileBlocksMethod())
              .addMethod(getGetFileInfoMethod())
              .addMethod(getAddCacheDirectiveMethod())
              .addMethod(getModifyCacheDirectiveMethod())
              .addMethod(getRemoveCacheDirectiveMethod())
              .addMethod(getListCacheDirectivesMethod())
              .addMethod(getAddCachePoolMethod())
              .addMethod(getModifyCachePoolMethod())
              .addMethod(getRemoveCachePoolMethod())
              .addMethod(getListCachePoolsMethod())
              .addMethod(getGetFileLinkInfoMethod())
              .addMethod(getGetContentSummaryMethod())
              .addMethod(getSetQuotaMethod())
              .addMethod(getFsyncMethod())
              .addMethod(getSetTimesMethod())
              .addMethod(getCreateSymlinkMethod())
              .addMethod(getGetLinkTargetMethod())
              .addMethod(getUpdateBlockForPipelineMethod())
              .addMethod(getUpdatePipelineMethod())
              .addMethod(getGetDelegationTokenMethod())
              .addMethod(getRenewDelegationTokenMethod())
              .addMethod(getCancelDelegationTokenMethod())
              .addMethod(getSetBalancerBandwidthMethod())
              .addMethod(getGetDataEncryptionKeyMethod())
              .addMethod(getIsFileClosedMethod())
              .addMethod(getModifyAclEntriesMethod())
              .addMethod(getRemoveAclEntriesMethod())
              .addMethod(getRemoveDefaultAclMethod())
              .addMethod(getRemoveAclMethod())
              .addMethod(getSetAclMethod())
              .addMethod(getGetAclStatusMethod())
              .addMethod(getSetXAttrMethod())
              .addMethod(getGetXAttrsMethod())
              .addMethod(getListXAttrsMethod())
              .addMethod(getRemoveXAttrMethod())
              .addMethod(getGetActiveNamenodesForClientMethod())
              .addMethod(getPingMethod())
              .addMethod(getGetEncodingStatusMethod())
              .addMethod(getEncodeFileMethod())
              .addMethod(getRevokeEncodingMethod())
              .addMethod(getGetRepairedBlockLocationsMethod())
              .addMethod(getChangeConfMethod())
              .addMethod(getCheckAccessMethod())
              .addMethod(getGetLastUpdatedContentSummaryMethod())
              .addMethod(getAddUserMethod())
              .addMethod(getAddGroupMethod())
              .addMethod(getAddUserToGroupMethod())
              .addMethod(getRemoveUserMethod())
              .addMethod(getRemoveGroupMethod())
              .addMethod(getRemoveUserFromGroupMethod())
              .addMethod(getInvCachesUserRemovedMethod())
              .addMethod(getInvCachesGroupRemovedMethod())
              .addMethod(getInvCachesUserRemovedFromGroupMethod())
              .addMethod(getInvCachesUserAddedToGroupMethod())
              .addMethod(getCreateEncryptionZoneMethod())
              .addMethod(getListEncryptionZonesMethod())
              .addMethod(getGetEZForPathMethod())
              .addMethod(getGetNNEpochMSMethod())
              .build();
        }
      }
    }
    return result;
  }
}
