package com.examly.springapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    Feedback getFeedbackById(int id);
    List<Feedback> getAllFeedbacks();
    Feedback deleteFeedback(int id);
    List<Feedback> getFeedbacksByUserId(int userId);
}