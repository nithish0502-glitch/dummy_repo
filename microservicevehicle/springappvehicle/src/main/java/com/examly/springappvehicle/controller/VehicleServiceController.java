package com.examly.springapp.controller;

//import com.examly.springapp.model.Service;

import com.examly.springapp.model.VehicleMaintenance;
// import com.examly.springapp.service.ServiceService;
import com.examly.springapp.service.VehicleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Provider.Service;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/services")
public class VehicleServiceController {

    @Autowired
    private VehicleService serviceService;

    // Add a new service
    @PostMapping
    public ResponseEntity<VehicleMaintenance> addService(@RequestBody VehicleMaintenance service) {
        VehicleMaintenance createdService = serviceService.addService(service);
        return new ResponseEntity<>(createdService, HttpStatus.CREATED);
    }

    // Update an existing service by ID
    @PutMapping("/{id}")
    public ResponseEntity<VehicleMaintenance> updateService(@PathVariable Long id, @RequestBody VehicleMaintenance service) {
        VehicleMaintenance updatedService = serviceService.updateService(id, service);
        if (updatedService != null) {
            return new ResponseEntity<>(updatedService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Service not found
        }
    }

    // Delete a service by ID
    @DeleteMapping("/{id}")
public ResponseEntity<?> deleteService(@PathVariable Long id) {
    VehicleMaintenance deletedService = serviceService.deleteService(id);
    
    if (deletedService != null) {
        return new ResponseEntity<>(deletedService, HttpStatus.OK); // Return deleted service JSON with 200 OK
    } else {
        return new ResponseEntity<>("Service not found", HttpStatus.NOT_FOUND); // 404 if service doesn't exist
    }
}

        
    // Get a service by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleMaintenance> getServiceById(@PathVariable Long id) {
        Optional<VehicleMaintenance> service = serviceService.getServiceById(id);
        if (service.isPresent()) {
            return new ResponseEntity<>(service.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Service not found
        }
    }

    // Get all services
    @GetMapping
    public ResponseEntity<List<VehicleMaintenance>> getAllServices() {
        List<VehicleMaintenance> services = serviceService.getAllServices();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @GetMapping("/service")
public List<VehicleMaintenance> findByServiceName(@RequestParam String serviceName) {
    return serviceService.findByServiceName(serviceName);
}
}

