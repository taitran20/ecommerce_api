package com.tai.service;

import com.tai.exception.OrderException;
import com.tai.model.Address;
import com.tai.model.Order;
import com.tai.model.User;

import java.util.List;

public interface OrderService {
    Order crateOrder(User user, Address address);
    Order findById(Long id) throws OrderException;
    List<Order> userOrdersHistory(Long user_id);
    Order placedOrder(Long order_id) throws OrderException;

    Order confirmedOrder(Long order_id) throws OrderException;

    Order shippedOrder(Long order_id) throws OrderException;
    Order deliveredOrder(Long order_id) throws OrderException;
    Order canceledOrder(Long order_id) throws OrderException;
    List<Order> getAllOrders();
    void deleteOrder(Long order_id) throws OrderException;
}
