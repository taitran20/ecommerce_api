package com.tai.service;

import com.tai.model.OrderItem;
import com.tai.repository.OrderItemRepository;

public interface OrderItemService {
    OrderItem createOrderItem(OrderItem item);
}
