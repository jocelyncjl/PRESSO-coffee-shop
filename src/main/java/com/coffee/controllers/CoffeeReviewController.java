package com.coffee.controllers;

import com.coffee.entities.CoffeeReview;
import com.coffee.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.CoffeeReviewService;

import java.util.List;


@RestController
@RequestMapping("/api/reviews")

public class CoffeeReviewController {
    private final CoffeeReviewService coffeeReviewService;

    @Autowired
    public CoffeeReviewController(CoffeeReviewService coffeeReviewService) {
        this.coffeeReviewService = coffeeReviewService;
    }

    /**
     * Creates a new CoffeeReview entity based on the provided parameters.
     *
     * @param coffeeId the ID of the Coffee entity to associate the review with
     * @param rating the rating score for the coffee (between 1 and 5)
     * @param comment the text comment for the coffee review
     * @return a ResponseEntity containing the newly created CoffeeReview entity with a status of CREATED (HTTP 201)
     */
    @PostMapping
    public ResponseEntity<CoffeeReview> createReview(@RequestParam String coffeeId, @RequestParam int rating, @RequestParam String comment){
        User currentUser = getCurrentUser();
        CoffeeReview review = coffeeReviewService.createReview(coffeeId,currentUser.getUserId(),rating,comment);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    /**
     * Retrieves a list of all CoffeeReview entities associated with the given Coffee entity.
     *
     * @param coffeeId the ID of the Coffee entity to find reviews for
     * @return a ResponseEntity containing a list of CoffeeReview entities with a status of OK (HTTP 200)
     */
    @GetMapping("/coffee/{coffeeId}")
    public ResponseEntity<List<CoffeeReview>> getReviewsByCoffee(@PathVariable String coffeeId){
        List<CoffeeReview> reviews = coffeeReviewService.getReviewsByCoffee(coffeeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    /**
     * Retrieves a list of all CoffeeReview entities created by the currently authenticated User.
     *
     * @return a ResponseEntity containing a list of CoffeeReview entities with a status of OK (HTTP 200)
     */
    public ResponseEntity<List<CoffeeReview>> getReviewsByUser(){
        User currentUser = getCurrentUser();
        List<CoffeeReview> reviews = coffeeReviewService.getReviewsByUser(currentUser.getUserId());
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    /**
     * Retrieves the currently authenticated User.
     *
     * @return the currently authenticated User
     */
    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new User(username);

    }

}
