package com.ruslank.springboot_microservices_app.repositories;

import com.ruslank.springboot_microservices_app.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
