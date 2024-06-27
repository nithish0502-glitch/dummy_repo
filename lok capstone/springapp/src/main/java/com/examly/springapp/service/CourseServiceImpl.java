package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Admission;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.Institute;
import com.examly.springapp.repository.AdmissionRepo;
import com.examly.springapp.repository.CoursesRepo;
import com.examly.springapp.repository.InstituteRepo;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CoursesRepo courseRepo;

    @Autowired
    private InstituteRepo instituteRepo;

    @Autowired
    private AdmissionRepo admissionRepo;

    @Override
    public Course createCourse(long instituteId, Course course) throws RuntimeException {
        if (instituteRepo.existsById(instituteId)) {
            Institute institute = instituteRepo.findById(instituteId).get();
            Course existingCourse = courseRepo.findByCourseName(course.getCourseName());
            if (existingCourse != null) {
                if (existingCourse.getInstitute().getInstituteName().equals(institute.getInstituteName())) {
                    throw new RuntimeException("Course already exists");
                }
            }
            institute.addCourse(course);
            instituteRepo.save(institute);
            course.setInstitute(institute);
            return courseRepo.save(course);
        }
        return null;
    }

    @Override
    public Course updateCourse(Course course, long instituteId) {
        Institute institute = instituteRepo.findById(instituteId).get();
        Course existingCourse = courseRepo.findById(course.getCourseId()).get();
        institute.removeCourse(existingCourse);
        institute.addCourse(course);
        instituteRepo.save(institute);
        course.setInstitute(institute);
        return courseRepo.save(course);
    }

    @Override
    public Course deleteCourse(long courseId) {
        if (courseRepo.existsById(courseId)) {
            Course existingCourse = courseRepo.findById(courseId).get();
            Institute institute = existingCourse.getInstitute();
            List<Admission> admissionList = admissionRepo.findByCourse(existingCourse);
            for (Admission admission : admissionList) {
                admission.setStudent(null);
                admission.setCourse(null);
                admissionRepo.deleteById(admission.getAdmissionId());
            }
            institute.removeCourse(existingCourse);
            instituteRepo.save(institute);
            courseRepo.deleteById(courseId);
            return existingCourse;
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        return courseRepo.findAll();
    }

    @Override
    public List<Course> getCourseByInstituteId(long instituteId) {
        Institute institute = instituteRepo.findById(instituteId).get();
        return courseRepo.findByInstitute(institute);
    }
}
