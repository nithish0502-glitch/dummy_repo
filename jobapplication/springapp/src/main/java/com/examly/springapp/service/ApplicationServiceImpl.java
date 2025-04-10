package com.examly.springapp.service;


import com.examly.springapp.exception.AlreadyAppliedForJobException;
import com.examly.springapp.model.Application;
import com.examly.springapp.repository.ApplicationRepo;
import com.examly.springapp.repository.JobRepo;
import com.examly.springapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationServiceImpl implements ApplicationService {

    @Autowired
    private ApplicationRepo applicationRepository;

    @Autowired
    private JobRepo jobRepo;

    @Autowired
    private UserRepo userRepo;
 
    @Override
    public List<Application> getAllApplications() {
        return applicationRepository.findAll();
    }
 
    @Override
    public Application getApplicationById(int id) {
        Optional<Application> optionalApplication = applicationRepository.findById(id);
        return optionalApplication.orElseThrow(() -> new RuntimeException("Application not found"));
    }

    @Override
    public Application saveApplication(Application application) throws AlreadyAppliedForJobException {

        Optional<Application> existingApplication = applicationRepository.findByUserAndJob(userRepo.findById(application.getUser().getUserId()).orElse(null), jobRepo.findById(application.getJob().getJobId()).orElse(null));
          
        if (existingApplication.isPresent()) {
            throw new AlreadyAppliedForJobException("You have already applied for this job.");
        }
  
        application.setJob(jobRepo.findById(application.getJob().getJobId()).orElse(null));
        application.setUser(userRepo.findById(application.getUser().getUserId()).orElse(null));
        application.setApplicationDate(new Date());
        return applicationRepository.save(application);
    }

    public List<Application> getApplicationsByUserId(int userId) {
        return applicationRepository.findByUser_UserId(userId); // Call the repository method
    }
  
    @Override
    public Application updateApplication(int id, Application application) {
        Application existingApplication = getApplicationById(id);
        existingApplication.setStatus(application.getStatus());
        existingApplication.setUser(application.getUser());
        existingApplication.setJob(application.getJob());
        existingApplication.setYearsOfExperience(application.getYearsOfExperience());
        existingApplication.setSkills(application.getSkills());
        existingApplication.setApplicationDate(application.getApplicationDate());
        existingApplication.setLocationPreference(application.getLocationPreference());
        return applicationRepository.save(existingApplication);
    }

    @Override
    public void deleteApplication(int id) {
        applicationRepository.deleteById(id);
    }
}