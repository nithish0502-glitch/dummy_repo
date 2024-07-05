package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateUniversityException;
import com.examly.springapp.model.University;
import com.examly.springapp.repository.UniversityRepo;


@Service
public class UniversityServiceImpl {

    @Autowired
    private UniversityRepo universityRepo;

    public University addUniversity(University university)
    {
       {
        if (universityRepo.existsByUniversityName(university.getUniversityName())) {
            throw new DuplicateUniversityException("University with name " + university.getUniversityName() + " already exists!");
        }
        
        // If not exists, save the department
        return universityRepo.save(university);
    }
    }


    public University getUniversityById(int universityId)
    {
        return universityRepo.findById(universityId).orElse(null);
    }
    
}
