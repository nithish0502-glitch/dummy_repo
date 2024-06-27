package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.examly.springapp.model.Admission;
import com.examly.springapp.service.AdmissionServiceImpl;

@RestController
public class AdmissionController {

  @Autowired
  private AdmissionServiceImpl admissionService;

  @PostMapping("/api/admission")
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<Admission> createAdmission(@RequestBody Admission admission) throws RuntimeException{
    Admission admissionFromService = admissionService.createAdmission(admission);
    if (admissionFromService != null) {
      return new ResponseEntity<>(admissionFromService, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/api/admission")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<List<Admission>> allAdmissions() {
    List<Admission> admissionListFromService = admissionService.getAllAdmission();
    if (admissionListFromService != null) {
      return new ResponseEntity<>(admissionListFromService, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PutMapping("/api/admission/{admissionId}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ResponseEntity<Admission> updateAdmission(@RequestBody Admission admission, @PathVariable long admissionId) {
    Admission admissionFromService = admissionService.updateAdmission(admission, admissionId);
    if (admissionFromService != null) {
      return new ResponseEntity<>(admissionFromService, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/api/admission/user/{userId}")
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<List<Admission>> admissionUserId(@PathVariable long userId) {
    List<Admission> admissionListFromService = admissionService.getAdmissionByUserId(userId);
    if (admissionListFromService != null) {
      return new ResponseEntity<>(admissionListFromService, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("/api/admission/student/{studentId}")
  @PreAuthorize("hasAuthority('STUDENT')")
  public ResponseEntity<List<Admission>> admissionStudentId(@PathVariable long studentId) {
    List<Admission> admissionListFromService = admissionService.getAdmissionByStudentId(studentId);
    if (admissionListFromService != null) {
      return new ResponseEntity<>(admissionListFromService, HttpStatus.CREATED);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
