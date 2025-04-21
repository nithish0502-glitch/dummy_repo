package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;

import java.util.List;

public interface LaptopService {
    Laptop createLaptop(Laptop laptop);
    Laptop updateLaptop(Long id, Laptop laptop);
    List<Laptop> getAllLaptops();
    Laptop getLaptopById(Long id);
    void deleteLaptop(Long id);
    List<Laptop> getLaptopsByDepartment(String department);
}

