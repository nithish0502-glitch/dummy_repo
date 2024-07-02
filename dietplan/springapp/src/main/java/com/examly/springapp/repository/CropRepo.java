package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Crop;
import com.examly.springapp.model.User;

@Repository
public interface CropRepo extends JpaRepository<Crop, Integer> {
    List<Crop> findByUser(User user);
}
