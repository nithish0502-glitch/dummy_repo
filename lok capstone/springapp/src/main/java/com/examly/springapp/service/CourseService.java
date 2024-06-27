package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Course;

public interface CourseService {

    public Course createCourse(long instituteId, Course course);

    public Course updateCourse(Course course, long instituteId);

    public Course deleteCourse(long courseId);

    public List<Course> getAllCourse();

    public List<Course> getCourseByInstituteId(long instituteId);

}
