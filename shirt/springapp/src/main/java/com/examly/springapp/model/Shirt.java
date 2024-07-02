package com.examly.springapp.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Shirt {
    @Id
    int shirtId;
    String shirtSize;
    String shirtColor;

    public Shirt() {
    }

    public Shirt(int shirtId, String shirtSize, String shirtColor) {
        this.shirtId = shirtId;
        this.shirtSize = shirtSize;
        this.shirtColor = shirtColor;

    }

    public int getShirtId() {
        return shirtId;
    }

    public void setShirtId(int shirtId) {
        this.shirtId = shirtId;
    }

    public String getShirtSize() {
        return shirtSize;
    }

    public void setShirtSize(String shirtSize) {
        this.shirtSize = shirtSize;
    }

    public String getShirtColor() {
        return shirtColor;
    }

    public void setShirtColor(String shirtColor) {
        this.shirtColor = shirtColor;
    }

}
