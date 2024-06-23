package com.coffee.repositories;

import com.coffee.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.coffee.entities.User;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    /**
     * Finds the cart of a specific user.
     *
     * @param user the user whose cart is to be found
     * @return an optional containing the user's cart, or an empty optional if not found
     */
    Optional<Cart> findByUser(User user);

    /**
     * Finds all carts belonging to a specific user.
     *
     * @param user the user whose carts are to be found
     * @return a list of carts belonging to the specified user
     */
    List<Cart> findAllByUser(User user);

    /**
     * Checks if a cart belonging to a specific user exists.
     *
     * @param user the user whose cart is to be checked
     * @return true if the user's cart exists, false otherwise
     */
    boolean existsByUser(User user);
}
