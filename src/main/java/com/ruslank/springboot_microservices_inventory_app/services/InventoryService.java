package com.ruslank.springboot_microservices_inventory_app.services;

import com.ruslank.springboot_microservices_inventory_app.entities.Inventory;
import com.ruslank.springboot_microservices_inventory_app.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class InventoryService {


    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInventoryInStock(String skuCode) {
        Optional<Inventory> inventory = this.inventoryRepository.findBySkuCode();
         return inventory.isPresent();
    }
}
