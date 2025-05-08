package com.examly.springapp.controller;

import com.examly.springapp.model.Appointment;
import com.examly.springapp.service.AppointmentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmenService;

    // Add a new vehicle
    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@RequestBody Appointment vehicle) {
        Appointment createdVehicle = appointmenService.addAppointment(vehicle);
        return new ResponseEntity<>(createdVehicle, HttpStatus.CREATED);
    }

    //Update an existing vehicle by ID
    @PutMapping("/{id}")
    public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment updatedAppointment = appointmenService.updateAppointment(id, appointment);
        if (updatedAppointment != null) {
            return new ResponseEntity<>(updatedAppointment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Vehicle not found
        }
    }

    // Delete a vehicle by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        Appointment deletedAppointment= appointmenService.deleteAppointment(id);
        return new ResponseEntity<>(deletedAppointment, HttpStatus.OK);
    }

  
    
    // public ResponseEntity<Appointment> geAppointmentById(@PathVariable Long id) {
    //     Optional<Appointment> appointmen = appointmenService.getAppointmentById(id);
    //     if (appointmen.isPresent()) {
    //         return new ResponseEntity<>(appointmen.get(), HttpStatus.OK);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Vehicle not found
    //     }
    // }
    @GetMapping("/{id}")
    public ResponseEntity<List<Appointment>> getAppointmentsByUserId(@PathVariable int id) {
        List<Appointment> appointments = appointmenService.getAppointmentsByUserId(id);
        if (appointments != null && !appointments.isEmpty()) {
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // No appointments found for this user
        }
    }


    // Get all vehicles
    @GetMapping
    public ResponseEntity<List<Appointment>> getAllAppointment() {
        List<Appointment> appointment = appointmenService.getAllAppointment();
        return new ResponseEntity<>(appointment, HttpStatus.OK);
    }
}

