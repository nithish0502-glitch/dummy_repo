package com.examly.springapp.service;

import com.examly.springapp.model.Appointment;


import java.util.List;
import java.util.Optional;

public interface AppointmentService {
    Appointment addAppointment(Appointment vehicle);  // To add a new vehicle
    public Appointment updateAppointment(Long id, Appointment appointment) ;
      
    Appointment deleteAppointment(Long id);  // To delete a vehicle
    Optional<Appointment> getAppointmentById(Long id);  // To get a vehicle by its ID
    List<Appointment> getAllAppointment();  // To get all vehicles
    List<Appointment> getAppointmentsByUserId(int id);
}

