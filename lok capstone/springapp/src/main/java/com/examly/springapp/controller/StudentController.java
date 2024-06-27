package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Student;
import com.examly.springapp.service.StudentService;

@RestController
@CrossOrigin
public class StudentController {

    @Autowired
    private StudentService studentService;

    @PostMapping("/api/student")
      @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> addStudentProfile(@RequestBody Student student) throws RuntimeException {
        Student studentFromService = studentService.createStudent(student);
        if (studentFromService != null) {
            return new ResponseEntity<>(studentFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/student")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<Student>> viewStudent() {
        List<Student> list = studentService.getAllStudent();
        if (list != null) {
            return new ResponseEntity<>(list, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/api/student/user/{userId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> viewStudent(@PathVariable long userId) {
        Student studentFromService = studentService.getStudentByUserId(userId);
        if (studentFromService != null) {
            return new ResponseEntity<>(studentFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/api/student/{studentId}")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<Student> editStudent(@RequestBody Student student, @PathVariable long studentId) {
        Student studentFromService = studentService.updateStudent(student, studentId);
        if (studentFromService != null) {
            return new ResponseEntity<>(studentFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
