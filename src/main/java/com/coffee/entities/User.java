package com.coffee.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/*
* The User entity class, representing a user in the coffee shop application.
* It is mapped to the "users" table in the database.
*
* */
@Entity(name = "users")
public class User {

    // The unique identifier of the user. It is generated automatically using the IDENTITY strategy.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    // The username of the user, which must be unique
    @Column(unique = true)
    private String userName;

    // The password of the user
    private String userPassword;

    // The email address of the user
    private String email;

    // The phone number of the user
    private String phone;

    // The address of the user
    private String address;

    // The date and time when the user registered
    private LocalDateTime registrationDate;

    // The number of points the user has accumulated
    private BigDecimal points;

    // Getters and Setters
    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }

    public BigDecimal getPoints() {
        return points;
    }

    public void setPoints(BigDecimal points) {
        this.points = points;
    }

    // Constructors
    public User() {
    }

    public User(String userName, String userPassword, String email, String phone, String address) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.registrationDate = LocalDateTime.now();
        this.points = BigDecimal.ZERO;
    }
}
