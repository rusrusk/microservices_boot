package com.ruslank.eureka_discovery_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaDiscoveryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaDiscoveryServiceApplication.class, args);
	}

}
