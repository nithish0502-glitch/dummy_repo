package com.examly.springapp.service;

import com.examly.springapp.model.*;
import com.examly.springapp.repository.AppointmentRepo;
// import com.examly.springapp.repository.ServiceRepo;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.repository.VehicleServiceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private VehicleServiceRepo serviceRepository;

    @Autowired
    private AppointmentRepo appointmentRepository;

    @Autowired
    private UserRepo userRepository;
 
    @Override
    public Appointment addAppointment(Appointment appointment) {
        User user = userRepository.findById(appointment.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found with id: " + appointment.getUser().getId()));

                VehicleMaintenance service = serviceRepository.findById(appointment.getService().getId())
                .orElseThrow(
                        () -> new RuntimeException("Service not found with id: " + appointment.getService().getId()));
                        System.out.println("********************************************************");
                        System.out.println("Checking if User and Service exist before sending request...");
System.out.println("User ID 2 exists: " + userRepository.existsById(2));
System.out.println("Service ID 1 exists: " + serviceRepository.existsById(1L));
   
        appointment.setUser(user);
        appointment.setService(service);
        return appointmentRepository.save(appointment); // Save and return the newly added vehicle
    }

    @Override
    public Appointment updateAppointment(Long id, Appointment vehicle) {
        Optional<Appointment> existingVehicle = appointmentRepository.findById(id);
        if (existingVehicle.isPresent()) {
            Appointment updatedVehicle = existingVehicle.get();
            updatedVehicle.setStatus(vehicle.getStatus());
            return appointmentRepository.save(updatedVehicle);
        }
        return null;
    }

    @Override
    public Appointment deleteAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id).orElse(null);
        appointmentRepository.deleteById(id); // Delete appointment by ID
        return appointment; // Return the deleted appointment
    }
    


    @Override
    public Optional<Appointment> getAppointmentById(Long id) {
        return appointmentRepository.findById(id); // Retrieve vehicle by ID
    }

    @Override
    public List<Appointment> getAllAppointment() {
        return appointmentRepository.findAll(); // Retrieve all vehicles
    }

    @Override
    public List<Appointment> getAppointmentsByUserId(int id) {
        return appointmentRepository.findByUserId(id); // Query repository for appointments by user ID
    }
}
