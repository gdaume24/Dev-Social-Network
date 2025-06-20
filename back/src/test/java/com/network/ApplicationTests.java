package com.network;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
class ApplicationTests {

    @BeforeAll
    static void loadEnv() {
        // Ne charger .env que si on est en local (par exemple profil "dev")
        String activeProfile = System.getenv("ACTIVE_PROFILE");
        System.out.println("Active profileAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA: " + activeProfile);
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
    }
	@Test
	void contextLoads() {
	}

}
