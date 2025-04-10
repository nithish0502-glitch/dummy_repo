package com.examly.springapp.service;

import com.examly.springapp.model.Job;

import java.util.List;
import java.util.Optional;

public interface JobService {

    Job createJob(Job job);

    Job updateJob(int id, Job job);

    Optional<Job> getJobById(int id);

    List<Job> getAllJobs();

    void deleteJob(int id);

    List<Job> getJobByUserId(int usedId);
}
  