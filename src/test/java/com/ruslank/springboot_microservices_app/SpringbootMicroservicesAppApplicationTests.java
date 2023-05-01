package com.ruslank.springboot_microservices_app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruslank.springboot_microservices_app.dto.ProductRequest;
import com.ruslank.springboot_microservices_app.dto.ProductResponse;
import com.ruslank.springboot_microservices_app.entities.Product;
import com.ruslank.springboot_microservices_app.repositories.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.com.fasterxml.jackson.core.JsonProcessingException;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class SpringbootMicroservicesAppApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private ProductRepository productRepository;

	@Container
	static MongoDBContainer mongoDBContainer = new MongoDBContainer();

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry) {
		dynamicPropertyRegistry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
	}

	@Test
	@DisplayName("""
   			when /product endpoint is called, new object should be created in db
   			and HTTP status code 201 should be returned 
			""")
	void ProductController_CreateProduct() throws Exception {
		ProductRequest productRequest = getProductRequest();
		String productObjectToString =  objectMapper.writeValueAsString(productRequest);
		mockMvc
				.perform(MockMvcRequestBuilders.post("/product")
						.contentType(MediaType.APPLICATION_JSON)
						.content(productObjectToString))
				.andExpect(status().isCreated());
		Assertions.assertThat(this.productRepository.findAll().size()).isEqualTo(1);
//		Assertions.assertThat(this.productRepository.).isEqualTo("Samsung");
	}

	private  ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Samsung")
				.desc("phone")
				.price(BigDecimal.valueOf(900))
				.build();
	}

//	@Test
//	@DisplayName("""
//			when /product/products endpoint is called, all the products should be fetched
//			from db and returned as a list and HTTP status code 200 should be returned
//			""")
//	void ProductController_GetAllProducts() throws Exception {
////		Product product = Product.builder()
////				.id("1")
////				.name("iMac")
////				.desc("phone")
////				.price(BigDecimal.valueOf(800))
////				.build();
//		ProductResponse productResponse = getProductResponse();
//		String productObjectToString = objectMapper.writeValueAsString(productResponse);
////		String productObjectToString =  objectMapper.writeValueAsString(productResponse);
//		mockMvc
//				.perform(MockMvcRequestBuilders.get("/product/products")
//						.contentType(MediaType.APPLICATION_JSON)
//						.content(productObjectToString))
//						.andExpect(status().isOk());
////		Assertions.assertThat(this.productRepository.findAll().get(1).getName()).isEqualTo("iMac");
//		Assertions.assertThat(this.productRepository.findAll().size()).isEqualTo(1);
//	}
//
//	private ProductResponse getProductResponse() {
//		ProductResponse productResponse = new ProductResponse();
//		return ProductResponse.builder()
//				.id("12")
//				.name("iMac")
//				.desc("phone")
//				.price(BigDecimal.valueOf(300))
//				.build();
//	}

}
