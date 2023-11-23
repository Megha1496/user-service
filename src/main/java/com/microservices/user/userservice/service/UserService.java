package com.microservices.user.userservice.service;

import com.microservices.user.userservice.model.User;
import com.microservices.user.userservice.payload.request.UserRequest;

import java.util.List;

public interface UserService {
    User createUser(UserRequest userRequest) ;

    List<User> getAllUsers();

    User findUserById(int id);

    User updateUser(int id, UserRequest userRequest);

    User deleteUser(int id);
}
