package com.tai.service;

import com.tai.exception.UserException;
import com.tai.model.User;
import com.tai.request.UserRequest;
import org.springframework.security.core.Authentication;

public interface AuthService {
    User registerUser(UserRequest userRequest) throws UserException;
    Authentication authenticate(String username, String password);
}
