package tests;

import io.grpc.Channel;
import io.grpc.netty.NettyChannelBuilder;
import grpc.TestRpcServiceGrpc;
import grpc.TestRpcServiceGrpc.*;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

public class RpcClient {
	private Channel channel;
	private TestRpcServiceBlockingStub stub;
	private TestRpcServiceFutureStub syncStub;

	public RpcClient(int grpcKeepAliveTime, String ip, int port) {
		SocketAddress socketAddress = new InetSocketAddress(ip, port);
		NettyChannelBuilder channelBuilder = NettyChannelBuilder.forAddress(socketAddress);
		channelBuilder.usePlaintext(true).keepAliveTime(grpcKeepAliveTime, TimeUnit.SECONDS).maxInboundMessageSize(
				2 * 1024 * 1024);
		channel = channelBuilder.build();
        stub = TestRpcServiceGrpc.newBlockingStub(channel);
		syncStub = TestRpcServiceGrpc.newFutureStub(channel);
	}

	public TestRpcServiceBlockingStub getStub() {
		return stub;
	}

	public TestRpcServiceFutureStub getSyncStub() {
		return syncStub;
	}

	public Channel getChannel() {
		return channel;
	}
}
