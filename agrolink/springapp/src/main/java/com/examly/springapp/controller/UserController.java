package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.AuthUser;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserService;
import com.examly.springapp.service.UserServiceImpl;

@RestController
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    // @PostMapping("/api/login")
    // public ResponseEntity<AuthUser> loginUser(@RequestParam String email, @RequestParam String password) throws UsernameNotFoundException{
    //     AuthUser u = userService.login(email, password);
    //     return new ResponseEntity<>(u, HttpStatus.OK);
    // }

    @PostMapping("/api/login")
    public ResponseEntity<AuthUser> loginUser(@RequestBody User user) throws UsernameNotFoundException{
        AuthUser u = userService.login(user.getEmail(), user.getPassword());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
 
    @PostMapping("/api/register")
    public ResponseEntity<User> register(@RequestBody User user) throws RuntimeException {
        User u = userService.registerUser(user);
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
