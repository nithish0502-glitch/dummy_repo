package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Job;

@Repository
public interface JobRepo extends JpaRepository<Job,Integer> {
    @Query("SELECT a.job FROM Application a WHERE a.user.userId = :userId")
    List<Job> findJobsByUserId(int userId);
}
 