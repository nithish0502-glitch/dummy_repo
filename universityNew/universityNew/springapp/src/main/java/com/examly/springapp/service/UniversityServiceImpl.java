package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.University;
import com.examly.springapp.repository.UniversityRepo;


@Service
public class UniversityServiceImpl {

    @Autowired
    private UniversityRepo universityRepo;

    public University addUniversity(University university)
    {
       // return universityRepo.save(university).orElse(null);

       try
       {
        universityRepo.save(university);
        return university;
       }catch(Exception e)
       {
        e.printStackTrace();
        return null;
       }
    }


    public University getUniversityById(int universityId)
    {
        return universityRepo.findById(universityId).orElse(null);
    }
    
}
