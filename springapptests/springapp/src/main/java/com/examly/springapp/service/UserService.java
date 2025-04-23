package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    boolean deleteUser(Long id);
    User updateUser(Long id, User userDetails);
}

