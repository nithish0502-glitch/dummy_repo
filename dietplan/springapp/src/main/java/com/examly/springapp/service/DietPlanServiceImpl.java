package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DietPlan;

@Service
public class DietPlanServiceImpl implements AgroChemicalService {

    
    

 
    @Override
    public List<DietPlan> getAllDietPlans() {
        return dietPlanRepository.findAll();
    }

    @Override
    public Optional<DietPlan> getDietPlanById(Long dietPlanId) {
        return dietPlanRepository.findById(dietPlanId);
    }

    @Override
    public DietPlan createDietPlan(DietPlan dietPlan) {
        return dietPlanRepository.save(dietPlan);
    }

    @Override
    public DietPlan updateDietPlan(Long dietPlanId, DietPlan updatedDietPlan) {
        DietPlan existingDietPlan = dietPlanRepository.findById(dietPlanId)
                .orElseThrow(() -> new RuntimeException("Diet Plan not found with id: " + dietPlanId));

        // Update fields
        existingDietPlan.setPlanName(updatedDietPlan.getPlanName());
        existingDietPlan.setDescription(updatedDietPlan.getDescription());
        existingDietPlan.setDuration(updatedDietPlan.getDuration());
        existingDietPlan.setStatus(updatedDietPlan.getStatus());
        // You can update createdAt if necessary, or keep it unchanged

        return dietPlanRepository.save(existingDietPlan);
    }

    @Override
    public void deleteDietPlan(Long dietPlanId) {
        dietPlanRepository.deleteById(dietPlanId);
    }
}