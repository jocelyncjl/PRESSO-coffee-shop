package com.coffee.controllers;

import com.coffee.entities.Cart;
import com.coffee.entities.Coffee;
import com.coffee.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CartService;

@RestController
@RequestMapping("/api/carts")
public class CartController {
    private final CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    /**
     * Creates a new Cart entity for the given User.
     *
     * @param user the User entity to associate the new Cart with
     * @return a ResponseEntity containing the newly created Cart entity with a status of CREATED (HTTP 201)
     */
    @PostMapping
    public ResponseEntity<Cart> createCart(@RequestBody User user){
        Cart cart = cartService.createCart(user);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);

    }

    /**
     * Adds a Coffee item to the specified Cart.
     *
     * @param cartId the ID of the Cart to add the item to
     * @param coffee the Coffee entity to add to the Cart
     * @param quantity the quantity of the Coffee item to add to the Cart
     * @return a ResponseEntity containing the updated Cart entity with a status of OK (HTTP 200)
     */
    @PostMapping("/{cartId}/items")
    public ResponseEntity<Cart> addToCart(@PathVariable Long cartId, @RequestBody Coffee coffee, @RequestParam int quantity){
        Cart cart = cartService.addToCart(cartId,coffee,quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    /**
     * Updates the quantity of a specific Cart item.
     *
     * @param cartId the ID of the Cart to update the item in
     * @param itemId the ID of the Cart item to update
     * @param quantity the new quantity for the Cart item
     * @return a ResponseEntity containing the updated Cart entity with a status of OK (HTTP 200)
     */
    @PutMapping("/{cartId}/items/{itemId}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable Long cartId, @PathVariable Long itemId, @RequestParam int quantity){
        Cart cart = cartService.updateCartItem(cartId, itemId, quantity);
        return new ResponseEntity<>(cart, HttpStatus.OK);

    }

}
