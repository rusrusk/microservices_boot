package com.ruslank.springboot_microservices_order_app.controller;

import com.ruslank.springboot_microservices_order_app.dto.OrderRequest;
import com.ruslank.springboot_microservices_order_app.entities.Order;
import com.ruslank.springboot_microservices_order_app.services.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
    @CircuitBreaker(name="inventory", fallbackMethod = "fallBackMethod")
    public String createOrder(@RequestBody OrderRequest order) {
        this.orderService.createOrder(order);
        return "Order was created successfully";
    }

    public String fallBackMethod(OrderRequest orderRequest) {
        return "The operation cannot be processed! Sorry!";
    }
}
