package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/login")
    public User Login(@RequestBody User user){
        return userService.login(user);
    }

    @PostMapping("/api/register")
    public User register(@RequestBody User user){
        return userService.register(user);
    }
}
