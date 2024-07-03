package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    Feedback createFeedback(Feedback feedback);

    Optional<Feedback> getFeedbackById(int feedbackId);

    List<Feedback> getAllFeedbacks();

    Feedback updateFeedback(int feedbackId, Feedback updatedFeedback);

    Feedback deleteFeedback(int feedbackId);
}