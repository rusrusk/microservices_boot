package com.ruslank.springboot_microservices_inventory_app.repositories;

import com.ruslank.springboot_microservices_inventory_app.entities.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {

    List<Inventory> findBySkuCodeIn(List<String> skuCode);
}
