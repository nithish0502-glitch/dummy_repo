package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.DietPlan;
import com.examly.springapp.model.DietPlanRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.DietPlanRepo;
import com.examly.springapp.repository.DietPlanRequestRepo;
import com.examly.springapp.repository.UserRepo;
 
@Service
public class DietPlanRequestServiceImpl implements DietPlanRequestService {

    @Autowired
    DietPlanRequestRepo dietPlanRequestRepo;
  @Override
    public List<DietPlanRequest> getAllDietPlanRequests() {
        return dietPlanRequestRepo.findAll();
    }

    @Override
    public Optional<DietPlanRequest> getDietPlanRequestById(int requestId) {
        return dietPlanRequestRepo.findById(requestId);
    }

    @Override
    public DietPlanRequest createDietPlanRequest(DietPlanRequest dietPlanRequest) {
        return dietPlanRequestRepo.save(dietPlanRequest);
    }

    @Override
    public DietPlanRequest updateDietPlanRequest(int requestId, DietPlanRequest updatedDietPlanRequest) {
        DietPlanRequest existingDietPlanRequest = dietPlanRequestRepo.findById(requestId)
                .orElseThrow(() -> new RuntimeException("Diet Plan Request not found with id: " + requestId));

        // Update fields
        existingDietPlanRequest.setAge(updatedDietPlanRequest.getAge());
        existingDietPlanRequest.setWeight(updatedDietPlanRequest.getWeight());
        existingDietPlanRequest.setHeight(updatedDietPlanRequest.getHeight());
        existingDietPlanRequest.setGender(updatedDietPlanRequest.getGender());
        existingDietPlanRequest.setActivityLevel(updatedDietPlanRequest.getActivityLevel());
        existingDietPlanRequest.setGoal(updatedDietPlanRequest.getGoal());
        existingDietPlanRequest.setMedicalConditions(updatedDietPlanRequest.getMedicalConditions());
        existingDietPlanRequest.setStatus(updatedDietPlanRequest.getStatus());
        // You can update createdAt if necessary, or keep it unchanged

        return dietPlanRequestRepo.save(existingDietPlanRequest);
    }

    @Override
    public void deleteDietPlanRequest(int requestId) {
        dietPlanRequestRepo.deleteById(requestId);
    }
}
