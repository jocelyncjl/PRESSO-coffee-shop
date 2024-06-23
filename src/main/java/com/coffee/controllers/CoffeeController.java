package com.coffee.controllers;

import com.coffee.entities.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import services.CoffeeService;

import java.util.Optional;

/**
 * The CoffeeController class, which handles the HTTP requests related to coffee products.
 */
@RestController
@RequestMapping("/api/coffees")
public class CoffeeController{
    /**
     * The CoffeeService instance, used for performing service-level operations on coffee products.
     */
    private final CoffeeService coffeeService;

    /**
     * Constructor that injects the required CoffeeService dependency.
     * @param coffeeService the CoffeeService instance
     */
    @Autowired
    public CoffeeController(CoffeeService coffeeService) {
        this.coffeeService = coffeeService;
    }

    /**
     * Retrieves a coffee product by its unique identifier.
     * @param id the unique identifier of the coffee product
     * @return a ResponseEntity containing the coffee product, or a 404 NOT FOUND response if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<Coffee> getCoffeeById(@PathVariable String id) {
        Optional<Coffee> coffee = coffeeService.getCoffeeById(id);
        return coffee.map(c -> new ResponseEntity<>(c, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

}