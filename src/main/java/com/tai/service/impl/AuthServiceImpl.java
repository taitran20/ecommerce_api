package com.tai.service.impl;

import com.tai.exception.UserException;
import com.tai.model.Cart;
import com.tai.model.User;
import com.tai.repository.UserRepository;
import com.tai.request.UserRequest;
import com.tai.service.AuthService;
import com.tai.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private CustomerServiceImpl customerService;
    //private CartService cartService;
    @Override
    public User registerUser(UserRequest userRequest) throws UserException{
        User user = userRepository.findByEmail(userRequest.getEmail());
        if(user != null){
            throw new UserException("Email is Already Used!");
        }
        User saveuser = new User();
        saveuser.setEmail(userRequest.getEmail());
        saveuser.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        saveuser.setFName(userRequest.getF_name());
        saveuser.setLName(userRequest.getL_name());
        saveuser.setCreatedAt(LocalDateTime.now());
        /*//Cart cart = cartService.createCart(saveuser);
        if(cart == null){
            throw new UserException("Error");
        }*/
        return userRepository.save(saveuser);
    }
    @Override
    public Authentication authenticate(String username, String password){
        UserDetails userDetails = customerService.loadUserByUsername(username);
        if (userDetails == null){
            throw new BadCredentialsException("Invalid Username");
        }
        if(!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }
}
