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
    FeedbackRepo feedbackRepo;

    @Autowired
    UserRepo userRepo;
    @Override
    public Feedback createFeedback(Feedback feedback) {
        User user= userRepo.findById(feedback.getUser().getUserId()).orElse(null);
        feedback.setUser(user);
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(int id) {
        Optional<Feedback> optionalFeedback = feedbackRepo.findById(id);
        return optionalFeedback.orElse(null);
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        return feedbackRepo.findAll();
    }

  
    @Override
    public boolean deleteFeedback(int id) {
        Optional<Feedback> optionalFeedback = feedbackRepo.findById(id);
        if (optionalFeedback.isPresent()) {
            feedbackRepo.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(int userId) {
        return feedbackRepo.findByUser(userRepo.findById(userId).orElse(null));
    }
}
