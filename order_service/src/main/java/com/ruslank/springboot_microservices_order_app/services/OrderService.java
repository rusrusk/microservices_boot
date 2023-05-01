package com.ruslank.springboot_microservices_order_app.services;

import com.ruslank.springboot_microservices_order_app.dto.ItemsPerOrderDto;
import com.ruslank.springboot_microservices_order_app.dto.OrderRequest;
import com.ruslank.springboot_microservices_order_app.entities.ItemsPerOrder;
import com.ruslank.springboot_microservices_order_app.entities.Order;
import com.ruslank.springboot_microservices_order_app.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        //map ItemsPerOrderDto to ItemsPerOrder
        List<ItemsPerOrder> itemsPerOrderDtoList =  orderRequest.getItemsPerOrderDtoList()
                .stream()
                .map(items -> mapToDto(items)).collect(Collectors.toList());

        //setting dtolist to order list
        order.setItemsPerOrderList(itemsPerOrderDtoList);

        List<String> listOfSkuCodes = order.getItemsPerOrderList()
                .stream()
                .map(item -> (item.getSkuCode()))
                .collect(Collectors.toList());

        //TODO == if the inventory is in stock, create an order
        Boolean resultFromInventory = webClient.get()
                .uri("http://localhost:9091/inventory/inventories",
                        uriBuilder -> uriBuilder.queryParam("skuCode", listOfSkuCodes)
                                .build())
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();
        if (resultFromInventory) {
            //save in db
            this.orderRepository.save(order);
        }
        else {
            throw new IllegalArgumentException();
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
