package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DietPlan;
import com.examly.springapp.repository.DietPlanRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.model.User;

import org.springframework.beans.factory.annotation.Autowired;
@Service
public class DietPlanServiceImpl implements DietPlanService {

     @Autowired
     DietPlanRepo dietPlanRepo;

     @Autowired
     UserRepo userRepo;
    @Override
    public List<DietPlan> getAllDietPlans() {
        return dietPlanRepo.findAll();
    }

    @Override
    public List<DietPlan> getDietPlanByUserId(int userId){
        User user=userRepo.findById(userId).orElse(null);
        return dietPlanRepo.findByUser(user);
    }

    @Override
    public Optional<DietPlan> getDietPlanById(int dietPlanId) {
        return dietPlanRepo.findById(dietPlanId);
    }

    @Override
    public DietPlan createDietPlan(DietPlan dietPlan) {
        return dietPlanRepo.save(dietPlan);
    }

    @Override
    public DietPlan updateDietPlan(int dietPlanId, DietPlan updatedDietPlan) {
        DietPlan existingDietPlan = dietPlanRepo.findById(dietPlanId)
                .orElseThrow(() -> new RuntimeException("Diet Plan not found with id: " + dietPlanId));

        // Update fields
        existingDietPlan.setPlanName(updatedDietPlan.getPlanName());
        existingDietPlan.setDescription(updatedDietPlan.getDescription());
        existingDietPlan.setDuration(updatedDietPlan.getDuration());
        existingDietPlan.setStatus(updatedDietPlan.getStatus());
        // You can update createdAt if necessary, or keep it unchanged

        return dietPlanRepo.save(existingDietPlan);
    }

    @Override
    public DietPlan deleteDietPlan(int dietPlanId) {
        DietPlan dietPlan=dietPlanRepo.findById(dietPlanId).orElse(null);
        dietPlanRepo.deleteById(dietPlanId);
        return dietPlan;
    }
}