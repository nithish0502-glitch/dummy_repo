package com.examly.springapp.service;

import com.examly.springapp.exception.LaptopUnderMaintenanceException;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new LaptopUnderMaintenanceException("User with ID " + id + " not found"));
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public User updateUser(Long id, User userDetails) {
            User user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    
            user.setName(userDetails.getName());
            user.setDepartment(userDetails.getDepartment());
    
            return userRepository.save(user);
        }
    }

