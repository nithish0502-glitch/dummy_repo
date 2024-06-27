package com.examly.springapp.model;

import java.util.ArrayList;

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
    private int userId;

    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole; // Farmer or Seller

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private ArrayList<Crop> crops;

    @OneToMany(mappedBy = "user")
    
    private ArrayList<Request> requests;

    @OneToMany(mappedBy = "user")
    private ArrayList<Feedback> feedbacks;
}
