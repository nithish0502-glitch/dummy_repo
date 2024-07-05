package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Course;
import com.examly.springapp.model.University;
import com.examly.springapp.repository.CourseRepo;
import com.examly.springapp.repository.UniversityRepo;

@Service
public class CourseServiceImpl {

    @Autowired
    private CourseRepo courseRepo;

    @Autowired
    private UniversityRepo universityRepo;

    public Course addCourse(int universityId,Course course)
    {
        University university = universityRepo.findById(universityId)
        .orElseThrow(()->new IllegalArgumentException("Invalid university Id: " + universityId));
        course.setUniversity(university);
        return courseRepo.save(course);
    }


    public List<Course> getCourseslessthanfees(double fee)
    {
        return courseRepo.getCourseslessthanfees(fee);
    }
    
public boolean deleteCoureById(int courseId)
{
   if(courseRepo.existsById(courseId))
   {
    courseRepo.deleteById(courseId);
    return true;
   }

   else
   {
    return false;
   }
}

}
