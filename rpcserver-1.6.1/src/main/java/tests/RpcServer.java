package tests;

import io.grpc.netty.NettyServerBuilder;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

public class RpcServer {

	public RpcServer(int grpcKeepAliveTime, String ip, int port) throws Exception {
		int cpuNum = Runtime.getRuntime().availableProcessors();
		EventLoopGroup bossGroup = new NioEventLoopGroup(cpuNum * 2);
		EventLoopGroup workGroup = new NioEventLoopGroup(cpuNum * 4 + 1);
		
		SocketAddress socketAddress = new InetSocketAddress(ip, port);
		NettyServerBuilder rpcServer = NettyServerBuilder.forAddress(socketAddress);
		rpcServer
				.addService(new TestRpcServiceImpl())
				.channelType(NioServerSocketChannel.class)
				.bossEventLoopGroup(bossGroup)
				.workerEventLoopGroup(workGroup)
				.keepAliveTime(grpcKeepAliveTime, TimeUnit.SECONDS)
				.keepAliveTimeout(grpcKeepAliveTime, TimeUnit.SECONDS)
				.maxConcurrentCallsPerConnection(128)
				.maxMessageSize(2 * 1024 * 1024)
				.permitKeepAliveTime(grpcKeepAliveTime, TimeUnit.SECONDS)
		;
		rpcServer
				.build()
				.start();
	}
}