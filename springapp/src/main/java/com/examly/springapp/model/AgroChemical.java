package com.examly.springapp.model;

import java.math.BigDecimal;
import java.util.ArrayList;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AgroChemical {
     @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int agroChemicalId;

    private String name;
    private String brand;
    private String category;
    private String description;
    private String unit;
    private BigDecimal pricePerUnit;
    private String image;

    @OneToMany(mappedBy = "agroChemical")
    private ArrayList<Request> requests;
}
