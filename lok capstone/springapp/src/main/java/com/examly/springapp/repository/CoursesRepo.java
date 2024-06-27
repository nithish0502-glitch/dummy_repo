package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Course;
import com.examly.springapp.model.Institute;

@Repository
public interface CoursesRepo extends JpaRepository<Course, Long> {

   Course findByCourseName(String courseName);
   List<Course> findByInstitute(Institute institute);

}
