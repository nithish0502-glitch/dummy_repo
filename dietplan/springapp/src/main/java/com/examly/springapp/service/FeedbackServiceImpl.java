package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        return feedbackRepo.save(feedback);
    }

    @Override
    public Optional<Feedback> getFeedbackById(int feedbackId) {
        return feedbackRepo.findById(feedbackId);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

    @Override
    public Feedback updateFeedback(int feedbackId, Feedback updatedFeedback) {
        Optional<Feedback> feedbackOptional = feedbackRepo.findById(feedbackId);
        if (feedbackOptional.isPresent()) {
            Feedback feedback = feedbackOptional.get();
            feedback.setContent(updatedFeedback.getContent());
            feedback.setCreatedAt(updatedFeedback.getCreatedAt());
            // You may update other fields as needed
            return feedbackRepo.save(feedback);
        } else {
            throw new RuntimeException("Feedback not found with id: " + feedbackId);
        }
    }

    @Override
    public Feedback deleteFeedback(int feedbackId) {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        feedbackRepo.deleteById(feedbackId);
        return feedback;
    }
   
}
