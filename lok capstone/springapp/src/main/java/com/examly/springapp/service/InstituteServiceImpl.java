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
public class InstituteServiceImpl implements InstituteService {

    @Autowired
    private InstituteRepo instituteRepo;

    @Autowired
    private CoursesRepo coursesRepo;

    @Autowired
    private AdmissionRepo admissionRepo;

    @Override
    public Institute createInstitute(Institute institute) throws RuntimeException {
        Institute existingInstitute = instituteRepo.findByInstituteName(institute.getInstituteName());
        if (existingInstitute != null) {
            throw new RuntimeException("Institute already exists");
        }
        return instituteRepo.save(institute);
    }

    @Override
    public Institute updateInstitute(Institute institute, long instituteId) {
        if (instituteRepo.existsById(instituteId)) {
            institute.setInstituteId(instituteId);
            if (institute.getCourses().size() > 0) {
                for (Course course : institute.getCourses()) {
                    course.setInstitute(institute);
                    coursesRepo.save(course);

                }
            }
            return instituteRepo.save(institute);
        }
        return null;
    }

    @Override
    public Institute deleteInstitute(long instituteId) {
        if (instituteRepo.existsById(instituteId)) {
            Institute institute = instituteRepo.findById(instituteId).get();
            List<Course> courses = institute.getCourses();
            if (courses.size() > 0) {
                for (Course course : courses) {
                    List<Admission> admissionList = admissionRepo.findByCourse(course);
                    for (Admission admission : admissionList) {
                        admission.setStudent(null);
                        admission.setCourse(null);
                        admissionRepo.deleteById(admission.getAdmissionId());
                    }
                }
            }
            if (courses.size() > 0) {
                for (Course course : courses) {
                    course.setInstitute(null);
                    coursesRepo.save(course);
                    coursesRepo.deleteById(course.getCourseId());
                }
            }
            instituteRepo.deleteById(instituteId);
            return institute;
        }
        return null;
    }

    @Override
    public List<Institute> getAllInstitute() {
        return instituteRepo.findAll();
    }

}
