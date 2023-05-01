package com.ruslank.springboot_microservices_order_app.dto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class InventoryResponse {

    private String skuCode;
    private boolean isInventoryInStock;
}
