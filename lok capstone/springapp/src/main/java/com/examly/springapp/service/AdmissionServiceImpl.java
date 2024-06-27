package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exception.SeatsNotAvailableException;
import com.examly.springapp.model.Admission;
import com.examly.springapp.model.Course;
import com.examly.springapp.model.Student;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.AdmissionRepo;
import com.examly.springapp.repository.CoursesRepo; 
import com.examly.springapp.repository.StudentRepo;
import com.examly.springapp.repository.UserRepo;

@Service      
public class AdmissionServiceImpl implements AdmissionService {

    @Autowired
    private AdmissionRepo admissionRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CoursesRepo coursesRepo;

    @Override
    public Admission createAdmission(Admission admission) throws RuntimeException {
        List<Admission> existingAdmissions = admissionRepo.findByStudent(admission.getStudent());
        if(existingAdmissions != null){
            for (Admission ad : existingAdmissions) {
            if (ad.getStudent().getName().equals(admission.getStudent().getName())
                    && ad.getCourse().getCourseName().equals(admission.getCourse().getCourseName())
                    && ad.getCourse().getInstitute().getInstituteName().equals(admission.getCourse().getInstitute().getInstituteName())) {
                throw new RuntimeException("Admission already exists");
            }
        }
    }
        return admissionRepo.save(admission);
    }

    @Override
    public List<Admission> getAdmissionByUserId(long userId) {
        User user = userRepo.findById(userId).get();
        Student student = studentRepo.findByUser(user);
        return admissionRepo.findByStudent(student);
    }

    @Override
    public List<Admission> getAdmissionByStudentId(long studentId) {
        Student student = studentRepo.findById(studentId).get();
        return admissionRepo.findByStudent(student);
    }

    @Override
    public List<Admission> getAllAdmission() {
        return admissionRepo.findAll();
    }

    @Override
    public Admission updateAdmission(Admission admission, long admissionId) {
        if (admission.getStatus().equals("Accepted")) {
            Course course = admission.getCourse();
            if (course.getNoOfSeats() > 0) {
                course.setNoOfSeats(course.getNoOfSeats() - 1);
                coursesRepo.save(course);
                admission.setAdmissionId(admissionId);
                admission.setCourse(course);
                return admissionRepo.save(admission);
            } else {
                admission.setStatus("Not Applicable");
                admission.setAdmissionId(admissionId);
                admissionRepo.save(admission);
                throw new SeatsNotAvailableException("Seats Not Available");
            }
        } else {
            admission.setAdmissionId(admissionId);
            return admissionRepo.save(admission);
        }
    }


}
