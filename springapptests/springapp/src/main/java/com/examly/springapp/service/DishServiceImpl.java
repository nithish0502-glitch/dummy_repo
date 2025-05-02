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
    public Optional<Dish> addDish(Long restaurantId, Dish dish) {
    if (dish.getStock() <= 0) {
        System.out.println("Dish is currently out of stock.");
        return Optional.empty();
    }

    Optional<Restaurant> optionalRestaurant = restaurantRepo.findById(restaurantId);
    if (optionalRestaurant.isPresent()) {
        dish.setRestaurant(optionalRestaurant.get());
        return Optional.of(dishRepo.save(dish));
    } else {
        System.out.println("Restaurant with ID " + restaurantId + " not found.");
        return Optional.empty();
    }
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

 