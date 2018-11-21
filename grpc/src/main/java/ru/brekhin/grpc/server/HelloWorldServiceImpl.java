package ru.brekhin.grpc.server;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.brekhin.grpc.Greeting;
import ru.brekhin.grpc.HelloWorldServiceGrpc;
import ru.brekhin.grpc.Person;

@GRpcService
public class HelloWorldServiceImpl extends HelloWorldServiceGrpc.HelloWorldServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(HelloWorldServiceImpl.class);

    @Override
    public void sayHello(Person request, StreamObserver<Greeting> responseObserver) {
        LOGGER.info("server received {}", request);
        String message = "Hello, " + request.getFirstName() + " " + request.getLastName();
        Greeting greeting = Greeting.newBuilder()
                        .setMessage(message)
                        .build();

        LOGGER.info("server responded {}", greeting);

        responseObserver.onNext(greeting);
        responseObserver.onCompleted();
    }
}
