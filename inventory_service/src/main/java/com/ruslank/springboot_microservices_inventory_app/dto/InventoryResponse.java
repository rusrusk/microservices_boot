package com.ruslank.springboot_microservices_inventory_app.dto;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class InventoryResponse {

    private String skuCode;
    private boolean isInventoryInStock;
}
