package com.tai.service.impl;

import com.tai.model.OrderItem;
import com.tai.repository.OrderItemRepository;
import com.tai.service.OrderItemService;
import com.tai.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemRepository orderItemRepository;
    @Override
    public OrderItem createOrderItem(OrderItem item) {
        return orderItemRepository.save(item);
    }
}
