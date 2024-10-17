package com.examly.springapp.service;

import com.examly.springapp.model.Feedback;

import java.util.List;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);
    List<Feedback> getAllFeedback();
    Feedback getFeedbackById(Long feedbackId);
    Feedback updateFeedback(Long feedbackId, Feedback feedback);
    void deleteFeedback(Long feedbackId);

    List<Feedback> getFeedbackByUserId(Long userId);
}
