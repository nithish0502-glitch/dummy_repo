package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Course;
import com.examly.springapp.service.CourseService;

@RestController
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/api/institute/{instituteId}/course")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Course> createCourse(@PathVariable long instituteId, @RequestBody Course course) throws RuntimeException {
        Course courseFromService = courseService.createCourse(instituteId, course);
        if (courseFromService != null)
            return new ResponseEntity<>(courseFromService, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/course")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courseListFromService = courseService.getAllCourse();
        if (courseListFromService != null)
            return new ResponseEntity<>(courseListFromService, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @GetMapping("/api/institute/{instituteId}/course")
    @PreAuthorize("hasAuthority('STUDENT')")
    public ResponseEntity<List<Course>> getCourseByInstituteId(@PathVariable long instituteId) {
        List<Course> courseListFromService = courseService.getCourseByInstituteId(instituteId);
        if (courseListFromService != null)
            return new ResponseEntity<>(courseListFromService, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PutMapping("/api/institute/course/{instituteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Course> updateCourse(@PathVariable long instituteId, @RequestBody Course course) {
        Course courseFromService = courseService.updateCourse(course, instituteId);
        if (courseFromService != null)
            return new ResponseEntity<>(courseFromService, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @DeleteMapping("/api/institute/course/{courseId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Course> deleteCourse(@PathVariable long courseId) {
        Course courseFromService = courseService.deleteCourse(courseId);
        if (courseFromService != null)
            return new ResponseEntity<>(courseFromService, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
