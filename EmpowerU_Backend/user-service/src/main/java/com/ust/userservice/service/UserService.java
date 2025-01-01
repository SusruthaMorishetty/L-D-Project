package com.ust.userservice.service;

import com.ust.userservice.dto.UserDtoResponse;
import com.ust.userservice.model.User;

import java.util.List;

public interface UserService {

    UserDtoResponse saveUser(User user);

    User updateUser(Long id, User user);

    void deleteUser(Long id);

    UserDtoResponse getUserById(Long id);

    List<User> getAllUser();


}
