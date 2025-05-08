package com.examly.springappfeedback.service;

import java.util.List;
import java.util.Optional;

import com.examly.springappfeedback.model.Feedback;

public interface FeedbackService {
    public Feedback addFeedback(Feedback feedback, String token);
    public List<Feedback> getFeedbackByUserId(Long userId);
    public List<Feedback> getAllFeedbacks();
    public Optional<Feedback> getFeedbackById(Long id);
    public Feedback updateFeedback(Long id, Feedback  updatedFeedback);
    public Feedback deleteFeed(Long id);

    
}
