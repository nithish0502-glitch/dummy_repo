package com.examly.springapp.repository;

import com.examly.springapp.model.Appointment;

//import com.examly.springapp.model.ServiceForVehicle;
import com.examly.springapp.model.VehicleMaintenance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.security.Provider.Service;
import java.util.List;

@Repository
public interface VehicleServiceRepo extends JpaRepository<VehicleMaintenance, Long> {
    // Find services by vehicle
//     List<ServiceForVehicle> findByAppointment(Appointment appointment);

//     // // Find services by status (e.g., Pending, In Progress, Completed)
//      //List<ServiceForVehicle> findBytyForVehicles(String typeOfVehicle);
    
//    // Find services by vehicle ID
//    List<ServiceForVehicle> findByAppointmentId(Long appointmentId);
List<VehicleMaintenance> findByServiceName(String serviceName);

}

