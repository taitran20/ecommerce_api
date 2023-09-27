package com.tai.service;

import com.tai.exception.UserException;
import com.tai.model.User;
import org.apache.catalina.util.Introspection;

public interface UserService {
    User findUserById(Long id) throws UserException;
    User getUserProfileByJwt(String jwt) throws UserException;
}
