package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.examly.springapp.model.Dish;
import com.examly.springapp.service.DishService;

import java.util.List;

@RestController
@RequestMapping("/api/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @PostMapping("/{restaurantId}")
    public ResponseEntity<Dish> addDish(@PathVariable Long restaurantId, @RequestBody Dish dish) {
        Dish saved = dishService.addDish(restaurantId, dish);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
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
    public ResponseEntity<Void> deleteDish(@PathVariable Long id) {
        dishService.deleteDish(id);
        return ResponseEntity.noContent().build();
    }
}
