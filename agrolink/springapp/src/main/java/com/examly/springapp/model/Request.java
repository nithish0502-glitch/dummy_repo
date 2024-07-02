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
    public int getRequestId() {
        return requestId;
    }
    public Request() {
    }
    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }
    public AgroChemical getAgroChemical() {
        return agroChemical;
    }
    public void setAgroChemical(AgroChemical agroChemical) {
        this.agroChemical = agroChemical;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Crop getCrop() {
        return crop;
    }
    public void setCrop(Crop crop) {
        this.crop = crop;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public LocalDateTime getRequestDate() {
        return requestDate;
    }
    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

}
