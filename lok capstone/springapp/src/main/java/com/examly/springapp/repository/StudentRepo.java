package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Student;
import com.examly.springapp.model.User;

@Repository
public interface StudentRepo extends JpaRepository<Student, Long> {

    Student findByName(String name);   
    Student findByUser(User user);

}
