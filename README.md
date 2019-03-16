Test a bug encountered in previous version of grpc-java(before 1.6.0, exclude)

## Environment

Windows 10 pro 1809  
VS Code 1.32.3(user setup)  
Several Java Extensions to support Maven and debug

## Test

In this test, two versions of grpc-java are used.  

In the rpcserver-1.6.1 folder, v1.6.1 is used. It creats both server and client connect to the server itself.  

In the rpcclient-1.4.0 folder, v1.4.0 is used. It creats only client connect to the v1.6.1 server.

So first launch the TestRpcServer main, second launch the TestRpcClient main.

## RESULT:  

Server is always right. Client 1.6.1 running in the TestRpcServer main is right.

Client 1.4.0 running in the TestRpcClient main terminates. Before it terminates, it will call `jmap histo:live <pid>` to get the memory data.

The last output of the crash is like below:

```
Mar 17, 2019 12:52:14 AM io.grpc.netty.NettyClientHandler$1 onGoAwayReceived
WARNING: Received GOAWAY with ENHANCE_YOUR_CALM. Debug data: {1}
Mar 17, 2019 12:52:14 AM io.grpc.internal.AtomicBackoff$State backoff
WARNING: Increased keepalive time nanos to 20,000,000,000
Mar 17, 2019 12:52:14 AM tests.TestRpcClient startRpcClient
INFO: req sent: 22217, 35996
Mar 17, 2019 12:52:14 AM tests.TestRpcClient startRpcClient
WARNING: 
io.grpc.StatusRuntimeException: RESOURCE_EXHAUSTED: Bandwidth exhausted
HTTP/2 error code: ENHANCE_YOUR_CALM
Received Goaway
too_many_pings
	at io.grpc.stub.ClientCalls.toStatusRuntimeException(ClientCalls.java:227)
	at io.grpc.stub.ClientCalls.getUnchecked(ClientCalls.java:208)
	at io.grpc.stub.ClientCalls.blockingUnaryCall(ClientCalls.java:141)
	at grpc.TestRpcServiceGrpc$TestRpcServiceBlockingStub.handle(TestRpcServiceGrpc.java:138)
	at tests.TestRpcClient.startRpcClient(TestRpcClient.java:40)
	at tests.TestRpcClient.main(TestRpcClient.java:15)

Mar 17, 2019 12:52:14 AM tests.TestRpcClient getJVM
INFO: jmap -histo:live 9032
   1:         21195        1186920  io.netty.util.concurrent.ScheduledFutureTask
   2:          9301         826416  [C
   3:          1910         628992  [Ljava.lang.Object;
   4:         21194         508656  io.netty.util.concurrent.PromiseTask$RunnableAdapter
   5:          2398         268400  java.lang.Class
```

In the output there are two problems:
1. too_many_pings in the log
2. too many io.netty.util.concurrent.ScheduledFutureTask instances from the jmap output


## SOLVE

For the first appearance, it seems like two separate issues, one is about keep alive interval, another is memory leak.

But after some search from Google, and read the discussion from several issues in GitHub, I found it was a previous bug in grpc-java(before 1.6.0). The reason was in the KeepAliveManager. When client sends request very frequently, the ping is frequently creating some future object, cause the too many instances. Also more than regular pings are sent to the server, cause the too many pings returned by the server.

This bug is fixed in grpc-java 1.6.0, according to this issue:  
Don't schedule multiple pings. #3295  
https://github.com/grpc/grpc-java/pull/3295
