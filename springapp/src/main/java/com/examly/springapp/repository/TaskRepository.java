package com.examly.springapp.repository;

import com.examly.springapp.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {
    List<Task> findByStatusAndProject_ProjectIdNotIn(String status, List<Integer> projectIds);
}
