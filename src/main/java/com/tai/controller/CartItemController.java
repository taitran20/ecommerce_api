package com.tai.controller;

import com.tai.exception.CartItemException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.CartItem;
import com.tai.model.User;
import com.tai.service.CartItemService;
import com.tai.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart_item")
public class CartItemController {
    private CartItemService cartItemService;
    private UserService userService;

    @GetMapping("/{cartItemId}")
    public Object removeCartItem(@RequestHeader("Authorization")String jwt, @PathVariable Long cartItemId) throws UserException, CartItemException {
        User user = userService.getUserProfileByJwt(jwt);
            try {
                cartItemService.removeCartItem(user.getId(), cartItemId);
                return new ResponseEntity<>("Delete Successfully",HttpStatus.OK);
            }catch (Exception e){
                throw new CartItemException("Error");
            }
    }

    @PutMapping("/update/{cartItemId}")
    public Object updateCartItem(@RequestHeader("Authorization")String jwt, @PathVariable Long cartItemId, @RequestBody CartItem cartItem) throws UserException, CartItemException {
        User user = userService.getUserProfileByJwt(jwt);
        try {
            CartItem updateCartItem = cartItemService.updateCartItem(user.getId(), cartItemId, cartItem);
            return new ResponseEntity<>(updateCartItem,HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error",HttpStatus.BAD_REQUEST);
        }

    }
}
