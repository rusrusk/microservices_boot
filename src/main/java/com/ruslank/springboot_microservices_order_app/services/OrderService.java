package com.ruslank.springboot_microservices_order_app.services;

import com.ruslank.springboot_microservices_order_app.dto.ItemsPerOrderDto;
import com.ruslank.springboot_microservices_order_app.dto.OrderRequest;
import com.ruslank.springboot_microservices_order_app.entities.ItemsPerOrder;
import com.ruslank.springboot_microservices_order_app.entities.Order;
import com.ruslank.springboot_microservices_order_app.repositories.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());
        List<ItemsPerOrder> itemsPerOrderDtoList =  orderRequest.getItemsPerOrderDtoList()
                .stream()
                .map(items -> mapToDto(items)).collect(Collectors.toList());
        order.setItemsPerOrderList(itemsPerOrderDtoList);
        this.orderRepository.save(order);
    }

    private ItemsPerOrder mapToDto(ItemsPerOrderDto itemsPerOrderDto) {
        ItemsPerOrder itemsPerOrder = new ItemsPerOrder();
        itemsPerOrder.setPrice(itemsPerOrderDto.getPrice());
        itemsPerOrder.setSkuCode(itemsPerOrderDto.getSkuCode());
        itemsPerOrder.setQuantity(itemsPerOrderDto.getQuantity());
        return itemsPerOrder;
    }
}
