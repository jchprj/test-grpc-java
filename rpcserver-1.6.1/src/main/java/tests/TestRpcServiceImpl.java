package tests;

import io.grpc.stub.StreamObserver;
import grpc.TestRPCProto.Request;
import grpc.TestRPCProto.Response;
import grpc.TestRpcServiceGrpc.TestRpcServiceImplBase;

public class TestRpcServiceImpl extends TestRpcServiceImplBase {
	long t = System.currentTimeMillis();
	@Override
	public void handle(Request request, StreamObserver<Response> responseObserver) {
		long t1 = System.currentTimeMillis();
		// System.out.println("req received: " + (t1 - t) + ", " + request.getCode());
		Response response = Response.newBuilder().build();
		responseObserver.onNext(response);
		responseObserver.onCompleted();
	}
}