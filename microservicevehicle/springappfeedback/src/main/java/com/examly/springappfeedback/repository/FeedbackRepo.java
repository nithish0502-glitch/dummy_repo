package com.examly.springappfeedback.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springappfeedback.model.Feedback;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long>{
    
    @Query("SELECT f FROM Feedback f WHERE f.user.userId = :userId")
    List<Feedback> findFeedbackByUserId(Long userId);
}
