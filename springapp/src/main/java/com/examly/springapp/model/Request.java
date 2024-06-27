package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int requestId;

    @ManyToOne
    @JoinColumn(name = "agroChemicalId")
    private AgroChemical agroChemical;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "cropId", nullable = false)
    private Crop crop;

    private int quantity;
    private String status;
    private LocalDateTime requestDate;

}
