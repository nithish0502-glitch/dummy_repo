package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import org.springframework.security.access.prepost.PreAuthorize;
@RestController
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @PostMapping("/api/feedback")
    public ResponseEntity<Feedback> createFeedback(@RequestBody Feedback feedback) {
        Feedback createdFeedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFeedback);
    }

    @GetMapping("/api/feedback/{id}")
    public ResponseEntity<Feedback> getFeedbackById(@PathVariable int id) {
        Optional<Feedback> feedback = feedbackService.getFeedbackById(id);
        return feedback.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/api/feedback")
    public ResponseEntity<List<Feedback>> getAllFeedbacks() {
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        return ResponseEntity.ok(feedbacks);
    }

    @PutMapping("/api/feedback/{id}")
    public ResponseEntity<Feedback> updateFeedback(@PathVariable int id, @RequestBody Feedback updatedFeedback) {
        Feedback updated = feedbackService.updateFeedback(id, updatedFeedback);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/api/feedback/{id}")
    public ResponseEntity<Feedback> deleteFeedback(@PathVariable int id) {
        Feedback feedback=feedbackService.deleteFeedback(id);
        return ResponseEntity.ok(feedback); 
    }
   
}
