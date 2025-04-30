package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String memberName;
    private LocalDate startDate;
    private LocalDate endDate;
    private String type;

    @ManyToOne
    @JoinColumn(name = "gym_id")
    @JsonIgnore
    private Gym gym;

    // Constructors
    public Membership() {}

    public Membership(String memberName, LocalDate startDate, LocalDate endDate, String type) {
        this.memberName = memberName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Gym getGym() {
        return gym;
    }

    public void setGym(Gym gym) {
        this.gym = gym;
    }
}
