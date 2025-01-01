package com.ust.userservice.controller;


import com.ust.userservice.dto.UserDtoResponse;
import com.ust.userservice.model.User;
import com.ust.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDtoResponse saveUser(@RequestBody User user){
        return userService.saveUser(user);
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUser(){
        return userService.getAllUser();
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public User updateUser(@PathVariable Long id, @RequestBody User user){
        return userService.updateUser(id,user);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDtoResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }
}
