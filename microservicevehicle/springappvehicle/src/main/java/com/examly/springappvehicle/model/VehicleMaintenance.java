package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
@Table(name = "service_for_vehicle")
public class VehicleMaintenance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String serviceName;
    private int servicePrice;
    private String typeOfVehicle; // Pending, In Progress, Completed
    


    public VehicleMaintenance() {
    }

    
    public VehicleMaintenance(Long id, String serviceName, int servicePrice, String typeOfVehicle) {
        this.id = id;
        this.serviceName = serviceName;
        this.servicePrice = servicePrice;
        this.typeOfVehicle = typeOfVehicle;
    }


    public int getServicePrice() {
        return servicePrice;
    }


    public void setServicePrice(int servicePrice) {
        this.servicePrice = servicePrice;
    }


    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }


    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
  

    // public Appointment getAppointment() {
    //     return appointment;
    // }
    
    // public void setAppointment(Appointment appointment) {
    //     this.appointment = appointment;
    // }
   


   
}

