package com.ruslank.springboot_microservices_inventory_app.controllers;

import com.ruslank.springboot_microservices_inventory_app.dto.InventoryResponse;
import com.ruslank.springboot_microservices_inventory_app.entities.Inventory;
import com.ruslank.springboot_microservices_inventory_app.services.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInventoryInStock(@RequestParam List<String> listOfSkuCodes) {
        return this.inventoryService.ListOfInventoriesInStock(listOfSkuCodes);
    }
}
