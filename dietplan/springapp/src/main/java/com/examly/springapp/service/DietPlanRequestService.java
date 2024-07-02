package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.DietPlanRequest;

public interface DietPlanRequestService {
    List<DietPlanRequest> getAllDietPlanRequests();

    Optional<DietPlanRequest> getDietPlanRequestById(Long requestId);

    DietPlanRequest createDietPlanRequest(DietPlanRequest dietPlanRequest);

    DietPlanRequest updateDietPlanRequest(Long requestId, DietPlanRequest dietPlanRequest);

    void deleteDietPlanRequest(Long requestId);
}
