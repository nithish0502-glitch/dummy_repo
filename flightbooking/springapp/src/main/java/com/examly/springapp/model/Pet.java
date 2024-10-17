package com.examly.springapp.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.Set;


@Entity
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long petId;

    private String name;
    private String species;
    private String breed;
    private LocalDate dateOfBirth;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    private String status;

    // Constructors, getters, and setters
    public Pet() {
    }

    public Pet(String name, String species, String breed, LocalDate dateOfBirth, User user, String status) {
        this.name = name;
        this.species = species;
        this.breed = breed;
        this.dateOfBirth = dateOfBirth;
        this.user = user;
        this.status = status;
    }

    // Getters and setters
    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
