package com.network;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.github.cdimascio.dotenv.Dotenv;

@SpringBootTest
class ApplicationTests {

    @BeforeAll
    static void loadEnv() {
        Dotenv dotenv = Dotenv
                .configure()
                .load();

        dotenv.entries().forEach(entry ->
                System.setProperty(entry.getKey(), entry.getValue())
        );
    }
	@Test
	void contextLoads() {
	}

}
