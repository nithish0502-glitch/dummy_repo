package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.University;

@Repository
public interface UniversityRepo extends JpaRepository<University,Integer> {
    boolean existsByUniversityName(String universityName);
    
}
 