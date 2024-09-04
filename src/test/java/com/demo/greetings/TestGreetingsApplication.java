package com.demo.greetings;

import org.springframework.boot.SpringApplication;

public class TestGreetingsApplication {

	// Build/Run test environment to also start
	// Greeting API service (Application) from this main!

	public static void main(String[] args) {
		SpringApplication
				.from(Application::main)
				.with(TestcontainersConfiguration.class)
				.run(args);
	}
}
