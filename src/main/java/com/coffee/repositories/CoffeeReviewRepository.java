package com.coffee.repositories;

import com.coffee.entities.Coffee;
import com.coffee.entities.CoffeeReview;
import com.coffee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoffeeReviewRepository extends JpaRepository<CoffeeReview,Long> {
    /**
     * Retrieves a list of all CoffeeReview entities associated with the given Coffee entity.
     *
     * @param coffee the Coffee entity to find reviews for
     * @return a list of CoffeeReview entities related to the given Coffee
     */
    List<CoffeeReview> findByCoffee(Coffee coffee);
    /**
     * Retrieves a list of all CoffeeReview entities created by the given User entity.
     *
     * @param user the User entity to find reviews for
     * @return a list of CoffeeReview entities created by the given User
     */
    List<CoffeeReview> findByUser(User user);

    /**
     * Retrieves a list of CoffeeReview entities created by the given User for the given Coffee.
     *
     * @param coffee the Coffee entity to find reviews for
     * @param user the User entity to find reviews by
     * @return a list of CoffeeReview entities created by the given User for the given Coffee
     */

    List<CoffeeReview> findByCoffeeAndUser(Coffee coffee, User user);


}
