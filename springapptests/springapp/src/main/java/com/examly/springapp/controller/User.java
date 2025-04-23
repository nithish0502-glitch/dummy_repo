package com.examly.springapp.controller;

import com.examly.springapp.model.Laptop;
import com.examly.springapp.service.LaptopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/laptop")
public class User {

     @Autowired
    private LaptopService laptopService;

    @PostMapping("/user/{userId}")
    public ResponseEntity<?> addLaptop(@RequestBody Laptop laptop, @PathVariable Long userId) {
        try {
            Laptop savedLaptop = laptopService.createLaptopWithUser(laptop, userId);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedLaptop);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Error saving laptop: " + e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllLaptops() {
        List<Laptop> laptops = laptopService.getAllLaptops();
        if (laptops.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No laptops found.");
        }
        return ResponseEntity.ok(laptops);
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<?> getLaptopById(@PathVariable Long id) {
    //     Optional<Laptop> laptop = laptopService.getLaptopById(id);
    //     return laptop.map(ResponseEntity::ok)
    //                  .orElse(() -> ResponseEntity.status(HttpStatus.NOT_FOUND)
    //                                                 .body("Laptop with ID " + id + " not found."));
    // }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateLaptop(@PathVariable Long id, @RequestBody Laptop updatedLaptop) {
        Laptop laptop = laptopService.updateLaptop(id, updatedLaptop);
        if (laptop != null) {
            return ResponseEntity.ok(laptop);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laptop with ID " + id + " not found.");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLaptop(@PathVariable Long id) {
        boolean deleted = laptopService.deleteLaptop(id);
        if (deleted) {
            return ResponseEntity.ok("Laptop deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laptop with ID " + id + " not found.");
        }
    }
}
