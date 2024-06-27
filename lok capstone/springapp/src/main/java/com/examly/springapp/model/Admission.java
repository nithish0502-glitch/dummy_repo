package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter; 
import lombok.NoArgsConstructor;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Admission {

    @Id
    @GeneratedValue
    private long admissionId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    private String status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

}