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
 * Protocol used by the sub-ordinate namenode to send requests
 * the active/primary namenode.
 * See the request and response for details of rpc call.
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.32.1)",
    comments = "Source: NamenodeProtocol.proto")
public final class NamenodeProtocolServiceGrpc {

  private NamenodeProtocolServiceGrpc() {}

  public static final String SERVICE_NAME = "org.apache.hadoop.namenode.NamenodeProtocolService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto,
      org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> getGetBlocksMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlocks",
      requestType = org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto,
      org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> getGetBlocksMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto, org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> getGetBlocksMethod;
    if ((getGetBlocksMethod = NamenodeProtocolServiceGrpc.getGetBlocksMethod) == null) {
      synchronized (NamenodeProtocolServiceGrpc.class) {
        if ((getGetBlocksMethod = NamenodeProtocolServiceGrpc.getGetBlocksMethod) == null) {
          NamenodeProtocolServiceGrpc.getGetBlocksMethod = getGetBlocksMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto, org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlocks"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new NamenodeProtocolServiceMethodDescriptorSupplier("getBlocks"))
              .build();
        }
      }
    }
    return getGetBlocksMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto,
      org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> getGetBlockKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getBlockKeys",
      requestType = org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto,
      org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> getGetBlockKeysMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto, org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> getGetBlockKeysMethod;
    if ((getGetBlockKeysMethod = NamenodeProtocolServiceGrpc.getGetBlockKeysMethod) == null) {
      synchronized (NamenodeProtocolServiceGrpc.class) {
        if ((getGetBlockKeysMethod = NamenodeProtocolServiceGrpc.getGetBlockKeysMethod) == null) {
          NamenodeProtocolServiceGrpc.getGetBlockKeysMethod = getGetBlockKeysMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto, org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getBlockKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new NamenodeProtocolServiceMethodDescriptorSupplier("getBlockKeys"))
              .build();
        }
      }
    }
    return getGetBlockKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto,
      org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "versionRequest",
      requestType = org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto.class,
      responseType = org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto,
      org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod() {
    io.grpc.MethodDescriptor<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto, org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> getVersionRequestMethod;
    if ((getVersionRequestMethod = NamenodeProtocolServiceGrpc.getVersionRequestMethod) == null) {
      synchronized (NamenodeProtocolServiceGrpc.class) {
        if ((getVersionRequestMethod = NamenodeProtocolServiceGrpc.getVersionRequestMethod) == null) {
          NamenodeProtocolServiceGrpc.getVersionRequestMethod = getVersionRequestMethod =
              io.grpc.MethodDescriptor.<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto, org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "versionRequest"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto.getDefaultInstance()))
              .setSchemaDescriptor(new NamenodeProtocolServiceMethodDescriptorSupplier("versionRequest"))
              .build();
        }
      }
    }
    return getVersionRequestMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static NamenodeProtocolServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceStub>() {
        @java.lang.Override
        public NamenodeProtocolServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamenodeProtocolServiceStub(channel, callOptions);
        }
      };
    return NamenodeProtocolServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static NamenodeProtocolServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceBlockingStub>() {
        @java.lang.Override
        public NamenodeProtocolServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamenodeProtocolServiceBlockingStub(channel, callOptions);
        }
      };
    return NamenodeProtocolServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static NamenodeProtocolServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<NamenodeProtocolServiceFutureStub>() {
        @java.lang.Override
        public NamenodeProtocolServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new NamenodeProtocolServiceFutureStub(channel, callOptions);
        }
      };
    return NamenodeProtocolServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   **
   * Protocol used by the sub-ordinate namenode to send requests
   * the active/primary namenode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static abstract class NamenodeProtocolServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     **
     * Get list of blocks for a given datanode with length
     * of blocks adding up to given size.
     * </pre>
     */
    public void getBlocks(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto request,
                          io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlocksMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Get the current block keys
     * </pre>
     */
    public void getBlockKeys(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto request,
                             io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBlockKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     **
     * Request info about the version running on this NameNode
     * </pre>
     */
    public void versionRequest(org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto request,
                               io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> responseObserver) {
      asyncUnimplementedUnaryCall(getVersionRequestMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetBlocksMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto,
                org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto>(
                  this, METHODID_GET_BLOCKS)))
          .addMethod(
            getGetBlockKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto,
                org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto>(
                  this, METHODID_GET_BLOCK_KEYS)))
          .addMethod(
            getVersionRequestMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto,
                org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto>(
                  this, METHODID_VERSION_REQUEST)))
          .build();
    }
  }

  /**
   * <pre>
   **
   * Protocol used by the sub-ordinate namenode to send requests
   * the active/primary namenode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class NamenodeProtocolServiceStub extends io.grpc.stub.AbstractAsyncStub<NamenodeProtocolServiceStub> {
    private NamenodeProtocolServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeProtocolServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamenodeProtocolServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Get list of blocks for a given datanode with length
     * of blocks adding up to given size.
     * </pre>
     */
    public void getBlocks(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto request,
                          io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Get the current block keys
     * </pre>
     */
    public void getBlockKeys(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto request,
                             io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBlockKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     **
     * Request info about the version running on this NameNode
     * </pre>
     */
    public void versionRequest(org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto request,
                               io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getVersionRequestMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   **
   * Protocol used by the sub-ordinate namenode to send requests
   * the active/primary namenode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class NamenodeProtocolServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<NamenodeProtocolServiceBlockingStub> {
    private NamenodeProtocolServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeProtocolServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamenodeProtocolServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Get list of blocks for a given datanode with length
     * of blocks adding up to given size.
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto getBlocks(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetBlocksMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Get the current block keys
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto getBlockKeys(org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getGetBlockKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     **
     * Request info about the version running on this NameNode
     * </pre>
     */
    public org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto versionRequest(org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto request) {
      return blockingUnaryCall(
          getChannel(), getVersionRequestMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   **
   * Protocol used by the sub-ordinate namenode to send requests
   * the active/primary namenode.
   * See the request and response for details of rpc call.
   * </pre>
   */
  public static final class NamenodeProtocolServiceFutureStub extends io.grpc.stub.AbstractFutureStub<NamenodeProtocolServiceFutureStub> {
    private NamenodeProtocolServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected NamenodeProtocolServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new NamenodeProtocolServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     **
     * Get list of blocks for a given datanode with length
     * of blocks adding up to given size.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto> getBlocks(
        org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlocksMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Get the current block keys
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto> getBlockKeys(
        org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBlockKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     **
     * Request info about the version running on this NameNode
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto> versionRequest(
        org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto request) {
      return futureUnaryCall(
          getChannel().newCall(getVersionRequestMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_BLOCKS = 0;
  private static final int METHODID_GET_BLOCK_KEYS = 1;
  private static final int METHODID_VERSION_REQUEST = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final NamenodeProtocolServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(NamenodeProtocolServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_BLOCKS:
          serviceImpl.getBlocks((org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlocksResponseProto>) responseObserver);
          break;
        case METHODID_GET_BLOCK_KEYS:
          serviceImpl.getBlockKeys((org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.GetBlockKeysResponseProto>) responseObserver);
          break;
        case METHODID_VERSION_REQUEST:
          serviceImpl.versionRequest((org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionRequestProto) request,
              (io.grpc.stub.StreamObserver<org.apache.hadoop.hdfs.protocol.HdfsProtos.VersionResponseProto>) responseObserver);
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

  private static abstract class NamenodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    NamenodeProtocolServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return org.apache.hadoop.hdfs.protocol.NamenodeProtocolProtos.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("NamenodeProtocolService");
    }
  }

  private static final class NamenodeProtocolServiceFileDescriptorSupplier
      extends NamenodeProtocolServiceBaseDescriptorSupplier {
    NamenodeProtocolServiceFileDescriptorSupplier() {}
  }

  private static final class NamenodeProtocolServiceMethodDescriptorSupplier
      extends NamenodeProtocolServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    NamenodeProtocolServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (NamenodeProtocolServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new NamenodeProtocolServiceFileDescriptorSupplier())
              .addMethod(getGetBlocksMethod())
              .addMethod(getGetBlockKeysMethod())
              .addMethod(getVersionRequestMethod())
              .build();
        }
      }
    }
    return result;
  }
}
