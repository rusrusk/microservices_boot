package com.ruslank.springboot_microservices_order_app.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class WebClientConfig {

    @LoadBalanced
    @Bean
    public WebClient.Builder LoadBalancedWebClientBuilder() {
        return WebClient.builder();
    }

//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder().build();
//    }
}
