package com.tai.service.impl;

import com.tai.exception.OrderException;
import com.tai.model.*;
import com.tai.repository.*;
import com.tai.service.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private OrderItemService orderItemService;
    private OrderItemRepository orderItemRepository;
    private CartService cartService;
    @Override
    public Order crateOrder(User user, Address address) {
        address.setUser(user);
        Address address_ship = addressRepository.save(address);
        user.getAddressList().add(address_ship);
        userRepository.save(user);

        Cart cart = cartService.findUserCart(user.getId());
        List<OrderItem> orderItems = new ArrayList<>();
        for (CartItem cartItem:
             cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPrice(cartItem.getPrice());
            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSize(cartItem.getSize());
            orderItem.setDiscountPrice(cartItem.getDiscountPrice());
            orderItem.setUserId(cartItem.getUserId());

            OrderItem createdOrderItem = orderItemRepository.save(orderItem);
            orderItems.add(createdOrderItem);

        }
        Order order = new Order();
        order.setUser(user);
        order.setOrderItems(orderItems);
        order.setOrderDate(LocalDateTime.now());
        order.setAddress(address_ship);
        order.setStatus("PENDING");
        order.getPaymentDetails().setStatus("PENDING");
        order.setCreatedAt(LocalDateTime.now());
        order.setDiscounte(cart.getDiscounte());
        order.setTotal_item(cart.getTotalItem());
        order.setTotal_Price(cart.getTotalPrice());
        order.setTotal_discountPrice(cart.getTotalDiscountPrice());
        Order createdOrder = orderRepository.save(order);
        for (OrderItem orderItem:
             orderItems) {
            orderItem.setOrder(createdOrder);
            orderItemRepository.save(orderItem);
        }
        return createdOrder;
    }

    @Override
    public Order findById(Long id) throws OrderException {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    @Override
    public List<Order> userOrdersHistory(Long user_id) {
        return orderRepository.getUsersOrder(user_id);
    }

    @Override
    public Order placedOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        order.setStatus("PLACED");
        order.getPaymentDetails().setStatus("COMPLETED");
        return orderRepository.save(order);
    }

    @Override
    public Order confirmedOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        order.setStatus("CONFIRMED");
        return orderRepository.save(order);
    }

    @Override
    public Order shippedOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        order.setStatus("SHIPPED");
        return orderRepository.save(order);
    }

    @Override
    public Order deliveredOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        order.setStatus("DELIVERED");
        return orderRepository.save(order);
    }

    @Override
    public Order canceledOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        order.setStatus("CANCELED");
        return orderRepository.save(order);
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public void deleteOrder(Long order_id) throws OrderException {
        Order order = findById(order_id);
        orderRepository.delete(order);
    }
}
