package com.ruslank.springboot_microservices_inventory_app.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {

    private String skuCode;
    private boolean isInventoryInStock;
}
