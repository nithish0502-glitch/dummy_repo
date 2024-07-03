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
    
   @GetMapping("/api/dietplanrequest") 
   @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<List<DietPlanRequest>> getAllDietPlanRequests() {
        List<DietPlanRequest> dietPlanRequests = dietPlanRequestService.getAllDietPlanRequests();
        return ResponseEntity.ok(dietPlanRequests);
    }

    @GetMapping("/api/dietplanrequest/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlanRequest> getDietPlanRequestById(@PathVariable int requestId) {
        Optional<DietPlanRequest> dietPlanRequest = dietPlanRequestService.getDietPlanRequestById(requestId);
        return dietPlanRequest.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/api/dietplanrequest")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlanRequest> createDietPlanRequest(@RequestBody DietPlanRequest dietPlanRequest) {
        DietPlanRequest createdDietPlanRequest = dietPlanRequestService.createDietPlanRequest(dietPlanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdDietPlanRequest);
    }

    @PutMapping("/api/dietplanrequest/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<DietPlanRequest> updateDietPlanRequest(@PathVariable int requestId,
                                                                 @RequestBody DietPlanRequest updatedDietPlanRequest) {
        DietPlanRequest updated = dietPlanRequestService.updateDietPlanRequest(requestId, updatedDietPlanRequest);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/dietplanrequest/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<Void> deleteDietPlanRequest(@PathVariable int requestId) {
        dietPlanRequestService.deleteDietPlanRequest(requestId);
        return ResponseEntity.noContent().build();
    }
}
    