package com.examly.springapp.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.Student;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.StudentRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    public Student createStudent(Student student) throws RuntimeException {
        Student existingStudent = studentRepo.findByName(student.getName());
        if (existingStudent != null) {
            throw new RuntimeException("Student already exists");
        }
        return studentRepo.save(student);
    }

    @Override
    public List<Student> getAllStudent() {
        return studentRepo.findAll();
    }

    @Override
    public Student getStudentByUserId(long userId) {
        User user = userRepo.findById(userId).get();
        return studentRepo.findByUser(user);
    }

    @Override
    public Student updateStudent(Student student, long studentId) {
        if (studentRepo.existsById(studentId)) {
            student.setStudentId(studentId);
            return studentRepo.save(student);
        }
        return null;
    }
}
