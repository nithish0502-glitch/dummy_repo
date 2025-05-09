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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    private String content;
    private LocalDateTime createdAt;
    public int getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }
    public Feedback() {
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
   
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
   
}
