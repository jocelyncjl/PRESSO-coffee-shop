package com.coffee.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // The user who owns this cart
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // The items in the cart
    @ManyToMany
    @JoinTable(
            name = "cart_items",
            joinColumns = @JoinColumn(name = "cart_id"),
            inverseJoinColumns = @JoinColumn(name = "coffee_id")
    )
    private List<Coffee> items = new ArrayList<>();

    // The date and time when the cart was created
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    // The date and time when the cart was last updated
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    // Default constructor
    public Cart() {
    }

    // Constructor that takes a user
    public Cart(User user) {
        this.user = user;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Coffee> getItems() {
        return items;
    }

    public void setItems(List<Coffee> items) {
        this.items = items;
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

    /**
     * Adds a new cart item to the cart.
     *
     * @param cartItem the cart item to be added
     */
    public void addCartItem(CartItem cartItem) {
        cartItem.add(cartItem);
        cartItem.setCart(this);
    }

    /**
     * Removes a cart item from the cart.
     *
     * @param cartItem the cart item to be removed
     */
    public void removeCartItem(CartItem cartItem) {
        cartItem.remove(cartItem);
        cartItem.setCart(null);
    }


    /**
     * Gets the cart item from the cart.
     *
     * @return the cart item
     */
    public CartItem getCartItem() {
        if (cartItems.isEmpty()) {
            return null;
        }
        return cartItems.get(0);
    }

}
