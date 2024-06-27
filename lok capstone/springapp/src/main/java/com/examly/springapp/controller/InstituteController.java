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

import com.examly.springapp.model.Institute;
import com.examly.springapp.service.InstituteServiceImpl;

@RestController
public class InstituteController {

    @Autowired
    private InstituteServiceImpl instituteService;

    @PostMapping("/api/institute")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Institute> addInstitutes(@RequestBody Institute institute) throws RuntimeException {
        Institute instituteFromService = instituteService.createInstitute(institute);
        if (instituteFromService != null) {
            return new ResponseEntity<>(instituteFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/api/institute")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<Institute>> viewInstitutes() {
        List<Institute> institutes = instituteService.getAllInstitute();
        if (institutes != null) {
            return new ResponseEntity<>(institutes, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/api/institute/{instituteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Institute> editInstitutes(@RequestBody Institute institute, @PathVariable long instituteId) {
        Institute instituteFromService = instituteService.updateInstitute(institute, instituteId);
        if (instituteFromService != null) {
            return new ResponseEntity<>(instituteFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/api/institute/{instituteId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Institute> deleteInstitutes(@PathVariable long instituteId) {
        Institute instituteFromService = instituteService.deleteInstitute(instituteId);
        if (instituteFromService != null) {
            return new ResponseEntity<>(instituteFromService, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
