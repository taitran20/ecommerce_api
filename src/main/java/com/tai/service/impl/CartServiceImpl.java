package com.tai.service.impl;

import com.tai.exception.CartItemException;
import com.tai.exception.ProductException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.CartItem;
import com.tai.model.Product;
import com.tai.model.User;
import com.tai.repository.CartRepository;
import com.tai.request.AddItemRequest;
import com.tai.service.CartItemService;
import com.tai.service.CartService;
import com.tai.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;
    private CartItemService cartItemService;
    private ProductService productService;
    @Override
    public Cart createCart(User user) {
        Cart cart = new Cart();
        cart.setUser(user);
        return cartRepository.save(cart);
    }

    @Override
    public String addCartItem(Long userId, AddItemRequest request) throws ProductException, CartItemException, UserException {
        Cart cart = cartRepository.findByUserId(userId);
        Product product = productService.findProductById(request.getProductId());
        CartItem isPresent = cartItemService.isCartItemExist(cart, product, request.getSize(), userId);
        //System.out.println(cart.getCartItems().size());
        /*CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setCart(cart);
        cartItem.setUserId(userId);
        double price = request.getQuantity() * product.getDiscountPrice();
        cartItem.setPrice(price);
        cartItem.setSize(request.getSize());
        if(isPresent == null){
            cartItem.setQuantity(request.getQuantity());
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        else if (Objects.equals(isPresent.getProduct().getId(), request.getProductId())){
            cartItem.setQuantity(cartItem.getQuantity() + request.getQuantity());
            CartItem createdCartItem = cartItemService.updateCartItem(userId,cart.getId(),cartItem);
            cart.getCartItems().add(createdCartItem);
        }
        CartItem createdCartItem = cartItemService.createCartItem(cartItem);
        cart.getCartItems().add(createdCartItem);*/
        if (isPresent == null){
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setCart(cart);
            cartItem.setQuantity(request.getQuantity());
            cartItem.setUserId(userId);

            double price = request.getQuantity() * product.getDiscountPrice();
            cartItem.setPrice(price);
            cartItem.setSize(request.getSize());
            CartItem createdCartItem = cartItemService.createCartItem(cartItem);
            cart.getCartItems().add(createdCartItem);
            //System.out.println(cart.getCartItems().size());
            //Cart cart1 = cartRepository.save(cart);
        }

        return "Item add to Cart";
    }

    @Override
    public Cart findUserCart(Long userId) {
        Cart cart = cartRepository.findByUserId(userId);
        double totalPrice = 0;
        double totalDiscountPrice = 0;
        int totalItem = 0;


        for (CartItem item:
             cart.getCartItems()) {
            totalPrice = totalPrice + item.getPrice();
            totalDiscountPrice = totalDiscountPrice + item.getDiscountPrice();
            totalItem = totalItem + item.getQuantity();
        }

        cart.setTotalItem(totalItem);
        cart.setTotalDiscountPrice(totalDiscountPrice);
        cart.setTotalPrice(totalPrice);
        cart.setDiscounte(totalPrice - totalDiscountPrice);

        return cartRepository.save(cart);
    }
}
