package com.coffee.controllers;

import com.coffee.entities.Gift;
import com.coffee.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.coffee.services.UserService;

import java.util.Optional;


/**
 * The UserController class, which provides REST endpoints for user-related operations.
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    /**
     * The UserService instance, used for handling user-related business logic.
     */
    private final UserService userService;

    /**
     * Constructor that injects the required UserService dependency.
     * @param userService the UserService instance
     */
    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    /**
     * Registers a new user.
     * @param user the user to be registered
     * @return a ResponseEntity containing the registered user and a status of CREATED
     */
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user){
        User registeredUser = userService.registerUser(user);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    /**
     * Logs in a user.
     * @param userName the username of the user
     * @param password the password of the user
     * @return a ResponseEntity containing the logged-in user and a status of OK, or a status of UNAUTHORIZED if the login fails
     */
    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestParam("username") String userName,@RequestParam("password") String password){
        Optional<User> optionalUser = userService.login(userName,password);
        return optionalUser.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));

    }

}
