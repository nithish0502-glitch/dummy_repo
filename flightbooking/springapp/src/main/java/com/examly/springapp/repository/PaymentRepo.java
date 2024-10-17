package com.examly.springapp.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Pet;

@Repository
public interface PaymentRepo extends JpaRepository<Pet, Long> {
    List<Pet> findByUser_UserId(int userId);

    List<Pet> findByName(String name);
    
    List<Pet> findBySpecies(String species);
    
    List<Pet> findByBreed(String breed);
    
    List<Pet> findByStatus(String status);
    
}
