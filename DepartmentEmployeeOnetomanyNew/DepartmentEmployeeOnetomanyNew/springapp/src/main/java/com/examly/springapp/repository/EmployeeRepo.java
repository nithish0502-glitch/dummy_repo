package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Employee;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
    @Query("SELECT e FROM Employee e ORDER BY e.salary DESC")
    List<Employee> findAllByOrderBySalaryDesc();
    
    
}
