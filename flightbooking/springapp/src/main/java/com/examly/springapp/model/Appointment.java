package com.examly.springapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long appointmentId;

    @ManyToOne
    @JoinColumn(name = "petId")
    private Pet pet;

    private LocalDate appointmentDate;

    private String reason;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String status;

    // @OneToOne(mappedBy = "appointment")
    // private TreatmentRecord treatmentRecord;

    @OneToMany(mappedBy = "appointment") // Adjusted to one-to-many relationship
    private Set<TreatmentRecord> treatmentRecords;


    // Constructors, getters, and setters
    public Appointment() {
    }

    public Appointment(Pet pet, LocalDate appointmentDate, String reason, User user, String status) {
        this.pet = pet;
        this.appointmentDate = appointmentDate;
        this.reason = reason;
        this.user = user;
        this.status = status;
    }

    // Getters and setters
    public Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Long appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @JsonIgnore
    public Set<TreatmentRecord> getTreatmentRecords() {
        return treatmentRecords;
    }

    public void setTreatmentRecords(Set<TreatmentRecord> treatmentRecords) {
        this.treatmentRecords = treatmentRecords;
    }

    public LocalDate getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(LocalDate appointmentDate) {
        this.appointmentDate = appointmentDate;
    }


}
