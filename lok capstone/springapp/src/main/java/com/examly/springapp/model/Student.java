package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue
    private long studentId;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String name;
    private String dob;
    private String gender;
    private String motherName;
    private String fatherName;
    private String nationality;
    private long age;
    private String address;
    private String mobile;
    private int marksSSLC;
    private int marksHSC;
    private int marksDiploma;
    private String eligibility;

    @OneToMany(mappedBy = "student")
    List<Admission> admissions = new ArrayList<>();

    @JsonIgnore
    public List<Admission> getAdmissions() {
        return admissions;
    }

    public void addAdmission(Admission admission) {
        this.admissions.add(admission);
    }

    public void removeAdmission(Admission admission) {
        this.admissions.remove(admission);
    }

}
