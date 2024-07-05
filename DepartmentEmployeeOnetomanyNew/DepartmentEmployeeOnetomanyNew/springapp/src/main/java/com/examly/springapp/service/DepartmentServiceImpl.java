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

    public Department addDepartment(Department department) {
        // Check if department with the same name already exists
        if (departmentRepo.existsByDepartmentName(department.getDepartmentName())) {
            throw new DuplicateDepartmentException("Department with name " + department.getDepartmentName() + " already exists!");
        }
        
        // If not exists, save the department
        return departmentRepo.save(department);
    }


    public Department getDepartmentById(int departmentId) {
        return departmentRepo.findById(departmentId).orElse(null);
    }
}
