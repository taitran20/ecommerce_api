package com.tai.controller;

import com.tai.exception.CartItemException;
import com.tai.exception.ProductException;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.User;
import com.tai.request.AddItemRequest;
import com.tai.response.ApiResponse;
import com.tai.service.CartService;
import com.tai.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/api/cart")
@Tag(name = "Cart Management", description = "Find user cart and add item to cart")
public class CartController {
    private CartService cartService;
    private UserService userService;

    @GetMapping("")
    @Operation(description = "Find cart by user")
    public ResponseEntity<Cart> findUserCart(@RequestHeader("Authorization")String jwt) throws UserException{
        User user = userService.getUserProfileByJwt(jwt);
        //System.out.println(user.getEmail());
        Cart cart = cartService.findUserCart(user.getId());
        System.out.println(cart.getCartItems().size());
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PutMapping("/add")
    @Operation(description = "Add item to cart")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestHeader("Authorization")String jwt, @RequestBody AddItemRequest request) throws UserException, ProductException, CartItemException {
        User user = userService.getUserProfileByJwt(jwt);
        cartService.addCartItem(user.getId(), request);
        ApiResponse apiResponse = new ApiResponse("Item added to Cart", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
