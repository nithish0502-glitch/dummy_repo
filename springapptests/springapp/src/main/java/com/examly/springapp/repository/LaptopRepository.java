package com.examly.springapp.repository;

import com.examly.springapp.model.Laptop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface LaptopRepository extends JpaRepository<Laptop, Long> {

    List<Laptop> findByUserDepartment(String department);

 }

