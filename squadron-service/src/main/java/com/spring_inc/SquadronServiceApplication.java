package com.spring_inc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import io.github.cdimascio.dotenv.Dotenv;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class SquadronServiceApplication {

	public static void main(String[] args) {
		
		// Load .env file
	    Dotenv dotenv = Dotenv.configure().load();

	    // Set system properties so Spring Boot can use them
	    System.setProperty("DB_URL", dotenv.get("DB_URL"));
	    System.setProperty("DB_USERNAME", dotenv.get("DB_USERNAME"));
	    System.setProperty("DB_PASSWORD", dotenv.get("DB_PASSWORD"));
	    System.setProperty("DB_DRIVER", dotenv.get("DB_DRIVER"));
	    
		SpringApplication.run(SquadronServiceApplication.class, args);
	}
}
