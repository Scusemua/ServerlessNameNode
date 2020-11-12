package org.apache.hadoop.hdfs.protocol;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 **
 * Protocol used from client to the Datanode.
 * See the request and response for details of rpc call.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: ClientDatanodeProtocol.proto")
public final class ClientDatanodeProtocolServiceGrpc {

  private ClientDatanodeProtocolServiceGrpc() {}

  public static final String SERVICE_NAME = "org.apache.hadoop.ClientDatanodeProtocolService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> getGetReplicaVisibleLengthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getReplicaVisibleLength",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> getGetReplicaVisibleLengthMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> getGetReplicaVisibleLengthMethod;
    if ((getGetReplicaVisibleLengthMethod = ClientDatanodeProtocolServiceGrpc.getGetReplicaVisibleLengthMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getGetReplicaVisibleLengthMethod = ClientDatanodeProtocolServiceGrpc.getGetReplicaVisibleLengthMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getGetReplicaVisibleLengthMethod = getGetReplicaVisibleLengthMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getReplicaVisibleLength"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("getReplicaVisibleLength"))
              .build();
        }
      }
    }
    return getGetReplicaVisibleLengthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> getDeleteBlockPoolMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "deleteBlockPool",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> getDeleteBlockPoolMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> getDeleteBlockPoolMethod;
    if ((getDeleteBlockPoolMethod = ClientDatanodeProtocolServiceGrpc.getDeleteBlockPoolMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getDeleteBlockPoolMethod = ClientDatanodeProtocolServiceGrpc.getDeleteBlockPoolMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getDeleteBlockPoolMethod = getDeleteBlockPoolMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "deleteBlockPool"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("deleteBlockPool"))
              .build();
        }
      }
    }
    return getDeleteBlockPoolMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> getGetBlockLocalPathInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockLocalPathInfo",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> getGetBlockLocalPathInfoMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> getGetBlockLocalPathInfoMethod;
    if ((getGetBlockLocalPathInfoMethod = ClientDatanodeProtocolServiceGrpc.getGetBlockLocalPathInfoMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getGetBlockLocalPathInfoMethod = ClientDatanodeProtocolServiceGrpc.getGetBlockLocalPathInfoMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getGetBlockLocalPathInfoMethod = getGetBlockLocalPathInfoMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlockLocalPathInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("getBlockLocalPathInfo"))
              .build();
        }
      }
    }
    return getGetBlockLocalPathInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> getGetHdfsBlockLocationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getHdfsBlockLocations",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> getGetHdfsBlockLocationsMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> getGetHdfsBlockLocationsMethod;
    if ((getGetHdfsBlockLocationsMethod = ClientDatanodeProtocolServiceGrpc.getGetHdfsBlockLocationsMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getGetHdfsBlockLocationsMethod = ClientDatanodeProtocolServiceGrpc.getGetHdfsBlockLocationsMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getGetHdfsBlockLocationsMethod = getGetHdfsBlockLocationsMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getHdfsBlockLocations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("getHdfsBlockLocations"))
              .build();
        }
      }
    }
    return getGetHdfsBlockLocationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> getShutdownDatanodeMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "shutdownDatanode",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> getShutdownDatanodeMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> getShutdownDatanodeMethod;
    if ((getShutdownDatanodeMethod = ClientDatanodeProtocolServiceGrpc.getShutdownDatanodeMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getShutdownDatanodeMethod = ClientDatanodeProtocolServiceGrpc.getShutdownDatanodeMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getShutdownDatanodeMethod = getShutdownDatanodeMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "shutdownDatanode"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("shutdownDatanode"))
              .build();
        }
      }
    }
    return getShutdownDatanodeMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> getGetDatanodeInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getDatanodeInfo",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> getGetDatanodeInfoMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> getGetDatanodeInfoMethod;
    if ((getGetDatanodeInfoMethod = ClientDatanodeProtocolServiceGrpc.getGetDatanodeInfoMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getGetDatanodeInfoMethod = ClientDatanodeProtocolServiceGrpc.getGetDatanodeInfoMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getGetDatanodeInfoMethod = getGetDatanodeInfoMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getDatanodeInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("getDatanodeInfo"))
              .build();
        }
      }
    }
    return getGetDatanodeInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> getGetReconfigurationStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getReconfigurationStatus",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> getGetReconfigurationStatusMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> getGetReconfigurationStatusMethod;
    if ((getGetReconfigurationStatusMethod = ClientDatanodeProtocolServiceGrpc.getGetReconfigurationStatusMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getGetReconfigurationStatusMethod = ClientDatanodeProtocolServiceGrpc.getGetReconfigurationStatusMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getGetReconfigurationStatusMethod = getGetReconfigurationStatusMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getReconfigurationStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("getReconfigurationStatus"))
              .build();
        }
      }
    }
    return getGetReconfigurationStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> getStartReconfigurationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "startReconfiguration",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> getStartReconfigurationMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> getStartReconfigurationMethod;
    if ((getStartReconfigurationMethod = ClientDatanodeProtocolServiceGrpc.getStartReconfigurationMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getStartReconfigurationMethod = ClientDatanodeProtocolServiceGrpc.getStartReconfigurationMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getStartReconfigurationMethod = getStartReconfigurationMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "startReconfiguration"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("startReconfiguration"))
              .build();
        }
      }
    }
    return getStartReconfigurationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> getTriggerBlockReportMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "triggerBlockReport",
      requestType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto,
      org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> getTriggerBlockReportMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> getTriggerBlockReportMethod;
    if ((getTriggerBlockReportMethod = ClientDatanodeProtocolServiceGrpc.getTriggerBlockReportMethod) == null) {
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        if ((getTriggerBlockReportMethod = ClientDatanodeProtocolServiceGrpc.getTriggerBlockReportMethod) == null) {
          ClientDatanodeProtocolServiceGrpc.getTriggerBlockReportMethod = getTriggerBlockReportMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto, org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "triggerBlockReport"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceMethodDescriptorSupplier("triggerBlockReport"))
              .build();
        }
      }
    }
    return getTriggerBlockReportMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ClientDatanodeProtocolServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceStub>() {
        @java.lang.Override
        public ClientDatanodeProtocolServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientDatanodeProtocolServiceStub(channel, callOptions);
        }
      };
    return ClientDatanodeProtocolServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ClientDatanodeProtocolServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceBlockingStub>() {
        @java.lang.Override
        public ClientDatanodeProtocolServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientDatanodeProtocolServiceBlockingStub(channel, callOptions);
        }
      };
    return ClientDatanodeProtocolServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ClientDatanodeProtocolServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ClientDatanodeProtocolServiceFutureStub>() {
        @java.lang.Override
        public ClientDatanodeProtocolServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ClientDatanodeProtocolServiceFutureStub(channel, callOptions);
        }
      };
    return ClientDatanodeProtocolServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Protocol used from client to the Datanode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static abstract class ClientDatanodeProtocolServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Returns the visible length of the replica
     * </pre>
     */
    public void getReplicaVisibleLength(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto request,
                                        io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetReplicaVisibleLengthMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Delete the block pool from the datanode.
     * </pre>
     */
    public void deleteBlockPool(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto request,
                                io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getDeleteBlockPoolMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves the path names of the block file and metadata file stored on the
     * local file system.
     * </pre>
     */
    public void getBlockLocalPathInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto request,
                                      io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockLocalPathInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve additional HDFS-specific metadata about a set of blocks stored
     * on the local file system.
     * </pre>
     */
    public void getHdfsBlockLocations(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto request,
                                      io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetHdfsBlockLocationsMethod(), responseObserver);
    }

    /**
     */
    public void shutdownDatanode(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto request,
                                 io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getShutdownDatanodeMethod(), responseObserver);
    }

    /**
     */
    public void getDatanodeInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto request,
                                io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetDatanodeInfoMethod(), responseObserver);
    }

    /**
     */
    public void getReconfigurationStatus(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto request,
                                         io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetReconfigurationStatusMethod(), responseObserver);
    }

    /**
     */
    public void startReconfiguration(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto request,
                                     io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getStartReconfigurationMethod(), responseObserver);
    }

    /**
     */
    public void triggerBlockReport(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto request,
                                   io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getTriggerBlockReportMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetReplicaVisibleLengthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto>(
                  this, METHODID_GET_REPLICA_VISIBLE_LENGTH)))
          .addMethod(
            getDeleteBlockPoolMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto>(
                  this, METHODID_DELETE_BLOCK_POOL)))
          .addMethod(
            getGetBlockLocalPathInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto>(
                  this, METHODID_GET_BLOCK_LOCAL_PATH_INFO)))
          .addMethod(
            getGetHdfsBlockLocationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto>(
                  this, METHODID_GET_HDFS_BLOCK_LOCATIONS)))
          .addMethod(
            getShutdownDatanodeMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto>(
                  this, METHODID_SHUTDOWN_DATANODE)))
          .addMethod(
            getGetDatanodeInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto>(
                  this, METHODID_GET_DATANODE_INFO)))
          .addMethod(
            getGetReconfigurationStatusMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto>(
                  this, METHODID_GET_RECONFIGURATION_STATUS)))
          .addMethod(
            getStartReconfigurationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto>(
                  this, METHODID_START_RECONFIGURATION)))
          .addMethod(
            getTriggerBlockReportMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto,
                org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto>(
                  this, METHODID_TRIGGER_BLOCK_REPORT)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Protocol used from client to the Datanode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class ClientDatanodeProtocolServiceStub extends io.grpc.stub.AbstractAsyncStub<ClientDatanodeProtocolServiceStub> {
    private ClientDatanodeProtocolServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientDatanodeProtocolServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientDatanodeProtocolServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns the visible length of the replica
     * </pre>
     */
    public void getReplicaVisibleLength(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto request,
                                        io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetReplicaVisibleLengthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Delete the block pool from the datanode.
     * </pre>
     */
    public void deleteBlockPool(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto request,
                                io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDeleteBlockPoolMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieves the path names of the block file and metadata file stored on the
     * local file system.
     * </pre>
     */
    public void getBlockLocalPathInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto request,
                                      io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockLocalPathInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Retrieve additional HDFS-specific metadata about a set of blocks stored
     * on the local file system.
     * </pre>
     */
    public void getHdfsBlockLocations(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto request,
                                      io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetHdfsBlockLocationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void shutdownDatanode(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto request,
                                 io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShutdownDatanodeMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getDatanodeInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto request,
                                io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetDatanodeInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReconfigurationStatus(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto request,
                                         io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetReconfigurationStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void startReconfiguration(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto request,
                                     io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStartReconfigurationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void triggerBlockReport(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto request,
                                   io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getTriggerBlockReportMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Protocol used from client to the Datanode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class ClientDatanodeProtocolServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<ClientDatanodeProtocolServiceBlockingStub> {
    private ClientDatanodeProtocolServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientDatanodeProtocolServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientDatanodeProtocolServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns the visible length of the replica
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto getReplicaVisibleLength(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetReplicaVisibleLengthMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Delete the block pool from the datanode.
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto deleteBlockPool(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getDeleteBlockPoolMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieves the path names of the block file and metadata file stored on the
     * local file system.
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto getBlockLocalPathInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockLocalPathInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Retrieve additional HDFS-specific metadata about a set of blocks stored
     * on the local file system.
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto getHdfsBlockLocations(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetHdfsBlockLocationsMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto shutdownDatanode(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getShutdownDatanodeMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto getDatanodeInfo(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetDatanodeInfoMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto getReconfigurationStatus(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetReconfigurationStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto startReconfiguration(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getStartReconfigurationMethod(), getCallOptions(), request);
    }

    /**
     */
    public org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto triggerBlockReport(org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getTriggerBlockReportMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Protocol used from client to the Datanode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class ClientDatanodeProtocolServiceFutureStub extends io.grpc.stub.AbstractFutureStub<ClientDatanodeProtocolServiceFutureStub> {
    private ClientDatanodeProtocolServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ClientDatanodeProtocolServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ClientDatanodeProtocolServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Returns the visible length of the replica
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto> getReplicaVisibleLength(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetReplicaVisibleLengthMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Delete the block pool from the datanode.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto> deleteBlockPool(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getDeleteBlockPoolMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieves the path names of the block file and metadata file stored on the
     * local file system.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto> getBlockLocalPathInfo(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockLocalPathInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Retrieve additional HDFS-specific metadata about a set of blocks stored
     * on the local file system.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto> getHdfsBlockLocations(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetHdfsBlockLocationsMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto> shutdownDatanode(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getShutdownDatanodeMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto> getDatanodeInfo(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetDatanodeInfoMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto> getReconfigurationStatus(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetReconfigurationStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto> startReconfiguration(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getStartReconfigurationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto> triggerBlockReport(
        org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getTriggerBlockReportMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_REPLICA_VISIBLE_LENGTH = 0;
  private static final int METHODID_DELETE_BLOCK_POOL = 1;
  private static final int METHODID_GET_BLOCK_LOCAL_PATH_INFO = 2;
  private static final int METHODID_GET_HDFS_BLOCK_LOCATIONS = 3;
  private static final int METHODID_SHUTDOWN_DATANODE = 4;
  private static final int METHODID_GET_DATANODE_INFO = 5;
  private static final int METHODID_GET_RECONFIGURATION_STATUS = 6;
  private static final int METHODID_START_RECONFIGURATION = 7;
  private static final int METHODID_TRIGGER_BLOCK_REPORT = 8;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ClientDatanodeProtocolServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ClientDatanodeProtocolServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_REPLICA_VISIBLE_LENGTH:
          serviceImpl.getReplicaVisibleLength((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReplicaVisibleLengthResponseProto>) responseObserver);
          break;
        case METHODID_DELETE_BLOCK_POOL:
          serviceImpl.deleteBlockPool((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.DeleteBlockPoolResponseProto>) responseObserver);
          break;
        case METHODID_GET_BLOCK_LOCAL_PATH_INFO:
          serviceImpl.getBlockLocalPathInfo((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetBlockLocalPathInfoResponseProto>) responseObserver);
          break;
        case METHODID_GET_HDFS_BLOCK_LOCATIONS:
          serviceImpl.getHdfsBlockLocations((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetHdfsBlockLocationsResponseProto>) responseObserver);
          break;
        case METHODID_SHUTDOWN_DATANODE:
          serviceImpl.shutdownDatanode((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.ShutdownDatanodeResponseProto>) responseObserver);
          break;
        case METHODID_GET_DATANODE_INFO:
          serviceImpl.getDatanodeInfo((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetDatanodeInfoResponseProto>) responseObserver);
          break;
        case METHODID_GET_RECONFIGURATION_STATUS:
          serviceImpl.getReconfigurationStatus((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.GetReconfigurationStatusResponseProto>) responseObserver);
          break;
        case METHODID_START_RECONFIGURATION:
          serviceImpl.startReconfiguration((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.StartReconfigurationResponseProto>) responseObserver);
          break;
        case METHODID_TRIGGER_BLOCK_REPORT:
          serviceImpl.triggerBlockReport((org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.TriggerBlockReportResponseProto>) responseObserver);
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

  private static abstract class ClientDatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ClientDatanodeProtocolServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.apache.hadoop.hdfs.protocol.ClientDatanodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ClientDatanodeProtocolService");
    }
  }

  private static final class ClientDatanodeProtocolServiceFileDescriptorSupplier
      extends ClientDatanodeProtocolServiceBaseDescriptorSupplier {
    ClientDatanodeProtocolServiceFileDescriptorSupplier() {}
  }

  private static final class ClientDatanodeProtocolServiceMethodDescriptorSupplier
      extends ClientDatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ClientDatanodeProtocolServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (ClientDatanodeProtocolServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ClientDatanodeProtocolServiceFileDescriptorSupplier())
              .addMethod(getGetReplicaVisibleLengthMethod())
              .addMethod(getDeleteBlockPoolMethod())
              .addMethod(getGetBlockLocalPathInfoMethod())
              .addMethod(getGetHdfsBlockLocationsMethod())
              .addMethod(getShutdownDatanodeMethod())
              .addMethod(getGetDatanodeInfoMethod())
              .addMethod(getGetReconfigurationStatusMethod())
              .addMethod(getStartReconfigurationMethod())
              .addMethod(getTriggerBlockReportMethod())
              .build();
        }
      }
    }
    return result;
  }
}
