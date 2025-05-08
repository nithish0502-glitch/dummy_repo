package com.examly.springappfeedback.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springappfeedback.model.User;
import com.examly.springappfeedback.config.JwtValidationService;
import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.repository.FeedbackRepo;
import com.examly.springappfeedback.repository.UserRepo;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private  FeedbackRepo feedbackrepo;

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtValidationService jwtValidationService;

    public Feedback addFeedback(Feedback feedback, String token) {
        try {
            if (token == null || !jwtValidationService.validateToken(token)) {
                System.out.println("Invalid token");
                return null;  // Token is invalid
            }
            
            User user = jwtValidationService.getUserById(feedback.getUser().getUserId());
            System.out.println("++++++++++++++++++++"+user);  
            userRepo.save(user); 
            if (user != null) {
                feedback.setUser(user); 
                System.out.println("Saved feedback: " + feedback.getFeedbackText());
                Feedback feedbacknew = feedbackrepo.save(feedback);
                return feedbacknew;
            } else {
                return null; // User not found
            }
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

    // public Feedback addFeedback(Feedback feedback,String token) {
    //     try {
    //         Optional<User> userOptional = userRepo.findById(feedback.getUser().getUserId());
    //         if (userOptional.isPresent()) {
    //             feedback.setUser(userOptional.get());
    //             return feedbackrepo.save(feedback);
    //         } else {
    //             return null; // User not found
    //         }
    //     } catch (Exception e) {
    //         System.out.println(e);
    //         return null;
    //     }
    // }

    public List<Feedback> getFeedbackByUserId(Long userId) {
        return feedbackrepo.findFeedbackByUserId(userId);
    }


    public List<Feedback> getAllFeedbacks() {
        return feedbackrepo.findAll();
    }

    public Optional<Feedback> getFeedbackById(Long id) {
        return feedbackrepo.findById(id);
    }
    public Feedback updateFeedback(Long id, Feedback  updatedFeedback) {
        Optional<Feedback> feedbackOptional = feedbackrepo.findById(id);
        if (feedbackOptional.isPresent()) {
            updatedFeedback.setFeedbackId(id); // Ensure the ID is set correctly
            return feedbackrepo.save(updatedFeedback);
        } else {
            return null; // Feedback not found
        }
    }

    public Feedback deleteFeed(Long id) {
       Optional<Feedback> feedbackoptional = feedbackrepo.findById(id);
        if (feedbackoptional.isPresent()) {
            Feedback deletefeedback = feedbackoptional.get();
            feedbackrepo.deleteById(id);
            return deletefeedback; // Feedback deleted successfully
        } else {
            return null; // Feedback not found
        }
    }

    
}
