package com.coffee.controllers;

import com.coffee.entities.Order;
import com.coffee.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import services.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    // Inject the OrderService instance to handle the order-related business logic
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Create a new order.
     *
     * @param user The user who is placing the order.
     * @return A ResponseEntity containing the created Order object and an HTTP status of CREATED (201).
     */
    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody User user){
        Order order = orderService.createOrder(user);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    /**
     * Retrieve the order history for a specific user.
     *
     * @param userId The ID of the user whose order history should be retrieved.
     * @return A ResponseEntity containing a list of Order objects and an HTTP status of OK (200).
     */
    public ResponseEntity<List<Order>> getOrderHistory(@RequestParam Long userId){
        List<Order> orders = orderService.getOrderHistory(userId);
        return new ResponseEntity<>(orders, HttpStatus.OK);

    }
}
