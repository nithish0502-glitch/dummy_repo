package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Dish;
import com.examly.springapp.model.Restaurant;
import com.examly.springapp.repository.DishRepo;
import com.examly.springapp.repository.RestaurantRepo;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishRepo dishRepo;

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public Dish addDish(Long restaurantId, Dish dish) {
        if (dish.getStock() <= 0) {
            throw new RuntimeException("Dish is currently out of stock.");
        }        
        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + restaurantId + " not found."));
        dish.setRestaurant(restaurant);
        return dishRepo.save(dish);
    }

    @Override
    public List<Dish> getDishesBelowPrice(double price) {
        List<Dish> dishes = dishRepo.findDishesBelowPrice(price);
        if (dishes.isEmpty()) {
            throw new RuntimeException("No dishes found under the specified price range.");
        }
        return dishes;
    }

    @Override
    public void deleteDish(Long id) {
        Dish dish = dishRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Dish not found with ID: " + id));
        dishRepo.delete(dish);
    }
}
