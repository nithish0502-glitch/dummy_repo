package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.Dish;
import com.examly.springapp.service.DishService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("/{restaurantId}")
     public ResponseEntity<?> addDish(@PathVariable Long restaurantId, @RequestBody Dish dish) {
    Optional<Dish> saved = dishService.addDish(restaurantId, dish);
    if (saved.isPresent()) {
        return new ResponseEntity<>(saved.get(), HttpStatus.CREATED);
    } else if (dish.getStock() <= 0) {
        return new ResponseEntity<>("Dish is currently out of stock.", HttpStatus.BAD_REQUEST);
    } else {
        return new ResponseEntity<>("Restaurant with ID " + restaurantId + " not found.", HttpStatus.NOT_FOUND);
    }
}


    @GetMapping("/search/{price}")
    public ResponseEntity<List<Dish>> getDishesUnderPrice(@PathVariable double price) {
        List<Dish> dishes = dishService.getDishesBelowPrice(price);
        if (dishes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(dishes, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDish(@PathVariable Long id) {
    boolean deleted = dishService.deleteDish(id);
    if (deleted) {
        return ResponseEntity.ok("Dish deleted successfully.");
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dish not found with ID: "+id);
    }

    
}

}

