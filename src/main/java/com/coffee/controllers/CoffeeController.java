package com.coffee.controllers;

import com.coffee.entities.Coffee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coffee.services.CoffeeService;

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

    /**
     * Handles the creation of a new coffee.
     *
     * @param coffee The coffee object to be created, passed in the request body.
     * @return A ResponseEntity containing the created coffee object, with a status of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<Coffee> createCoffee(@RequestBody Coffee coffee){
        Coffee savedCoffee = coffeeService.saveCoffee(coffee);
        return new ResponseEntity<>(savedCoffee, HttpStatus.CREATED);

    }

    /**
     * Handles the update of an existing coffee.
     *
     * @param id The ID of the coffee to be updated, passed in the URL path.
     * @param coffee The updated coffee object, passed in the request body.
     * @return A ResponseEntity containing the updated coffee object, with a status of OK (200).
     */
    @PutMapping("/{id}")
    public ResponseEntity<Coffee> updateCoffee(@PathVariable String id, @RequestBody Coffee coffee){
        Coffee updatedCoffee = coffeeService.updateCoffee(id, coffee);
        return new ResponseEntity<>(updatedCoffee, HttpStatus.OK);
    }

    /**
     * Handles the deletion of an existing coffee.
     *
     * @param id The ID of the coffee to be deleted, passed in the URL path.
     * @return A ResponseEntity with no content (void), and a status of NO_CONTENT (204).
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCoffee(@PathVariable String id){
        coffeeService.deleteCoffee(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Handles the search for coffees based on various criteria.
     *
     * @param keyword   An optional search keyword to filter the results.
     * @param minPrice  An optional minimum price to filter the results.
     * @param maxPrice  An optional maximum price to filter the results.
     * @param page      An optional page number for pagination (default is 0).
     * @param size      An optional page size for pagination (default is 10).
     * @param sortBy    An optional field to sort the results by (default is "name").
     * @param sortDirection An optional sort direction (default is "asc").
     * @return A ResponseEntity containing a Page of matching Coffee objects, with a status of OK (200).
     */

    @GetMapping("/search")
    public ResponseEntity<Page<Coffee>> searchCoffees(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer minPrice,
            @RequestParam(required = false) Integer maxPrice,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size,
            @RequestParam(required = false, defaultValue = "name") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") Sort.Direction sortDirection){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, sortBy));
        Page<Coffee> coffees = coffeeService.searchCoffees(keyword, minPrice, maxPrice, pageable);

        return new ResponseEntity<>(coffees, HttpStatus.OK);
    }
}