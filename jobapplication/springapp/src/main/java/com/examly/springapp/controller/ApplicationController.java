package com.examly.springapp.controller;

import com.examly.springapp.exception.AlreadyAppliedForJobException;
import com.examly.springapp.model.Application;
import com.examly.springapp.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
@RestController
@RequestMapping("/applications")
public class ApplicationController {
 
    @Autowired
    private ApplicationService applicationService;

    @GetMapping
    public List<Application> getAllApplications() {
        return applicationService.getAllApplications();
    }
 
    @GetMapping("/{id}")
    public ResponseEntity<Application> getApplicationById(@PathVariable int id) {
        Application application = applicationService.getApplicationById(id);
        return ResponseEntity.ok(application);
    }

    @GetMapping("/user/{userId}")
    public List<Application> getApplicationsByUserId(@PathVariable int userId) {
        return applicationService.getApplicationsByUserId(userId); // Call the service method
    }
 
    @PostMapping
    public ResponseEntity<Application> saveApplication(@RequestBody Application application) {
         try {
            Application savedApplication = applicationService.saveApplication(application);
            return ResponseEntity.ok(savedApplication);
        } catch (AlreadyAppliedForJobException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null); // Respond with BAD_REQUEST
        }
    }
 
    @PutMapping("/{id}")
    public ResponseEntity<Application> updateApplication(@PathVariable int id, @RequestBody Application application) {
        Application updatedApplication = applicationService.updateApplication(id, application);
        return ResponseEntity.ok(updatedApplication);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApplication(@PathVariable int id) {
        applicationService.deleteApplication(id);
        return ResponseEntity.noContent().build();
    }
}
