package ru.brekhin.grpc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.brekhin.grpc.client.HeloWorldClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GrpcApplicationTests {

	@Autowired
	private HeloWorldClient heloWorldClient;

	@Test
	public void contextLoads() {
		assertThat(heloWorldClient.sayHello("John", "Doe")).
				isEqualTo("Hello, John Doe");
	}
}
