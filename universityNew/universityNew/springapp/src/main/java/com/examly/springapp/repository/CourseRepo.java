package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course,Integer> {

@Query("SELECT c FROM Course c WHERE c.fees >= ?1")
List<Course> getCourseslessthanfees(double fees);
    
}
