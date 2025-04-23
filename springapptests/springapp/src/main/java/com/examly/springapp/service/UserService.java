package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User updateUser(Long id, User user);
    User getUserById(Long id);
    List<User> getAllUsers();
    void deleteUser(Long id);
    Laptop assignLaptopToUser(Long userId, Long laptopId);

}

