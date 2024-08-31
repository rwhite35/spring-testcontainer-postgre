package com.demo.greetings;

import org.springframework.boot.SpringApplication;

public class TestGreetingsApplication {

	public static void main(String[] args) {
		SpringApplication.from(GreetingsApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
