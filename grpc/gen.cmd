REM should provide valid protoc-gen-grpc-java.exe
REM currently used protoc version is 3.3.0

protoc-3.3.0  --java_out=../rpcclient-1.4.0/src/main/java TestRPC.proto
protoc-3.3.0 --plugin=protoc-gen-grpc-java=d:\g\cmds\protoc-gen-grpc-java.exe --grpc-java_out=../rpcclient-1.4.0/src/main/java TestRPC.proto
protoc-3.3.0  --java_out=../rpcserver-1.6.1/src/main/java TestRPC.proto
protoc-3.3.0 --plugin=protoc-gen-grpc-java=d:\g\cmds\protoc-gen-grpc-java.exe --grpc-java_out=../rpcserver-1.6.1/src/main/java TestRPC.proto