package com.tai.controller;

import com.tai.config.JwtProvider;
import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.User;
import com.tai.request.LoginRequest;
import com.tai.response.AuthResponse;
import com.tai.request.UserRequest;
import com.tai.service.AuthService;
import com.tai.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthService authService;
    private JwtProvider jwtProvider;
    private CartService cartService;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> createdUserHandler(@RequestBody UserRequest userRequest) throws UserException {
        User user = authService.registerUser(userRequest);
        Cart cart = cartService.createCart(user);
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Signup Success");
        return new ResponseEntity<>(authResponse, HttpStatus.CREATED);
    }
    @PostMapping("/signin")
    public ResponseEntity<AuthResponse> loginUserHandler(@RequestBody LoginRequest loginRequest){
        Authentication authentication = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(token,"Signin Success");
        return new ResponseEntity<>(authResponse, HttpStatus.ACCEPTED);
    }
}
