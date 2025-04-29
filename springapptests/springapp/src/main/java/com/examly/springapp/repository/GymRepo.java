package com.examly.springapp.repository;

import com.examly.springapp.model.Gym;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface GymRepo extends JpaRepository<Gym, Long> {
    Optional<Gym> findByNameAndLocation(String name, String location);
}
