package com.ruslank.springboot_microservices_order_app.dto;

import com.ruslank.springboot_microservices_order_app.entities.ItemsPerOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderRequest {

    private List<ItemsPerOrderDto> itemsPerOrderDtoList;
}
