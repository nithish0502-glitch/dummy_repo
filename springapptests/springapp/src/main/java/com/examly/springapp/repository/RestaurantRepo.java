package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Restaurant;
import java.util.Optional;

public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {
    Optional<Restaurant> findByNameAndLocation(String name, String location);
}
