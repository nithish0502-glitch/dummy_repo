package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException.InternalServerError;

import com.examly.springapp.exception.DuplicateDepartmentException;
import com.examly.springapp.model.Department;
import com.examly.springapp.service.DepartmentServiceImpl;

@RestController
public class DepartmentController {


    @Autowired
    private DepartmentServiceImpl departmentServiceImpl;


    @PostMapping("department")
    public ResponseEntity<?> addDepartment(@RequestBody Department department)
    {
        try {
            Department newDepartment = departmentServiceImpl.addDepartment(department);
            return ResponseEntity.status(HttpStatus.CREATED).body(newDepartment);
        } catch (DuplicateDepartmentException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage()); // Conflict status for duplicate entry
        } catch (Exception e) {
            // Handle other exceptions if needed
            System.out.println("Unexpected error occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
        
    }


    @GetMapping("department/{departmentId}")
    public ResponseEntity<Department> getDepartment(@PathVariable int departmentId)
    {
        Department department = departmentServiceImpl.getDepartmentById(departmentId);
        if (department != null) {
            return ResponseEntity.status(HttpStatus.OK).body(department);
        } else {
            return ResponseEntity.notFound().build();
        }

    }

  

    
}
