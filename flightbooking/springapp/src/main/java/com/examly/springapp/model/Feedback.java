package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId; // Unique identifier for each feedback

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user; // User who provided the feedback
    
    @ManyToOne
    @JoinColumn(name = "appointmentId")
    private Appointment appointment; // Associated appointment

    private String message; // Feedback message
    private int rating; // Feedback rating (e.g., out of 5)

    // Constructors, getters, and setters
    public Feedback() {
    }

    public Feedback(User user, Appointment appointment, String message, int rating) {
        this.user = user;
        this.appointment = appointment;
        this.message = message;
        this.rating = rating;
    }

    public Long getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(Long feedbackId) {
        this.feedbackId = feedbackId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
