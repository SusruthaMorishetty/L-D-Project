package com.ust.userservice.service;

import com.ust.userservice.dto.UserDtoResponse;
import com.ust.userservice.model.User;
import com.ust.userservice.repository.UserRepository;
import org.aspectj.apache.bcel.classfile.Module;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;


    @Override
    public UserDtoResponse saveUser(User user) {
        if(user==null){
            throw new RuntimeException("user cannot be null");
        }
        if(userRepository.existsById(user.getId())){
            throw  new RuntimeException("user with "+user.getId()+" already present");
        }
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        var savedUser = userRepository.save(user);
        return new UserDtoResponse(savedUser.getName(), savedUser.getRole(), savedUser.getEmail());

    }

    @Override
    public User updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee with Id "+id+" not found"));


        existingUser.setName(user.getName());
        existingUser.setEmail(user.getEmail());
        existingUser.setRole(user.getRole());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {

        if(!userRepository.existsById(id)){
            throw  new RuntimeException("User with "+id+" not exist.");
        }
        userRepository.deleteById(id);
    }

    @Override
    public UserDtoResponse getUserById(Long id) {
        var savedUser =  userRepository.findById(id)
                .orElseThrow(()->new RuntimeException("user with "+id+" not found"));

        return new UserDtoResponse(savedUser.getName(), savedUser.getRole(), savedUser.getEmail());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }


}
