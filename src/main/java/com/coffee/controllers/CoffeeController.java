package com.coffee.controllers;

import com.coffee.entities.Cart;
import com.coffee.entities.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.CartService;
import services.CoffeeService;
import services.OrderService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/coffees")
public class CoffeeController {
    private final CoffeeService coffeeService;
    private final CartService cartService;
    private final OrderService orderService;


    @Autowired
    public CoffeeController(CoffeeService coffeeService, CartService cartService, OrderService orderService) {
        this.coffeeService = coffeeService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<Coffee>> getAllCoffees() {
        List<Coffee> coffees = coffeeService.getAllCoffees();
        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable("id") String coffeeId) {
        Optional<Coffee> optionalCoffee = coffeeService.getCoffeeById(coffeeId);
        return optionalCoffee.map(coffee -> new ResponseEntity<>(coffee, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/cart")
    public ResponseEntity<Cart> addToCart(@RequestBody Cart cart) {
        Cart updatedCart = cartService.addToCart(cart);
        return new ResponseEntity<>(updatedCart, HttpStatus.OK);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order placedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

}
