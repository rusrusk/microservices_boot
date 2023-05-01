package com.ruslank.springboot_microservices_inventory_app.services;

import com.ruslank.springboot_microservices_inventory_app.dto.InventoryResponse;
import com.ruslank.springboot_microservices_inventory_app.entities.Inventory;
import com.ruslank.springboot_microservices_inventory_app.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventoryService {


    private final InventoryRepository inventoryRepository;
    //querying db to find out all inventory objects
    @Transactional(readOnly = true)
    public List<InventoryResponse> ListOfInventoriesInStock(List<String> listOfSkuCodes) {
        List<Inventory> inventory = this.inventoryRepository.findBySkuCodeIn(listOfSkuCodes);
         return inventory
                 .stream()
                 .map(i ->
                     InventoryResponse.builder()
                             .skuCode(i.getSkuCode())
                             .isInventoryInStock(i.getQuantity() > 0)
                             .build())
                 .collect(Collectors.toList());
    }
}
