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

		// Ne charger .env que si on est en local (par exemple profil "dev")
		String activeProfile = System.getenv("ACTIVE_PROFILE");

		if ("dev".equalsIgnoreCase(activeProfile)) {
			try {
				Dotenv dotenv = Dotenv.configure().load();
				dotenv.entries().forEach(entry ->
						System.setProperty(entry.getKey(), entry.getValue())
				);
			} catch (Exception e) {
				System.err.println("⚠️ Impossible de charger le .env en profil dev : " + e.getMessage());
			}
		}
		SpringApplication.run(Application.class, args);
	}

}
