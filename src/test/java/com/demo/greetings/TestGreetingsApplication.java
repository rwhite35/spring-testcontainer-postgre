package com.demo.greetings;

import org.springframework.boot.SpringApplication;

public class TestGreetingsApplication {

	// NOTE: Is actaully starting GreetingApplication
	// from within the TestGreetingsAccplication!

	public static void main(String[] args) {
		SpringApplication
				.from(Application::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}
}
