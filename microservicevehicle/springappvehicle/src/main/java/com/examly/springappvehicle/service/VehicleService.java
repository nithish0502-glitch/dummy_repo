package com.examly.springapp.service;

//import com.examly.springapp.model.Service;
//import com.examly.springapp.model.ServiceForVehicle;
import com.examly.springapp.model.VehicleMaintenance;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

public interface VehicleService {
    VehicleMaintenance addService(VehicleMaintenance service);  // Add a new service
    VehicleMaintenance updateService(Long id, VehicleMaintenance service);  // Update service by ID
    VehicleMaintenance deleteService(Long id);  // Delete service by ID
    Optional<VehicleMaintenance> getServiceById(Long id);  // Get service by ID
    List<VehicleMaintenance> getAllServices();  // Get all services
    List<VehicleMaintenance> findByServiceName(String serviceName);
    
}

