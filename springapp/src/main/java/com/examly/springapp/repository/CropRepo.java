package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Crop;

@Repository
public interface CropRepo extends JpaRepository<Crop, Integer> {
    
}
