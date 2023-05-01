package com.ruslank.springboot_microservices_inventory_app.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skuCode;

    private Integer quantity;
}
