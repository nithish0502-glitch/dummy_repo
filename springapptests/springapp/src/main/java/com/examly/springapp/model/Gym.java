package com.examly.springapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Entity
public class Gym {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String location;
    private String description;

    @OneToMany(mappedBy = "gym", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Membership> memberships;

    // Constructors
    public Gym() {}

    public Gym(String name, String location, String description) {
        this.name = name;
        this.location = location;
        this.description = description;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Membership> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<Membership> memberships) {
        this.memberships = memberships;
    }

    public Membership save(Gym membership) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

    public Optional<Gym> findById(Long membershipId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    public void setEndDate(LocalDate localDate) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setEndDate'");
    }

    public LocalDate getEndDate() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getEndDate'");
    }
}
