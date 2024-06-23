package com.coffee.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.coffee.entities.User;

import java.util.Optional;

/*
* The UserRepository interface, which extends the JpaRepository interface.
* This interface provides methods for interacting with the User entity in the database.
*
* */

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {

    /*
    * Finds a User by their username.
    * @param username the username of the user to find
    * @return an Optional containing the User if found, or an empty Optional if not found
    * */
    Optional<User> findByUsername(String username);

    /**
     * Finds a User by their email address.
     * @param email the email address of the user to find
     * @return an Optional containing the User if found, or an empty Optional if not found
     */
    Optional<User> findByEmail(String email);
}
