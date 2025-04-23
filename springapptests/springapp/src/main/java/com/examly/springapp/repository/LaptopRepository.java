package com.examly.springapp.repository;

import com.examly.springapp.model.Laptop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LaptopRepository extends JpaRepository<Laptop, Long> {
    @Query
    List<Laptop> findByUserDepartment(String department);

 }

