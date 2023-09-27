package com.tai.service.impl;

import com.tai.config.JwtProvider;
import com.tai.exception.UserException;
import com.tai.model.User;
import com.tai.repository.UserRepository;
import com.tai.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private JwtProvider jwtProvider;
    @Override
    public User findUserById(Long id) throws UserException {
        Optional<User> user = userRepository.findById(id);
        return user.orElse(null);
    }

    @Override
    public User getUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new UserException("Not found!");
        }
        return user;
    }
}
