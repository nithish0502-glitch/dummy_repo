package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.examly.springapp.model.Dish;
import java.util.List;
import org.springframework.data.jpa.repository.Query;

public interface DishRepo extends JpaRepository<Dish, Long> {
    
    @Query("SELECT d FROM Dish d WHERE d.price < :price")
    List<Dish> findDishesBelowPrice(double price);
}
