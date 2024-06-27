package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Student;

public interface StudentService {

    public Student createStudent(Student student);

    public List<Student> getAllStudent();

    public Student getStudentByUserId(long userId);

    public Student updateStudent(Student student, long studentId);
    
}
