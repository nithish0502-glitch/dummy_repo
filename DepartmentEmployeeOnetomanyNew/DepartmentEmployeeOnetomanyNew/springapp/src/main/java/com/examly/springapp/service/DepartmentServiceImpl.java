package com.examly.springapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.DuplicateDepartmentException;
import com.examly.springapp.model.Department;
import com.examly.springapp.repository.DepartmentRepo;

@Service
public class DepartmentServiceImpl {
    

    @Autowired
    private DepartmentRepo departmentRepo;

    public Department addDepartment(Department department)
    { 
        try {
            if (departmentRepo.existsByDepartmentName(department.getDepartmentName())) {
                throw new DuplicateDepartmentException("Department with name " + department.getDepartmentName() + " already exists!");
            } 
            return departmentRepo.save(department);
        } catch (Exception e) {
            // Log the exception (using a logger is preferred)
            e.printStackTrace();
            return null;
        } 
    }


    public Department getDepartmentById(int departmentId) {
        return departmentRepo.findById(departmentId).orElse(null);
    }
}
