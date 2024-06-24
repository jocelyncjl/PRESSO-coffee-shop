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

    @PostMapping
    public ResponseEntity<CoffeeReview> createReview(@RequestParam String coffeeId, @RequestParam int rating, @RequestParam String comment){
        User currentUser = getCurrentUser();
        CoffeeReview review = coffeeReviewService.createReview(coffeeId,currentUser.getUserId(),rating,comment);
        return new ResponseEntity<>(review, HttpStatus.CREATED);
    }

    @GetMapping("/coffee/{coffeeId}")
    public ResponseEntity<List<CoffeeReview>> getReviewsByCoffee(@PathVariable String coffeeId){
        List<CoffeeReview> reviews = coffeeReviewService.getReviewsByCoffee(coffeeId);
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    public ResponseEntity<List<CoffeeReview>> getReviewsByUser(){
        User currentUser = getCurrentUser();
        List<CoffeeReview> reviews = coffeeReviewService.getReviewsByUser(currentUser.getUserId());
        return new ResponseEntity<>(reviews, HttpStatus.OK);

    }

    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return new User(username);

    }

}
