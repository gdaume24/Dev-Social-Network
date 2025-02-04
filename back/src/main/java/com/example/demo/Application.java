package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		Dotenv dotenv = Dotenv
		.configure()
		.directory("./back")
		.load()
		;
		dotenv.entries().forEach(entry ->
		System.setProperty(entry.getKey(), entry.getValue())
		);
		SpringApplication.run(Application.class, args);
	}

}
