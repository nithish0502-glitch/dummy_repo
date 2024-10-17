package com.examly.springapp.service.impl;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepository;

    @Autowired
    public FeedbackServiceImpl(FeedbackRepo feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepository.save(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepository.findById(feedbackId).orElse(null);
    }

    @Override
    public Feedback updateFeedback(Long feedbackId, Feedback feedback) {
        if (feedbackRepository.existsById(feedbackId)) {
            feedback.setFeedbackId(feedbackId);
            return feedbackRepository.save(feedback);
        }
        return null;
    }

    @Override
    public void deleteFeedback(Long feedbackId) {
        feedbackRepository.deleteById(feedbackId);
    }
    @Override
    public List<Feedback> getFeedbackByUserId(Long userId) {
        return feedbackRepository.findByUserUserId(userId);
    }
}
