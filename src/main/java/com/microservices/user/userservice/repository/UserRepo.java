package com.microservices.user.userservice.repository;

import com.microservices.user.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Integer>{
    User findByName(String username);


}
