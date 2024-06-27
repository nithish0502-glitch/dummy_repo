package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;
    public User login(User user){
       return userRepo.save(user);
    }
    @Override
    public User register(User user) {
        return userRepo.save(user);
    }
}
