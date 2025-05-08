package com.examly.springapp.service;

//import com.examly.springapp.model.ServiceForVehicle;
import com.examly.springapp.model.VehicleMaintenance;
import com.examly.springapp.repository.VehicleServiceRepo;
//import com.examly.springapp.repository.ServiceRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleServiceRepo serviceRepository;

    @Override
    public VehicleMaintenance addService(VehicleMaintenance service) {
        return serviceRepository.save(service);  // Save and return the newly added service
    }

    @Override
    public VehicleMaintenance updateService(Long id, VehicleMaintenance service) {
        Optional<VehicleMaintenance> existingService = serviceRepository.findById(id);
        if (existingService.isPresent()) {
            VehicleMaintenance updatedService = existingService.get();
            updatedService.setServiceName(service.getServiceName());
            updatedService.setTypeOfVehicle(service.getTypeOfVehicle());
            updatedService.setServicePrice(service.getServicePrice());
            return serviceRepository.save(updatedService);
        }
        return null;  // Service not found, handle as needed
    }
 
    @Override
public VehicleMaintenance deleteService(Long id) {
    VehicleMaintenance vehicleMaintenance = serviceRepository.findById(id).orElse(null);
    if (vehicleMaintenance != null) {
        serviceRepository.deleteById(id);  // Delete service by ID
        return vehicleMaintenance;  // Return the deleted service details
    }
    return null;  // Return null if service not found
}
 

    @Override
    public Optional<VehicleMaintenance> getServiceById(Long id) {
        return serviceRepository.findById(id);  // Get service by ID
    }

    @Override
    public List<VehicleMaintenance> getAllServices() {
        return serviceRepository.findAll();  // Get all services
    }
    
    public List<VehicleMaintenance> findByServiceName(String serviceName) {
        return serviceRepository.findByServiceName(serviceName);
    }
  
}

