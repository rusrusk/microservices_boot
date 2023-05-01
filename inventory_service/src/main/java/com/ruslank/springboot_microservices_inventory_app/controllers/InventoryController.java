package com.ruslank.springboot_microservices_inventory_app.controllers;

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

    @GetMapping("inventories/{sku-code}")
    @ResponseStatus(HttpStatus.OK)
    public boolean isInventoryInStock(@PathVariable("sku-code") String skuCode) {
        return this.inventoryService.isInventoryInStock(skuCode);
    }
}
