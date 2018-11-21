package ru.brekhin.grpc.client;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.brekhin.grpc.Greeting;
import ru.brekhin.grpc.HelloWorldServiceGrpc;
import ru.brekhin.grpc.Person;

import javax.annotation.PostConstruct;

@Component
public class HeloWorldClient {
    private static Logger logger = LoggerFactory.getLogger(HeloWorldClient.class);

    private HelloWorldServiceGrpc.HelloWorldServiceBlockingStub helloWorldServiceBlockingStub;

    @PostConstruct
    private void init() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 6565)
                .usePlaintext()
                .build();
        helloWorldServiceBlockingStub = HelloWorldServiceGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello(String firstName, String lastName) {
        Person person = Person.newBuilder()
                .setFirstName(firstName)
                .setLastName(lastName)
                .build();

        logger.info("client sending {}", person);

        Greeting greeting =
                helloWorldServiceBlockingStub.sayHello(person);

        logger.info("client received {}", greeting);

        return greeting.getMessage();
    }



}
