package com.network;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.cdimascio.dotenv.Dotenv;

/**
 * Starter.
 * Load .env variables in the .back directory related to the terminal path. 
 * Because application.properties rely on .env variables.
 */
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
