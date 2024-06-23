package com.coffee.repositories;

import com.coffee.entities.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    // No additional methods needed, as JpaRepository provides the basic CRUD operations

}
