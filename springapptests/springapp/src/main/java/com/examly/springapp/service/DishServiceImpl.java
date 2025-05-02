package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Dish;
import com.examly.springapp.model.Restaurant;
import com.examly.springapp.repository.DishRepo;
import com.examly.springapp.repository.RestaurantRepo;

import java.util.List;
import java.util.Optional;

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
    return dishRepo.findDishesBelowPrice(price);
}


@Override
public boolean deleteDish(Long id) {
    Optional<Dish> optionalDish = dishRepo.findById(id);
    if (optionalDish.isPresent()) {
        dishRepo.delete(optionalDish.get());
        return true; // deleted
    } else {
        return false; 
    }
}

}

 