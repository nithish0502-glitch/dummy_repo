package com.examly.springappfeedback.controller;

import org.springframework.http.HttpHeaders;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springappfeedback.model.Feedback;
import com.examly.springappfeedback.service.FeedbackServiceImpl;


@RestController
public class FeedbackController {

    @Autowired
    private FeedbackServiceImpl feedbackServiceImpl;

 @PostMapping("/api/feedback")
 public ResponseEntity<Feedback>  addFeed(
    @RequestHeader(HttpHeaders.AUTHORIZATION) String authorizationHeader,
    		@RequestBody Feedback feedback) {
        String token = authorizationHeader != null ? authorizationHeader.replace("Bearer ", "") : null;
      
    Feedback newFeed = feedbackServiceImpl.addFeedback(feedback,token); 
    if(newFeed!=null)
    {
        return ResponseEntity.status(HttpStatus.CREATED).body(newFeed);
    }
    else
    {  
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
 }

 @GetMapping("/api/feedback")
 public ResponseEntity<List<Feedback>> getAllFeedbacks()
 {
    List<Feedback> allFeedbacks= feedbackServiceImpl.getAllFeedbacks();
    return ResponseEntity.status(HttpStatus.OK).body(allFeedbacks);
 }


 @GetMapping("/api/feedback/{feedbackId}")
 public ResponseEntity<Feedback> getFeedsById(@PathVariable Long feedbackId) {
     Optional<Feedback> feedOptional = feedbackServiceImpl.getFeedbackById(feedbackId);
     if (feedOptional.isPresent()) {
         return ResponseEntity.status(HttpStatus.OK).body(feedOptional.get());
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
 }

 @GetMapping("/api/feedback/user/{userId}")
 public ResponseEntity<List<Feedback>> getFeedsByUserId(@PathVariable Long userId) {
     List<Feedback> feedbackList = feedbackServiceImpl.getFeedbackByUserId(userId);
     if (!feedbackList.isEmpty()) {
         return ResponseEntity.status(HttpStatus.OK).body(feedbackList);
     } else {
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
 }


 @DeleteMapping("/api/feedback/{feedbackId}")
 public ResponseEntity<Feedback> deleteFeed(@PathVariable Long feedbackId)
 {
   Feedback  deletefeedback =feedbackServiceImpl.deleteFeed(feedbackId);
    if (deletefeedback!=null) {
        return  ResponseEntity.status(HttpStatus.OK).body(deletefeedback);
    } else {
        return ResponseEntity.notFound().build();
    }  
 }

    @PutMapping("/api/feedback/{feedbackId}")
    public ResponseEntity<Feedback> updateFeed(@PathVariable Long feedbackId,@RequestBody Feedback feedback)
    {
        Feedback updateFeed = feedbackServiceImpl.updateFeedback(feedbackId, feedback);
        if (updateFeed != null) {
            return ResponseEntity.ok(updateFeed);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    
}
