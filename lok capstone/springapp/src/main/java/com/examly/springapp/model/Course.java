package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue
    long courseId;

    String courseName;
    String courseDescription;
    String courseDuration;
    double fees;
    int noOfSeats;

    @ManyToOne
    @JoinColumn(name = "institute_id")
    private Institute institute;

}
