package com.examly.springapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Application;
import com.examly.springapp.model.Job;
import com.examly.springapp.model.User;

@Repository
public interface ApplicationRepo extends JpaRepository<Application,Integer> {
    Optional<Application> findByUserAndJob(User user, Job job);
    List<Application> findByUser_UserId(int userId);
}
