package com.examly.springapp.repository;

import com.examly.springapp.model.Flight;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepo extends JpaRepository<Flight, Long> {
    List<Feedback> findByUserUserId(Long userId);
}
