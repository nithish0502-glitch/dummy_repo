package com.examly.springapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.University;
import com.examly.springapp.service.UniversityServiceImpl;

@RestController
public class UniversityController {

    @Autowired
    private UniversityServiceImpl universityServiceImpl;


    @PostMapping("university")
    public ResponseEntity<University> addUniversity(@RequestBody University university)
    {
        University newuniversity = universityServiceImpl.addUniversity(university);
        if(newuniversity!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(newuniversity);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }
    
    @GetMapping("university/{universityId}")
    public ResponseEntity<University> getUniversityId(@PathVariable int universityId)
    {
        University getUniversity = universityServiceImpl.getUniversityById(universityId);
        if (getUniversity!=null) {
            return ResponseEntity.status(HttpStatus.OK).body(getUniversity);

        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }



}
