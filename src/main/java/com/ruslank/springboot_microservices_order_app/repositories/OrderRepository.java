package com.ruslank.springboot_microservices_order_app.repositories;

import com.ruslank.springboot_microservices_order_app.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
