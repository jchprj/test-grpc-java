package grpc;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.6.1)",
    comments = "Source: TestRPC.proto")
public final class TestRpcServiceGrpc {

  private TestRpcServiceGrpc() {}

  public static final String SERVICE_NAME = "grpc.TestRpcService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<grpc.TestRPCProto.Request,
      grpc.TestRPCProto.Response> METHOD_HANDLE =
      io.grpc.MethodDescriptor.<grpc.TestRPCProto.Request, grpc.TestRPCProto.Response>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "grpc.TestRpcService", "handle"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.TestRPCProto.Request.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              grpc.TestRPCProto.Response.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TestRpcServiceStub newStub(io.grpc.Channel channel) {
    return new TestRpcServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TestRpcServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TestRpcServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TestRpcServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TestRpcServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class TestRpcServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void handle(grpc.TestRPCProto.Request request,
        io.grpc.stub.StreamObserver<grpc.TestRPCProto.Response> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_HANDLE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_HANDLE,
            asyncUnaryCall(
              new MethodHandlers<
                grpc.TestRPCProto.Request,
                grpc.TestRPCProto.Response>(
                  this, METHODID_HANDLE)))
          .build();
    }
  }

  /**
   */
  public static final class TestRpcServiceStub extends io.grpc.stub.AbstractStub<TestRpcServiceStub> {
    private TestRpcServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestRpcServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestRpcServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestRpcServiceStub(channel, callOptions);
    }

    /**
     */
    public void handle(grpc.TestRPCProto.Request request,
        io.grpc.stub.StreamObserver<grpc.TestRPCProto.Response> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_HANDLE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TestRpcServiceBlockingStub extends io.grpc.stub.AbstractStub<TestRpcServiceBlockingStub> {
    private TestRpcServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestRpcServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestRpcServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestRpcServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public grpc.TestRPCProto.Response handle(grpc.TestRPCProto.Request request) {
      return blockingUnaryCall(
          getChannel(), METHOD_HANDLE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TestRpcServiceFutureStub extends io.grpc.stub.AbstractStub<TestRpcServiceFutureStub> {
    private TestRpcServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TestRpcServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TestRpcServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TestRpcServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<grpc.TestRPCProto.Response> handle(
        grpc.TestRPCProto.Request request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_HANDLE, getCallOptions()), request);
    }
  }

  private static final int METHODID_HANDLE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TestRpcServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TestRpcServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HANDLE:
          serviceImpl.handle((grpc.TestRPCProto.Request) request,
              (io.grpc.stub.StreamObserver<grpc.TestRPCProto.Response>) responseObserver);
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

  private static final class TestRpcServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return grpc.TestRPCProto.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TestRpcServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TestRpcServiceDescriptorSupplier())
              .addMethod(METHOD_HANDLE)
              .build();
        }
      }
    }
    return result;
  }
}
