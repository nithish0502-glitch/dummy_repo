package com.examly.springapp.controller;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.service.LaptopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptop")
public class LaptopController {

    @Autowired
    private LaptopService laptopService;

    @PostMapping("/{UserId}")
    public ResponseEntity<?> createLaptop(@RequestBody Laptop laptop) {
        if (laptop.getBrand().isEmpty() || laptop.getSerialNumber().isEmpty()) {
            return ResponseEntity.status(409).body("Laptop brand and serial number are required.");
        }
        return ResponseEntity.status(201).body(laptopService.createLaptop(laptop));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop) {
        return ResponseEntity.ok(laptopService.updateLaptop(id, laptop));
    }

    @GetMapping
    public ResponseEntity<?> getAllLaptops() {
        List<Laptop> laptops = laptopService.getAllLaptops();
        if (laptops.isEmpty()) return ResponseEntity.status(404).body("No laptops found.");
        return ResponseEntity.ok(laptops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLaptopById(@PathVariable Long id) {
        return ResponseEntity.ok(laptopService.getLaptopById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLaptop(@PathVariable Long id) {
        laptopService.deleteLaptop(id);
        return ResponseEntity.ok("Laptop deleted successfully.");
    }

    @GetMapping("/byDepartment/{department}")
    public ResponseEntity<List<Laptop>> getLaptopsByDepartment(@PathVariable String department) {
        return ResponseEntity.ok(laptopService.getLaptopsByDepartment(department));
    }
}

