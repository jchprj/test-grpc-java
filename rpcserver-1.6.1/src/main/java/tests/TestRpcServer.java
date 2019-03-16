package tests;
import java.io.IOException;
import java.io.InputStream;
import java.lang.management.ManagementFactory;
import java.util.logging.Level;
import java.util.logging.Logger;

import grpc.TestRPCProto.Request;

public class TestRpcServer {

	private static int pid;
	public static void main(String[] args)  {
		getPid();
		startRpcServer();
		startRpcClient();
	}

	private static void getPid() {
		String pidName = ManagementFactory.getRuntimeMXBean().getName();
		String[] pidSplit = pidName.split("@");
		pid = Integer.parseInt(pidSplit[0]);
		System.out.println(pid);
	}

	private static void startRpcServer() {
		try {
			new RpcServer(3, "127.0.0.1", 8850);
		}catch(Exception e) {
			System.out.println("exception");
			System.out.println(e.getStackTrace().toString());
		}
		System.out.println("RpcServer 1.6.1 started");
	}

	private static void startRpcClient() {
		RpcClient c = new RpcClient(1, "127.0.0.1", 8850);
		System.out.println("RpcClient 1.6.1 started");
		
		getJVM(5);

		long t = System.currentTimeMillis();
		int n = 5000;
		int sent = 0;
		Logger.getGlobal().info("req sending: " + 0 + ", " + sent + "-" + (sent + 5000));
		try {
			while(true) {
				for(int i = 0; i < n; i++) {
					Request req = Request.newBuilder().setCode(sent++).build();
					c.getStub().handle(req);
				}
				long t1 = System.currentTimeMillis();
				Logger.getGlobal().info("req sending: " + (t1 - t) + ", " + sent + "-" + (sent + 5000));
				getJVM(1);
				sleep(1000);
			}
		}catch(Exception e) {
			long t1 = System.currentTimeMillis();
			Logger.getGlobal().info("req sent: " + (t1 - t) + ", " + sent);
			Logger.getGlobal().log(Level.WARNING, "", e);
		}
	}

	private static void sleep(long ms) {
		try {
			Thread.sleep(ms);
		}catch(Exception e) {
			System.out.println(e.getStackTrace().toString());
		}
	}

	private static void getJVM(int showLines) {
		try {
			String command = "jmap -histo:live " + pid;
			Logger.getGlobal().info(command);
			final Process process = Runtime.getRuntime().exec(command);
			final InputStream in = process.getInputStream();
			int ch;
			StringBuilder line = new StringBuilder();
			int moreLines = 0;
			while((ch = in.read()) != -1) {
				if(ch == '\n') {
					if(line.indexOf("io.netty.util.concurrent.ScheduledFutureTask") != -1) {
						moreLines = showLines;
					}
					if(moreLines > 0) {
						System.out.println(line.toString());
						moreLines--;
					}
					line = new StringBuilder();
				}else{
					line.append((char)ch);
				}
			}
			if(line.indexOf("Scheduled") != -1) {
				System.out.println(line.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}