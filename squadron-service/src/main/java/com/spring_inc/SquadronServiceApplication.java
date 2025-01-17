package com.spring_inc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SquadronServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(SquadronServiceApplication.class, args);
	}

}
