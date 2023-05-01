package com.ruslank.springboot_microservices_order_app.controller;

import com.ruslank.springboot_microservices_order_app.dto.OrderRequest;
import com.ruslank.springboot_microservices_order_app.entities.Order;
import com.ruslank.springboot_microservices_order_app.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createOrder(@RequestBody OrderRequest order) {
        this.orderService.createOrder(order);
        return "Order was created successfully";
    }
}
