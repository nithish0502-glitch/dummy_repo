package com.examly.springapp.service;

import com.examly.springapp.model.User;
import com.examly.springapp.model.Laptop;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.repository.LaptopRepository;
import com.examly.springapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final LaptopRepository laptopRepository;

    public UserServiceImpl(UserRepository userRepository, LaptopRepository laptopRepository) {
        this.userRepository = userRepository;
        this.laptopRepository = laptopRepository;
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));
    }

    @Override
    public User assignLaptopToUser(Long userId, Long laptopId) {
        User user = user
::contentReference[oaicite:3]{index=3}
 

