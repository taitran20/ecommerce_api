package com.tai.service.impl;

import com.tai.exception.OrderException;
import com.tai.model.Address;
import com.tai.model.Order;
import com.tai.model.User;
import com.tai.repository.CartRepository;
import com.tai.service.CartItemService;
import com.tai.service.OrderService;
import com.tai.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    @Override
    public Order crateOrder(User user, Address address) {
        return null;
    }

    @Override
    public Order findById(Long id) throws OrderException {
        return null;
    }

    @Override
    public List<Order> userOrdersHistory(Long user_id) {
        return null;
    }

    @Override
    public Order placedOrder(Long order_id) throws OrderException {
        return null;
    }

    @Override
    public Order comfirmedOrder(Long order_id) throws OrderException {
        return null;
    }

    @Override
    public Order shippedOrder(Long order_id) throws OrderException {
        return null;
    }

    @Override
    public Order deliveredOrder(Long order_id) throws OrderException {
        return null;
    }

    @Override
    public Order canceledOrder(Long order_id) throws OrderException {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public void deleteOrder(Long order_id) throws OrderException {

    }
}
