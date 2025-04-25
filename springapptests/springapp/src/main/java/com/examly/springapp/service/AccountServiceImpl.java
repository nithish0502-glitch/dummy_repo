package com.examly.springapp.service;

import com.examly.springapp.exception.LaptopUnderMaintenanceException;
import com.examly.springapp.model.Account;
import com.examly.springapp.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository userRepository;

    @Override
    public Account createUser(Account user) {
        return userRepository.save(user);
    }

    @Override
    public Account getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new LaptopUnderMaintenanceException("User with ID " + id + " not found"));
    }

    @Override
    public List<Account> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUser(Long id) {
        Optional<Account> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Account updateUser(Long id, Account userDetails) {
            Account user = userRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User with ID " + id + " not found"));
    
            user.setName(userDetails.getName());
            user.setDepartment(userDetails.getDepartment());
    
            return userRepository.save(user);
        }
    }

