package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Employee;
import com.examly.springapp.service.EmployeeServiceImpl;

@RestController
public class EmployeeController {


    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("employee/{departmentId}/department")
    public ResponseEntity<Employee> addEmployee(@PathVariable int departmentId,@RequestBody Employee employee)
    {
    
    Employee savedEmployee = employeeServiceImpl.addEmployee(departmentId, employee);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedEmployee);
}
    
@GetMapping("/employee")
public ResponseEntity<List<Employee>> getAllEmployees() {
    List<Employee> employees = employeeServiceImpl.getAllEmployees();
    if (employees != null && !employees.isEmpty()) {
        return ResponseEntity.ok(employees);
    } else {
        return ResponseEntity.notFound().build();
    }
}


    @DeleteMapping("employee/{employeeId}")
    public String deleteEmployeeById(@PathVariable int employeeId)
    {
    boolean deletedEmployee =employeeServiceImpl.deleteEmployeeById(employeeId);
 if(deletedEmployee)
 {
    return "Employee "+employeeId+ " deleted successfully";
 }
 else
 {
    return "Employee "+employeeId+ " not found";
 }
    }
}
    
    

