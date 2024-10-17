package com.examly.springapp.repository;

import com.examly.springapp.model.Feedback;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Feedback, Long> {
    List<Feedback> findByUserUserId(Long userId);
}
