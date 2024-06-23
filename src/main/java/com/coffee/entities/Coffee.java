package com.coffee.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

/**
 * The Coffee entity class, representing a coffee product.
 */

@Entity(name = "coffees")
public class Coffee {
    @Id
    @Column(name = "coffee_id")
    private String coffeeId;

    @Column(name = "coffee_name")
    private String coffeeName;

    @Column(name = "coffee_description")
    private String coffeeDescription;

    @Column(name = "price")
    private double price;

    @Column(name = "category")
    private String category;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Constructors
    public Coffee() {
    }

    public Coffee(String coffeeId, String coffeeName, String coffeeDescription, double price, String category, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.coffeeId = coffeeId;
        this.coffeeName = coffeeName;
        this.coffeeDescription = coffeeDescription;
        this.price = price;
        this.category = category;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    // Getters and setters
    public String getCoffeeId() {
        return coffeeId;
    }

    public void setCoffeeId(String coffeeId) {
        this.coffeeId = coffeeId;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public String getCoffeeDescription() {
        return coffeeDescription;
    }

    public void setCoffeeDescription(String coffeeDescription) {
        this.coffeeDescription = coffeeDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

}
