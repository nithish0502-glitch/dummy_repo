package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    private User login(User user){

    }
}
