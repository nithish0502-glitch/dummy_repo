package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Department;
import com.examly.springapp.model.Employee;
import com.examly.springapp.repository.DepartmentRepo;
import com.examly.springapp.repository.EmployeeRepo;

@Service
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private DepartmentRepo departmentRepo;

    public Employee addEmployee(int departmentId,Employee employee)
   {
            // Check if the department exists and retrieve it
            Department department = departmentRepo.findById(departmentId)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid department Id: " + departmentId));
        
            // Set the department for the employee
            employee.setDepartment(department);
        
            // Save the employee
            return employeeRepo.save(employee);
 }

public List<Employee> getAllEmployees() {
        return employeeRepo.findAllByOrderBySalaryDesc();
    }
        //delete Employee by ID
        public boolean deleteEmployeeById(int employeeId)
        {
            if(employeeRepo.existsById(employeeId))
            {
                employeeRepo.deleteById(employeeId);
                return true;
            }
            else
            {
                return false;
            }
        }


    }


    
    

