package com.coffee.services;

import com.coffee.entities.Gift;
import com.coffee.entities.UserGift;
import com.coffee.mapper.UserMapper;
import com.coffee.repositories.GiftRepository;
import com.coffee.repositories.UserGiftRepository;
import com.coffee.repositories.UserRepository;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.coffee.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/*
* The UserService class, which provides methods for user registration, login, and profile update
* */

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    private final UserRepository userRepository;


    /**
     * The BCryptPasswordEncoder instance, used for password hashing and verification.
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor that injects the required dependencies.
     * @param userRepository the UserRepository instance
     * @param bCryptPasswordEncoder the BCryptPasswordEncoder instance
     */

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    /**
     * Registers a new user.
     * @param user the user to be registered
     * @return the registered user
     */
    public User registerUser(User user){
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        //return userRepository.save(user);
        userMapper.insert(user);
        return user;
    }

    /**
     * Logs in a user.
     * @param name the username of the user
     * @param password the password of the user
     * @return an Optional containing the user if the login is successful, or an empty Optional if the login fails
     */
    public Optional<User> login(String name,String password){
        Optional<User> optionalUser = userRepository.findByName(name);
        if (optionalUser.isPresent() && bCryptPasswordEncoder.matches(password,optionalUser.get().getUserPassword())){
            return optionalUser;
        }
        return Optional.empty();
    }

}
