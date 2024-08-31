package com.demo.greetings;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class GreetingsApplicationTests {

	@Test
	void contextLoads() {
	}

}
