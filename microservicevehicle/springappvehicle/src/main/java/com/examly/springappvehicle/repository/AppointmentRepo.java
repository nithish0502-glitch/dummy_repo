package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Appointment;



@Repository
public interface AppointmentRepo extends JpaRepository<Appointment, Long> {
    // You can add custom queries if needed
    List<Appointment> findByUserId(int id);
}

