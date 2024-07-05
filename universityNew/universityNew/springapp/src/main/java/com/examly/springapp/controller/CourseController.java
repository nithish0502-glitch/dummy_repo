package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Course;
import com.examly.springapp.service.CourseServiceImpl;

@RestController
public class CourseController {

    @Autowired
    private CourseServiceImpl courseServiceImpl;

    @PostMapping("course/{universityId}/university")
    public ResponseEntity<Course> addCourse(@PathVariable int universityId,@RequestBody Course course)
    {
        Course newCourse = courseServiceImpl.addCourse(universityId,course);
        if(newCourse!=null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(newCourse);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("course/findtheCourseGreaterthan/{fee}")
    public ResponseEntity <List<Course>> getCourseslessthanfees(@PathVariable double fee )
    {
     
        List<Course> course =courseServiceImpl.getCourseslessthanfees(fee);
        if(!course.isEmpty())
        {
            return ResponseEntity.status(HttpStatus.OK).body(course);
        }
        else
        {
            return ResponseEntity.notFound().build();

        }
    }


    @DeleteMapping("course/{courseId}")
    public ResponseEntity<String> deleteCoureById(@PathVariable int courseId)
    {
        boolean deleteCourse = courseServiceImpl.deleteCoureById(courseId);
        if (deleteCourse) {
            return ResponseEntity.status(HttpStatus.OK).body("Course " + courseId + " deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Course " + courseId + " not found");
        }
    }


    


    
}
