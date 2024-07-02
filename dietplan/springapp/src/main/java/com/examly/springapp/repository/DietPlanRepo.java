package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.AgroChemical;

@Repository
public interface DietPlanRepo extends JpaRepository<AgroChemical,Integer> {
    
}
