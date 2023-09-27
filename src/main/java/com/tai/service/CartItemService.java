package com.tai.service;

import com.tai.exception.CartItemException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.CartItem;
import com.tai.model.Product;

public interface CartItemService {
    CartItem createCartItem(CartItem cartItem);
    CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws CartItemException, UserException;
    CartItem isCartItemExist(Cart cart, Product product, String size, Long userId);
    void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException;
    CartItem findCartItemById(Long cartItemId) throws CartItemException;
}
