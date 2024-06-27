package com.examly.springapp.service;

import java.util.*;

import com.examly.springapp.model.Admission;
import com.examly.springapp.model.User;

public interface AdmissionService {

    public Admission createAdmission(Admission admission);

    public Admission updateAdmission(Admission admission, long admissionId);

    public List<Admission> getAdmissionByUserId(long userId);

    public List<Admission> getAllAdmission();

    public List<Admission> getAdmissionByStudentId(long studentId);
    

}
