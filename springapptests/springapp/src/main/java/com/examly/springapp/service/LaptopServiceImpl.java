package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.LaptopRepository;
import com.examly.springapp.repository.UserRepository;
import com.examly.springapp.exception.LaptopUnderMaintenanceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopServiceImpl implements LaptopService {
@Autowired
    private LaptopRepository laptopRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Laptop createLaptopWithUser(Laptop laptop, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
                if ("Under Maintenance".equalsIgnoreCase(laptop.getStatus())) {
                    throw new LaptopUnderMaintenanceException("Laptop is under maintenance and cannot be assigned.");
                }
        laptop.setUser(user);
        return laptopRepository.save(laptop);
    }
 
    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public Optional<Laptop> getLaptopById(Long id) {
        return laptopRepository.findById(id);
    }

    @Override
    public Laptop updateLaptop(Long id, Laptop updatedLaptop) {
        return laptopRepository.findById(id).map(existing -> {
            existing.setBrand(updatedLaptop.getBrand());
            existing.setModel(updatedLaptop.getModel());
            existing.setSerialNumber(updatedLaptop.getSerialNumber());
            existing.setStatus(updatedLaptop.getStatus());
            return laptopRepository.save(existing);
        }).orElse(null);
    }

    @Override
    public boolean deleteLaptop(Long id) {
        if (laptopRepository.existsById(id)) {
            laptopRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public Laptop getLaptopByDepartment(String department) {
        return laptopRepository.findByUserDepartment(department);
    }
}


