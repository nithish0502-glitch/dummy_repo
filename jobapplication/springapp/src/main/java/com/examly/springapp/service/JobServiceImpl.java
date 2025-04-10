package com.examly.springapp.service;

import com.examly.springapp.model.Job;
import com.examly.springapp.repository.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private final JobRepo jobRepository;

    @Autowired
    public JobServiceImpl(JobRepo jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job createJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public Job updateJob(int id, Job job) {
        if (jobRepository.existsById(id)) {
            job.setJobId(id);
            return jobRepository.save(job);
        } 
        throw new IllegalArgumentException("Job not found with id: " + id);
    }
  
    @Override
    public Optional<Job> getJobById(int id) {
        return jobRepository.findById(id);
    }

    @Override
    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    @Override
    public void deleteJob(int id) {
        if (jobRepository.existsById(id)) {
            jobRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("Job not found with id: " + id);
        }
    }
 
    @Override
    public List<Job> getJobByUserId(int usedId){
        return jobRepository.findJobsByUserId(usedId);
    }
}
 