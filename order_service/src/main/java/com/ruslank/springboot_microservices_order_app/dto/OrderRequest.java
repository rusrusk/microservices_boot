package com.ruslank.springboot_microservices_order_app.dto;

import com.ruslank.springboot_microservices_order_app.entities.ItemsPerOrder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Data
@RequiredArgsConstructor
public class OrderRequest {
    //request will be returned when post request is received to the server
    private List<ItemsPerOrderDto> itemsPerOrderDtoList;
}
