package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;

import java.util.List;
import java.util.Optional;

public interface LaptopService {
    Laptop createLaptopWithUser(Laptop laptop, Long userId);
    List<Laptop> getAllLaptops();
    Optional<Laptop> getLaptopById(Long id);
    Laptop updateLaptop(Long id, Laptop laptop);
    boolean deleteLaptop(Long id);
}

