package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;
import com.examly.springapp.service.*;
import com.examly.springapp.model.DietPlan;

@RestController
public class DietPlanController {

    @Autowired
    private DietPlanService dietPlanService;
@GetMapping("/api/dietplan")
@PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<List<DietPlan>> getAllDietPlans() {
        List<DietPlan> dietPlans = dietPlanService.getAllDietPlans();
        return ResponseEntity.ok(dietPlans);
    }

    @GetMapping("/api/dietplan/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlan> getDietPlanById(@PathVariable int dietPlanId) {
        Optional<DietPlan> dietPlan = dietPlanService.getDietPlanById(dietPlanId);
        return dietPlan.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/dietplan/user/{userId}")
    @PreAuthorize("hasAnyAuthority('NUTRITIONIST','USER')")
    public ResponseEntity<DietPlan> getDietPlanByUserId(@PathVariable int dietPlanId) {
        return dietPlanService.getDietPlanByUserId(userId);
    }


    @PostMapping("/api/dietplan") 
    @PreAuthorize("hasAnyAuthority('NUTRITIONIST')")
    public ResponseEntity<DietPlan> createDietPlan(@RequestBody DietPlan dietPlan) {
        DietPlan createdDietPlan = dietPlanService.createDietPlan(dietPlan);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDietPlan);
    }
  
    @PutMapping("/api/dietplan/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlan> updateDietPlan(@PathVariable int dietPlanId, 
                                                   @RequestBody DietPlan updatedDietPlan) {
        DietPlan updated = dietPlanService.updateDietPlan(dietPlanId, updatedDietPlan);
        return ResponseEntity.ok(updated);
    }
               
    @DeleteMapping("/api/dietplan/{id}")
    @PreAuthorize("hasAnyAuthority('USER', 'NUTRITIONIST')")
    public ResponseEntity<Void> deleteDietPlan(@PathVariable int dietPlanId) {
        dietPlanService.deleteDietPlan(dietPlanId);
        return ResponseEntity.noContent().build();
    }
  
}
 