package com.coffee.repositories;

import com.coffee.entities.Gift;
import com.coffee.entities.User;
import com.coffee.entities.UserGift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The UserGiftRepository interface extends the JpaRepository interface and provides methods for
 * interacting with the UserGift entity in the database.
 */

public interface UserGiftRepository extends JpaRepository<UserGift, Integer> {
    /**
     * Find a list of UserGift entities associated with the specified User.
     *
     * @param user The User entity to search for.
     * @return A list of UserGift entities associated with the specified User.
     */
    List<UserGift> findByUser(User user);
    /**
     * Find a list of UserGift entities associated with the specified Gift.
     *
     * @param gift The Gift entity to search for.
     * @return A list of UserGift entities associated with the specified Gift.
     */
    List<UserGift> findByGift(Gift gift);

    /**
     * Find a list of UserGift entities that have a non-null redeemedAt value.
     *
     * @return A list of redeemed UserGift entities.
     */
    List<UserGift> findByRedeemedAtIsNotNull();
    /**
     * Find a list of UserGift entities that have a null redeemedAt value.
     *
     * @return A list of unredeemed UserGift entities.
     */
    List<UserGift> findByRedeemedAtIsNull();

}
