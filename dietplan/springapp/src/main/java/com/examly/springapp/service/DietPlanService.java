package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.DietPlan;

public interface DietPlanService {
    List<DietPlan> getAllDietPlans();

    Optional<DietPlan> getDietPlanById(int dietPlanId);

    DietPlan createDietPlan(DietPlan dietPlan);

    List<DietPlan> getDietPlanByUserId(int userId); 

    DietPlan updateDietPlan(int dietPlanId, DietPlan dietPlan);


    DietPlan deleteDietPlan(int dietPlanId);
}
