package com.ruslank.springboot_microservices_app.services;

import com.ruslank.springboot_microservices_app.dto.ProductRequest;
import com.ruslank.springboot_microservices_app.dto.ProductResponse;
import com.ruslank.springboot_microservices_app.entities.Product;
import com.ruslank.springboot_microservices_app.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;


    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .desc(productRequest.getDesc())
                .price(productRequest.getPrice())
                .build();

        this.productRepository.save(product);
        log.info("Product : {} is saved", product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> productList = this.productRepository.findAll();

        List<ProductResponse> productResponse = productList.stream().map(p -> mapProductResponse(p)).collect(Collectors.toList());
        return productResponse;
    }

    private ProductResponse mapProductResponse(Product p) {
        return ProductResponse.builder()
                .id(p.getId())
                .name(p.getName())
                .desc(p.getDesc())
                .price(p.getPrice())
                .build();
    }
}
