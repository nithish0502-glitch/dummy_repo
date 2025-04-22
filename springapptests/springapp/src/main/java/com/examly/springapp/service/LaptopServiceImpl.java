package com.examly.springapp.service;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.repository.LaptopRepository;
import com.examly.springapp.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LaptopServiceImpl implements LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopServiceImpl(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    @Override
    public Laptop createLaptop(Laptop laptop) {
        return laptopRepository.save(laptop);
    }

    @Override
    public Laptop updateLaptop(Long id, Laptop laptopDetails) {
        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop with ID " + id + " not found."));
        laptop.setBrand(laptopDetails.getBrand());
        laptop.setModel(laptopDetails.getModel());
        laptop.setSerialNumber(laptopDetails.getSerialNumber());
        laptop.setStatus(laptopDetails.getStatus());
        return laptopRepository.save(laptop);
    }

    @Override
    public void deleteLaptop(Long id) {
        Laptop laptop = laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop with ID " + id + " not found."));
        laptopRepository.delete(laptop);
    }

    @Override
    public Laptop getLaptopById(Long id) {
        return laptopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Laptop with ID " + id + " not found."));
    }

    @Override
    public List<Laptop> getAllLaptops() {
        return laptopRepository.findAll();
    }

    @Override
    public List<Laptop> getLaptopsByDepartment(String department) {
        return laptopRepository.findByUserDepartment(department);
    }
}

