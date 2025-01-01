package com.ust.apigateway.service;


import com.ust.apigateway.dto.SignupReq;
import com.ust.apigateway.dto.UserDtoResponse;
import com.ust.apigateway.model.User;
import com.ust.apigateway.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCrudService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserDtoResponse saveUser(SignupReq signupReq){
        if (userRepository.findByEmail(signupReq.getEmail()).isPresent()) {
            throw new RuntimeException("User with email already exists");
        }
        User user = new User();
        user.setName(signupReq.getUsername());
        user.setEmail(signupReq.getEmail());
        user.setRole(signupReq.getRole());
        String encodedPassword = passwordEncoder.encode(signupReq.getPassword());
       user.setPassword(encodedPassword);
        var savedUser = userRepository.save(user);
        return new UserDtoResponse(savedUser.getName(), savedUser.getRole());
    }

    public  Long getUserIdByUserName(String username){
        Optional<User> user = userRepository.findByName(username);
        return user.get().getId();
    }

}
