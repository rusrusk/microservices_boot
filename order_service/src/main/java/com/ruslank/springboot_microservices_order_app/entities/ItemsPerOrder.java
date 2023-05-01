package com.ruslank.springboot_microservices_order_app.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@Table(name = "items_per_order")
public class ItemsPerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //each item will be auto generated
    private Long id;

    private BigDecimal price;

    private String skuCode;

    private Integer quantity;


}
