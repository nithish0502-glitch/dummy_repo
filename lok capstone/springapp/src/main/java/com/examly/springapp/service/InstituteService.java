package com.examly.springapp.service;

import java.util.List;
import com.examly.springapp.model.Institute;

public interface InstituteService {

    public Institute createInstitute(Institute institute);

    public Institute updateInstitute(Institute institute, long instituteId);

    public Institute deleteInstitute(long instituteId);

    public List<Institute> getAllInstitute();

}
