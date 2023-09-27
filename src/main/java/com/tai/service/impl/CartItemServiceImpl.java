package com.tai.service.impl;

import com.tai.exception.CartItemException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.CartItem;
import com.tai.model.Product;
import com.tai.model.User;
import com.tai.repository.CartItemRepository;
import com.tai.repository.CartRepository;
import com.tai.service.CartItemService;
import com.tai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private CartItemRepository cartItemRepository;
    private UserService userService;
    private CartRepository cartRepository;

    @Override
    public CartItem createCartItem(CartItem cartItem) {
        cartItem.setQuantity(1);
        cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
        cartItem.setDiscountPrice(cartItem.getProduct().getDiscountPrice() * cartItem.getQuantity());
        return cartItemRepository.save(cartItem);
    }

    @Override
    public CartItem updateCartItem(Long userId, Long id, CartItem cartItem) throws UserException, CartItemException {
        CartItem item = findCartItemById(id);
        User user = userService.findUserById(userId);
        if(user.getId().equals(userId)){
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(item.getQuantity() * item.getProduct().getPrice());
            item.setDiscountPrice(item.getProduct().getDiscountPrice() * item.getQuantity());
            item.setSize(cartItem.getSize());
            //cartItemRepository.save(item);

        }
        return cartItemRepository.save(item);
    }

    @Override
    public CartItem isCartItemExist(Cart cart, Product product, String size, Long userId) {
        return cartItemRepository.isCartItemExist(cart, product, size, userId);
    }

    @Override
    public void removeCartItem(Long userId, Long cartItemId) throws CartItemException, UserException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if(cartItem.isPresent()){
            User user = userService.findUserById(cartItem.get().getUserId());
            User reqUser = userService.findUserById(userId);
            if (user.getId().equals(reqUser.getId())) {
                cartItemRepository.delete(cartItem.get());
            }
            else {
                throw new CartItemException("Delete Failed");
            }

        }
        //cartItem.ifPresent(item -> cartItemRepository.delete(item));
    }

    @Override
    public CartItem findCartItemById(Long cartItemId) throws CartItemException {
        Optional<CartItem> cartItem = cartItemRepository.findById(cartItemId);
        if (cartItem.isPresent()){
            return cartItem.get();
        }
        throw new CartItemException("Not found");
    }
}
