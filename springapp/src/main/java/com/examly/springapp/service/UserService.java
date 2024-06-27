package com.examly.springapp.service;

import com.examly.springapp.model.User;

public interface UserService {
    public User login(User user);
    public User register(User user);
}
