package com.examly.springapp.model;

import jakarta.persistence.*;

@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String brand;
    private String model;
    private String serialNumber;
    private String status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User assignedTo;

    // Getters and Setters
}

