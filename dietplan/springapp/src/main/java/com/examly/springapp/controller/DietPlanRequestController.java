package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.security.access.prepost.PreAuthorize;

import com.examly.springapp.model.DietPlanRequest;
import com.examly.springapp.model.User;
import com.examly.springapp.service.DietPlanRequestService;

@RestController
public class DietPlanRequestController {
 
    @Autowired
    DietPlanRequestService dietPlanRequestService;
    
   @GetMapping
    public ResponseEntity<List<DietPlanRequest>> getAllDietPlanRequests() {
        List<DietPlanRequest> dietPlanRequests = dietPlanRequestService.getAllDietPlanRequests();
        return ResponseEntity.ok(dietPlanRequests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DietPlanRequest> getDietPlanRequestById(@PathVariable("id") Long requestId) {
        Optional<DietPlanRequest> dietPlanRequest = dietPlanRequestService.getDietPlanRequestById(requestId);
        return dietPlanRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<DietPlanRequest> createDietPlanRequest(@RequestBody DietPlanRequest dietPlanRequest) {
        DietPlanRequest createdDietPlanRequest = dietPlanRequestService.createDietPlanRequest(dietPlanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDietPlanRequest);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DietPlanRequest> updateDietPlanRequest(@PathVariable("id") Long requestId,
                                                                 @RequestBody DietPlanRequest updatedDietPlanRequest) {
        DietPlanRequest updated = dietPlanRequestService.updateDietPlanRequest(requestId, updatedDietPlanRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDietPlanRequest(@PathVariable("id") Long requestId) {
        dietPlanRequestService.deleteDietPlanRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
 