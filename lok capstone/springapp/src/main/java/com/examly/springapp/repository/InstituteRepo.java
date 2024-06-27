package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Institute;

@Repository
public interface InstituteRepo extends JpaRepository<Institute, Long> {

    Institute findByInstituteName(String instituteName);

}