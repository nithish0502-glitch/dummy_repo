package com.examly.springapp.service;

import com.examly.springapp.model.Dish;
import java.util.List;
import java.util.Optional;

public interface DishService {
    Optional<Dish> addDish(Long restaurantId, Dish dish);
    List<Dish> getDishesBelowPrice(double price);
    boolean deleteDish(Long id);
}
