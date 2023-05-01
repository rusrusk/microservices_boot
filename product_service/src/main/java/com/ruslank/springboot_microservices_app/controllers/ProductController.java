package com.ruslank.springboot_microservices_app.controllers;

import com.ruslank.springboot_microservices_app.dto.ProductRequest;
import com.ruslank.springboot_microservices_app.dto.ProductResponse;
import com.ruslank.springboot_microservices_app.entities.Product;
import com.ruslank.springboot_microservices_app.services.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest productRequest) {
        this.productService.createProduct(productRequest);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return this.productService.getAllProducts();
    }
}
