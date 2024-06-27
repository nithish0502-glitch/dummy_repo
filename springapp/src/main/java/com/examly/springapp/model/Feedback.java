package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Feedback {
      @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    private String feedbackText;
    private LocalDateTime date;
}
