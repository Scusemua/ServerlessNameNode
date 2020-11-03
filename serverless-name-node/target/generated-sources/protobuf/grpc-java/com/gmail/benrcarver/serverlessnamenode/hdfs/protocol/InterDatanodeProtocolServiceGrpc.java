package com.gmail.benrcarver.serverlessnamenode.hdfs.protocol;

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
 * Protocol used between datanodes for block recovery.
 * See the request and response for details of rpc call.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: InterDatanodeProtocol.proto")
public final class InterDatanodeProtocolServiceGrpc {

  private InterDatanodeProtocolServiceGrpc() {}

  public static final String SERVICE_NAME = "com.gmail.benrcarver.serverlessnamenode.InterDatanodeProtocolService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> getInitReplicaRecoveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "initReplicaRecovery",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> getInitReplicaRecoveryMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> getInitReplicaRecoveryMethod;
    if ((getInitReplicaRecoveryMethod = InterDatanodeProtocolServiceGrpc.getInitReplicaRecoveryMethod) == null) {
      synchronized (InterDatanodeProtocolServiceGrpc.class) {
        if ((getInitReplicaRecoveryMethod = InterDatanodeProtocolServiceGrpc.getInitReplicaRecoveryMethod) == null) {
          InterDatanodeProtocolServiceGrpc.getInitReplicaRecoveryMethod = getInitReplicaRecoveryMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "initReplicaRecovery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new InterDatanodeProtocolServiceMethodDescriptorSupplier("initReplicaRecovery"))
              .build();
        }
      }
    }
    return getInitReplicaRecoveryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> getUpdateReplicaUnderRecoveryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateReplicaUnderRecovery",
      requestType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto.class,
      responseType = com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto,
      com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> getUpdateReplicaUnderRecoveryMethod() {
    io.grpc.MethodDescriptor<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> getUpdateReplicaUnderRecoveryMethod;
    if ((getUpdateReplicaUnderRecoveryMethod = InterDatanodeProtocolServiceGrpc.getUpdateReplicaUnderRecoveryMethod) == null) {
      synchronized (InterDatanodeProtocolServiceGrpc.class) {
        if ((getUpdateReplicaUnderRecoveryMethod = InterDatanodeProtocolServiceGrpc.getUpdateReplicaUnderRecoveryMethod) == null) {
          InterDatanodeProtocolServiceGrpc.getUpdateReplicaUnderRecoveryMethod = getUpdateReplicaUnderRecoveryMethod =
              io.grpc.MethodDescriptor.<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto, com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateReplicaUnderRecovery"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new InterDatanodeProtocolServiceMethodDescriptorSupplier("updateReplicaUnderRecovery"))
              .build();
        }
      }
    }
    return getUpdateReplicaUnderRecoveryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static InterDatanodeProtocolServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceStub>() {
        @java.lang.Override
        public InterDatanodeProtocolServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InterDatanodeProtocolServiceStub(channel, callOptions);
        }
      };
    return InterDatanodeProtocolServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static InterDatanodeProtocolServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceBlockingStub>() {
        @java.lang.Override
        public InterDatanodeProtocolServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InterDatanodeProtocolServiceBlockingStub(channel, callOptions);
        }
      };
    return InterDatanodeProtocolServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static InterDatanodeProtocolServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<InterDatanodeProtocolServiceFutureStub>() {
        @java.lang.Override
        public InterDatanodeProtocolServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new InterDatanodeProtocolServiceFutureStub(channel, callOptions);
        }
      };
    return InterDatanodeProtocolServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Protocol used between datanodes for block recovery.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static abstract class InterDatanodeProtocolServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Initialize recovery of a replica
     * </pre>
     */
    public void initReplicaRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getInitReplicaRecoveryMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Update a replica with new generation stamp and length
     * </pre>
     */
    public void updateReplicaUnderRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto request,
                                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getUpdateReplicaUnderRecoveryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getInitReplicaRecoveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto>(
                  this, METHODID_INIT_REPLICA_RECOVERY)))
          .addMethod(
            getUpdateReplicaUnderRecoveryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto,
                com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto>(
                  this, METHODID_UPDATE_REPLICA_UNDER_RECOVERY)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Protocol used between datanodes for block recovery.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class InterDatanodeProtocolServiceStub extends io.grpc.stub.AbstractAsyncStub<InterDatanodeProtocolServiceStub> {
    private InterDatanodeProtocolServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterDatanodeProtocolServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InterDatanodeProtocolServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initialize recovery of a replica
     * </pre>
     */
    public void initReplicaRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto request,
                                    io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInitReplicaRecoveryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Update a replica with new generation stamp and length
     * </pre>
     */
    public void updateReplicaUnderRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto request,
                                           io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUpdateReplicaUnderRecoveryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Protocol used between datanodes for block recovery.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class InterDatanodeProtocolServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<InterDatanodeProtocolServiceBlockingStub> {
    private InterDatanodeProtocolServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterDatanodeProtocolServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InterDatanodeProtocolServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initialize recovery of a replica
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto initReplicaRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getInitReplicaRecoveryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Update a replica with new generation stamp and length
     * </pre>
     */
    public com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto updateReplicaUnderRecovery(com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getUpdateReplicaUnderRecoveryMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Protocol used between datanodes for block recovery.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class InterDatanodeProtocolServiceFutureStub extends io.grpc.stub.AbstractFutureStub<InterDatanodeProtocolServiceFutureStub> {
    private InterDatanodeProtocolServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected InterDatanodeProtocolServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new InterDatanodeProtocolServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Initialize recovery of a replica
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto> initReplicaRecovery(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getInitReplicaRecoveryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Update a replica with new generation stamp and length
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto> updateReplicaUnderRecovery(
        com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getUpdateReplicaUnderRecoveryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_INIT_REPLICA_RECOVERY = 0;
  private static final int METHODID_UPDATE_REPLICA_UNDER_RECOVERY = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final InterDatanodeProtocolServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(InterDatanodeProtocolServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_INIT_REPLICA_RECOVERY:
          serviceImpl.initReplicaRecovery((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.InitReplicaRecoveryResponseProto>) responseObserver);
          break;
        case METHODID_UPDATE_REPLICA_UNDER_RECOVERY:
          serviceImpl.updateReplicaUnderRecovery((com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryRequestProto) request,
              (io.grpc.stub.StreamObserver<com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.UpdateReplicaUnderRecoveryResponseProto>) responseObserver);
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

  private static abstract class InterDatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    InterDatanodeProtocolServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.gmail.benrcarver.serverlessnamenode.hdfs.protocol.InterDatanodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("InterDatanodeProtocolService");
    }
  }

  private static final class InterDatanodeProtocolServiceFileDescriptorSupplier
      extends InterDatanodeProtocolServiceBaseDescriptorSupplier {
    InterDatanodeProtocolServiceFileDescriptorSupplier() {}
  }

  private static final class InterDatanodeProtocolServiceMethodDescriptorSupplier
      extends InterDatanodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    InterDatanodeProtocolServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (InterDatanodeProtocolServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new InterDatanodeProtocolServiceFileDescriptorSupplier())
              .addMethod(getInitReplicaRecoveryMethod())
              .addMethod(getUpdateReplicaUnderRecoveryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
