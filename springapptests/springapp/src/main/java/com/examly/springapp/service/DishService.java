package com.examly.springapp.service;

import com.examly.springapp.model.Dish;
import java.util.List;

public interface DishService {
    Dish addDish(Long restaurantId, Dish dish);
    List<Dish> getDishesBelowPrice(double price);
    boolean deleteDish(Long id);
}
