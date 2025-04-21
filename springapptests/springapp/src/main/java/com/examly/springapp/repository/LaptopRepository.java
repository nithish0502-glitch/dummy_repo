package com.examly.springapp.repository;

import com.examly.springapp.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    @Query("SELECT l FROM Laptop l WHERE l.assignedTo.department = ?1")
    List<Laptop> findByDepartment(String department);
}

