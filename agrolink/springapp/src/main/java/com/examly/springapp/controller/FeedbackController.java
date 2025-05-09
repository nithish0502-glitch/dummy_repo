package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import org.springframework.security.access.prepost.PreAuthorize;
@RestController
public class FeedbackController {

    @Autowired
    FeedbackService feedbackService;

    @PostMapping("/api/feedback")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return new ResponseEntity<>(createdFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/api/feedback/{id}")
    @PreAuthorize("hasAnyAuthority('SELLER', 'FARMER')")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable("id") int id) {
        Feedback feedback = feedbackService.getFeedbackById(id);
        if (feedback != null) {
            return new ResponseEntity<>(feedback, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
  
    // Get all feedbacks
    @GetMapping("/api/feedback")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        if(feedbacks != null) {
            return new ResponseEntity<>(feedbacks, HttpStatus.OK);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/api/feedback/user/{userId}")
    @PreAuthorize("hasAuthority('SELLER')")
    public ResponseEntity<List<Feedback>> getFeedbacksByUserId(@PathVariable int userId) {
        List<Feedback> feedbacks = feedbackService.getFeedbacksByUserId(userId);
        return new ResponseEntity<>(feedbacks, HttpStatus.OK);
    }

    // Delete a feedback
    @DeleteMapping("/api/feedback/{id}")
    @PreAuthorize("hasAuthority('FARMER')")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable int id) {
        Feedback deleted = feedbackService.deleteFeedback(id);
        return new ResponseEntity<>(deleted, HttpStatus.OK);
    }
}
