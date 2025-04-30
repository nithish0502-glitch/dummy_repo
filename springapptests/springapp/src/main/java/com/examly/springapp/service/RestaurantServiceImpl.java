package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Restaurant;
import com.examly.springapp.repository.RestaurantRepo;
import com.examly.springapp.exception.DuplicateRestaurantException;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Override
    public Restaurant addRestaurant(Restaurant restaurant) {
        restaurantRepo.findByNameAndLocation(restaurant.getName(), restaurant.getLocation())
                .ifPresent(existing -> {
                    throw new DuplicateRestaurantException("A restaurant with the same name and location already exists.");
                });
        return restaurantRepo.save(restaurant);
    }

    @Override
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant with ID " + id + " not found."));
    }

    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepo.findAll();
    }
}
