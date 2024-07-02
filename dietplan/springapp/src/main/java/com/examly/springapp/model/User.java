package com.examly.springapp.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long userId;

    private String username;

    private String email;

    private String password;

    private String role;

    @OneToMany(mappedBy = "user")
    private List<DietPlan> dietPlans;

    @OneToMany(mappedBy = "user")
    private List<DietPlanRequest> dietPlanRequests;

    @OneToMany(mappedBy = "user")
    private List<Feedback> feedbacks;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<DietPlan> getDietPlans() {
        return dietPlans;
    }

    public void setDietPlans(List<DietPlan> dietPlans) {
        this.dietPlans = dietPlans;
    }

    public List<DietPlanRequest> getDietPlanRequests() {
        return dietPlanRequests;
    }

    public void setDietPlanRequests(List<DietPlanRequest> dietPlanRequests) {
        this.dietPlanRequests = dietPlanRequests;
    }

    public List<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(List<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }

    
}
