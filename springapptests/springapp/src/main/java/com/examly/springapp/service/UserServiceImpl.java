package com.examly.springapp.service;

import com.examly.springapp.exception.ResourceNotFoundException;
import com.examly.springapp.model.Laptop;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.LaptopRepository;
import com.examly.springapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LaptopRepository laptopRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User with ID " + id + " not found."));
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        User existingUser = getUserById(id);
        existingUser.setName(updatedUser.getName());
        existingUser.setDepartment(updatedUser.getDepartment());
        return userRepository.save(existingUser);
    }

    @Override
    public void deleteUser(Long id) {
        User existingUser = getUserById(id);
        userRepository.delete(existingUser);
    }

    @Override
    public String assignLaptopToUser(Long userId, Long laptopId) {
        User user = getUserById(userId);
        Laptop laptop = laptopRepository.findById(laptopId)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop with ID " + laptopId + " not found."));

        if ("Under Maintenance".equalsIgnoreCase(laptop.getStatus())) {
            throw new IllegalStateException("Laptop is under maintenance and cannot be assigned.");
        }

        if (laptop.getAssignedTo() != null) {
            throw new IllegalStateException("Laptop is already assigned to a user.");
        }

        if (user.getLaptop() != null) {
            throw new IllegalStateException("User already has a laptop assigned.");
        }

        // Establish bidirectional relationship
        laptop.setAssignedTo(user);
        user.setLaptop(laptop);

        laptopRepository.save(laptop);
        userRepository.save(user);

        return "Laptop assigned successfully";
    }
}
