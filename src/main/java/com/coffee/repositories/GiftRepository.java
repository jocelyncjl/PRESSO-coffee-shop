package com.coffee.repositories;

import com.coffee.entities.Gift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Timestamp;
import java.util.List;

public interface GiftRepository extends JpaRepository<Gift,String> {
    /**
     * Find a list of gifts that have a points required value less than or equal to the specified value.
     *
     * @param pointsRequired The maximum points required for the gifts.
     * @return A list of Gift entities that match the criteria.
     */
    List<Gift> findByPointsRequiredLessThanEqual(double pointsRequired);

    /**
     * Find a list of gifts that have a stock value greater than the specified value.
     *
     * @param stock The minimum stock value for the gifts.
     * @return A list of Gift entities that match the criteria.
     */
    List<Gift> findByStockGreaterThan(int stock);

    /**
     * Find a list of gifts that have been updated after the specified timestamp.
     *
     * @param timestamp The timestamp to compare the updated_at field against.
     * @return A list of Gift entities that match the criteria.
     */
    List<Gift> findByUpdatedAtAfter(Timestamp timestamp);

}
