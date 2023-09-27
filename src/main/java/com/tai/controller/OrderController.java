package com.tai.controller;

import com.tai.exception.OrderException;
import com.tai.exception.UserException;
import com.tai.model.Address;
import com.tai.model.Order;
import com.tai.model.User;
import com.tai.service.OrderService;
import com.tai.service.UserService;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.BorderUIResource;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {
    private OrderService orderService;
    private UserService userService;

    @PostMapping("")
    public Object createOrder(@RequestBody Address address, @RequestHeader("Authorization")String jwt) throws UserException{
        try {
            User user = userService.getUserProfileByJwt(jwt);
            Order order = orderService.crateOrder(user,address);
            return new ResponseEntity<>(order, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>("Error", HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/user")
    public ResponseEntity<List<Order>> userOrderHistory( @RequestHeader("Authorization")String jwt) throws UserException{
        User user = userService.getUserProfileByJwt(jwt);
        if(user == null){
            return new  ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Order> orders = orderService.userOrdersHistory(user.getId());
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<Order> findOrderById(@PathVariable("Id") Long orderId, @RequestHeader("Authorization")String jwt) throws UserException, OrderException {
        User user = userService.getUserProfileByJwt(jwt);
        Order order = orderService.findById(orderId);
        if(order == null || user == null){
            return new  ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
