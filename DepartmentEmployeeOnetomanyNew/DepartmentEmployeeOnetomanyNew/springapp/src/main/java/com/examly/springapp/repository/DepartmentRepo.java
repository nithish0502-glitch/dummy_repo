package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Department;

@Repository
public interface DepartmentRepo extends JpaRepository<Department,Integer> {
    boolean existsByDepartmentName(String departmentName);
}
