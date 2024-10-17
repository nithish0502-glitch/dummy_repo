package com.examly.springapp.service;

import com.examly.springapp.model.Pet;
import com.examly.springapp.repository.PetRepo;
import com.examly.springapp.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.examly.springapp.model.User;
import java.time.LocalDate; 
import java.util.List;
import java.util.Optional;
import com.examly.springapp.repository.UserRepo;

@Service
public class PetServiceImpl implements PetService {

    @Autowired
    private PetRepo petRepo;

    @Autowired
    private UserRepo userRepo;


    @Override
    public Pet createPet(Pet pet) {
        User user=userRepo.findById(pet.getUser().getUserId()).orElse(null);
        userRepo.save(user);
        pet.setUser(user);
        return petRepo.save(pet);
    }
 
    @Override
    public Pet getPetById(Long id) {
        Optional<Pet> pet = petRepo.findById(id);
        return pet.orElse(null);
    }

    @Override
    public List<Pet> getPetsByUserId(int userId) {
        return petRepo.findByUser_UserId(userId);
    }

    @Override
    public List<Pet> getAllPets() {
        return petRepo.findAll();
    }

    @Override
    public List<Pet> getPetsByName(String name) {
        return petRepo.findByName(name);
    }

    @Override
    public List<Pet> getPetsBySpecies(String species) {
        return petRepo.findBySpecies(species);
    }

    @Override
    public List<Pet> getPetsByBreed(String breed) {
        return petRepo.findByBreed(breed);
    }

    @Override
    public List<Pet> getPetsByStatus(String status) {
        return petRepo.findByStatus(status);
    }


    @Override
    public Pet updatePet(Long id, Pet pet) {
        if (petRepo.existsById(id)) {
            pet.setPetId(id);
            return petRepo.save(pet);
        }
        return null;
    }

    @Override
    public void deletePet(Long id) {
        petRepo.deleteById(id);
    }
}
