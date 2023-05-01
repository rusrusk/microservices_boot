package com.ruslank.springboot_microservices_inventory_app;

import com.ruslank.springboot_microservices_inventory_app.repositories.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootMicroservicesInventoryAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootMicroservicesInventoryAppApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {

	}

}
