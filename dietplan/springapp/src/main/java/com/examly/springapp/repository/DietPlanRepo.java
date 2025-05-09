package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.*;

@Repository
public interface DietPlanRepo extends JpaRepository<DietPlan,Integer> {
    List<DietPlan> findByUser(User user);
}
