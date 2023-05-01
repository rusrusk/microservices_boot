package com.ruslank.springboot_microservices_inventory_app;

import com.ruslank.springboot_microservices_inventory_app.entities.Inventory;
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

	//inventoryRepository will be injected through method param
	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
		//create supplier and create object
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setSkuCode("iWatch");
			inventory.setQuantity(20);

			Inventory inventory1 = new Inventory();
			inventory1.setSkuCode("AirPods");
			inventory1.setQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);

		};
	}

}
