package com.examly.springapp.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class AgroChemical {
     public int getAgroChemicalId() {
        return agroChemicalId;
    }

    public void setAgroChemicalId(int agroChemicalId) {
        this.agroChemicalId = agroChemicalId;
    }

    public AgroChemical() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


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
    @JsonIgnore
    private List<Request> requests = new ArrayList<>();

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

}
