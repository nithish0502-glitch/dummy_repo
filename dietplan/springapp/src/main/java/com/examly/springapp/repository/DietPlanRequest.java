package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

@Repository
public interface DietPlanRequest extends JpaRepository<Request,Integer> {
    List<Request> findByUser(User user);
}
