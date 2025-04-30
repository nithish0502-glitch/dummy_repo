package com.examly.springapp.service;

import com.examly.springapp.model.Restaurant;
import java.util.List;

public interface RestaurantService {
    Restaurant addRestaurant(Restaurant restaurant);
    Restaurant getRestaurantById(Long id);
    List<Restaurant> getAllRestaurants();
}
