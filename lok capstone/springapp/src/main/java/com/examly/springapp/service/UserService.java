package com.examly.springapp.service;

import com.examly.springapp.model.AuthUser;
import com.examly.springapp.model.User;

public interface UserService {

    public User registerUser(User user);

    public AuthUser login(String email, String password);

}