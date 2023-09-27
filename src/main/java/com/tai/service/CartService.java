package com.tai.service;

import com.tai.exception.CartItemException;
import com.tai.exception.ProductException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.User;
import com.tai.request.AddItemRequest;

public interface CartService {
    Cart createCart(User user);
    String addCartItem(Long userId, AddItemRequest request) throws ProductException, CartItemException, UserException;
    Cart findUserCart(Long userId);
}
