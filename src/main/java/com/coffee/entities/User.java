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
    private String name;

    // The password of the user
    @Column
    private String password;

    // The email address of the user
    @Column
    private String email;

    @Column
    private int age;

    // The phone number of the user
    @Column
    private String phone;

    // The address of the user
    @Column
    private String address;

    // Getters and Setters
    public Integer getUserId(){
        return userId;
    }

    public void setUserId(Integer userId){
        this.userId = userId;
    }

    public String getUserName(){
        return name;
    }

    public void setUserName(String name){
        this.name = name;
    }

    public String getUserPassword() {
        return password;
    }

    public void setUserPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
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


    // Constructors
    public User() {
    }

    public User(String name, String password, String email,int age, String phone, String address) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.age = age;
        this.phone = phone;
        this.address = address;
    }

    public User(String username) {
    }
}
