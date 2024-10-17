package com.examly.springapp.service;

import com.examly.springapp.model.Pet;

import java.time.LocalDate;
import java.util.List;

public interface PetService {
    Pet createPet(Pet pet);
    Pet getPetById(Long id);
    List<Pet> getAllPets();
    List<Pet> getPetsByName(String name);
    List<Pet> getPetsBySpecies(String species);
    List<Pet> getPetsByBreed(String breed);
    List<Pet> getPetsByStatus(String status);
    Pet updatePet(Long id, Pet pet);
    void deletePet(Long id);
    List<Pet> getPetsByUserId(int userId);
}
