package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Admission;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.Student;

@Repository
public interface AdmissionRepo extends JpaRepository<Admission, Long> {

    List<Admission> findByStudent(Student student);
    List<Admission> findByCourse(Course course);
    
}
