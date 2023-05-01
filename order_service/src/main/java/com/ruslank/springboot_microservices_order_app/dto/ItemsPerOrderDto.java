package com.ruslank.springboot_microservices_order_app.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;

@Data
@RequiredArgsConstructor
public class ItemsPerOrderDto {

    private Long id;

    private BigDecimal price;

    private String skuCode;

    private Integer quantity;
}
