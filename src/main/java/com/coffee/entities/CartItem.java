package com.coffee.entities;

import jakarta.persistence.*;


@Entity(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The coffee item in the cart
    @ManyToOne
    @JoinColumn(name = "coffee_id")
    private Coffee coffee;

    // The cart that this item belongs to
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    // The quantity of the coffee item in the cart
    @Column(name = "quantity")
    private Integer quantity;

    // Default constructor
    public CartItem() {
    }

    // Constructor that takes coffee and quantity
    public CartItem(Coffee coffee, Integer quantity) {
        this.coffee = coffee;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coffee getCoffee() {
        return coffee;
    }

    public void setCoffee(Coffee coffee) {
        this.coffee = coffee;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
