package com.examly.springapp.controller;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.service.LaptopService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laptop")
public class LaptopController {

    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createLaptop(@PathVariable Long userId, @RequestBody Laptop laptop) {
        if (laptop.getBrand() == null || laptop.getBrand().isEmpty() ||
            laptop.getSerialNumber() == null || laptop.getSerialNumber().isEmpty()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Laptop brand and serial number are required.");
        }

        try {
            Laptop createdLaptop = laptopService.createLaptop(userId, laptop);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdLaptop);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User with ID " + userId + " not found.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaptop(@PathVariable Long id, @RequestBody Laptop laptop) {
        try {
            Laptop updatedLaptop = laptopService.updateLaptop(id, laptop);
            return ResponseEntity.ok(updatedLaptop);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Laptop with ID " + id + " not found.");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllLaptops() {
        List<Laptop> laptops = laptopService.getAllLaptops();
        if (laptops.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No laptops found.");
        }
        return ResponseEntity.ok(laptops);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getLaptopById(@PathVariable Long id) {
        Optional<Laptop> laptop = laptopService.getLaptopById(id);
        if (laptop.isPresent()) {
            return ResponseEntity.ok(laptop.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Laptop with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaptop(@PathVariable Long id) {
        if (!laptopService.getLaptopById(id).isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Laptop with ID " + id + " not found.");
        }
        laptopService.deleteLaptop(id);
        return ResponseEntity.ok("Laptop deleted successfully.");
    }

    @GetMapping("/byDepartment/{department}")
    public ResponseEntity<?> getLaptopsByDepartment(@PathVariable String department) {
        List<Laptop> laptops = laptopService.getLaptopsByDepartment(department);
        if (laptops.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No laptops found for department: " + department);
        }
        return ResponseEntity.ok(laptops);
    }
}
