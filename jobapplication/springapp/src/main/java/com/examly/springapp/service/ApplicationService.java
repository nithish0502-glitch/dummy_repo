package com.examly.springapp.service;

import com.examly.springapp.exception.AlreadyAppliedForJobException;
import com.examly.springapp.model.Application;
import java.util.List;
 
public interface ApplicationService {
    List<Application> getAllApplications();
    Application getApplicationById(int id);
    Application saveApplication(Application application) throws AlreadyAppliedForJobException;
    Application updateApplication(int id, Application application);
    void deleteApplication(int id);
    public List<Application> getApplicationsByUserId(int userId);
}  