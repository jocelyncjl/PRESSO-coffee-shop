package com.coffee.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.security.Timestamp;

@Entity(name = "gifts")
public class Gift {
    @Id
    private String giftId;
    private String giftName;
    private String giftDescription;
    private BigDecimal pointsRequired;
    private int stock;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // Getters and Setters
    public String getGiftId() {
        return giftId;
    }

    public void setGiftId(String giftId) {
        this.giftId = giftId;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getGiftDescription() {
        return giftDescription;
    }

    public void setGiftDescription(String giftDescription) {
        this.giftDescription = giftDescription;
    }

    public BigDecimal getPointsRequired() {
        return pointsRequired;
    }

    public void setPointsRequired(BigDecimal pointsRequired) {
        this.pointsRequired = pointsRequired;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

}
