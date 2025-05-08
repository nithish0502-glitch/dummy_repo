package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "service_id")
    private VehicleMaintenance service;
    
    private LocalDate appointmentDate;
    private String location;
    @Column(columnDefinition = "VARCHAR(50) DEFAULT 'Pending'")
    private String status; // Pending, Approved, Rejected

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    // Getters and Setters
    public Long getAppointmentId() {
        return id;
    }

    public void setAppointmentId(Long appointmentId) {
        this.id = appointmentId;
    }

    public VehicleMaintenance getService() {
        return service;
    }

    public void setService(VehicleMaintenance service) {
        this.service = service;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

