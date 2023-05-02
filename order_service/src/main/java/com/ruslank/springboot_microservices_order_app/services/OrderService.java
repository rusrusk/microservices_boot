package com.ruslank.springboot_microservices_order_app.services;

import com.ruslank.springboot_microservices_order_app.config.WebClientConfig;
import com.ruslank.springboot_microservices_order_app.dto.InventoryResponse;
import com.ruslank.springboot_microservices_order_app.dto.ItemsPerOrderDto;
import com.ruslank.springboot_microservices_order_app.dto.OrderRequest;
import com.ruslank.springboot_microservices_order_app.entities.ItemsPerOrder;
import com.ruslank.springboot_microservices_order_app.entities.Order;
import com.ruslank.springboot_microservices_order_app.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    private WebClient.Builder LoadBalancedWebClientBuilder;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        //map ItemsPerOrderDto to ItemsPerOrder
        List<ItemsPerOrder> itemsPerOrderList =  orderRequest.getItemsPerOrderDtoList()
                .stream()
                .map(items -> mapToDto(items)).collect(Collectors.toList());

        //setting dtolist to order list
        order.setItemsPerOrderList(itemsPerOrderList);

        List<String> listOfSkuCodes = order.getItemsPerOrderList()
                .stream()
                .map(item -> (item.getSkuCode()))
                .collect(Collectors.toList());

        //TODO == if the inventory is in stock, create an order
        InventoryResponse[] resultAsInventoryResponseArray = LoadBalancedWebClientBuilder.build().get()
                .uri("http://inventory-service/inventory/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", listOfSkuCodes)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponse[].class)
                .block();

        boolean existInStock = Arrays
                .stream(resultAsInventoryResponseArray)
                .allMatch(InventoryResponse::isInventoryInStock);

        if (existInStock) {
            //save in db
            this.orderRepository.save(order);
        }
        else {
            //otherwise throw an exception
            throw new IllegalArgumentException("Unfortunately, given product is currently not available in stock!");
        }
    }

    private ItemsPerOrder mapToDto(ItemsPerOrderDto itemsPerOrderDto) {
        ItemsPerOrder itemsPerOrder = new ItemsPerOrder();
        itemsPerOrder.setPrice(itemsPerOrderDto.getPrice());
        itemsPerOrder.setSkuCode(itemsPerOrderDto.getSkuCode());
        itemsPerOrder.setQuantity(itemsPerOrderDto.getQuantity());
        return itemsPerOrder;
    }
}
