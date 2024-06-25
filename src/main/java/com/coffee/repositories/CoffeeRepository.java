package com.coffee.repositories;

import com.coffee.entities.Coffee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CoffeeRepository extends JpaRepository<Coffee,String> {
    /*
    * Finds a coffee by its name
    * @param name the name of the coffee
    * @return an optional containing the coffee, or an empty optional if not found
    *
    * */
    Optional<Coffee> findByName(String name);

    /*
    * Finds all coffees of a specific type.
    * @param type the type of the coffee
    * @return a list of coffees of the specified type
    * */

    List<Coffee> findAllByType(String type);

    /*
    * Finds all coffees with a price less than or equal to the specified price.
    * @param price the maximum price
    * @return a list of coffees with a price less than or equal to the specified price
    *
    * */

    List<Coffee> findAllByPriceLessThanEqual(double price);

    /*
    * Checks if a coffee with the given name exists.
    * @param name the name of the coffee
    * @return true if a coffee with the given name exists, false otherwise
    *
    * */

    boolean existsByName(String name);

    /**
     * Finds a paginated list of Coffees where the name contains the given keyword.
     *
     * @param keyword  the keyword to search for in the Coffee name
     * @param pageable the pagination information (page number, size, sorting, etc.)
     * @return a Page object containing the matching Coffees
     */
    Page<Coffee> findByNameContaining(String keyword, Pageable pageable);

    /**
     * Finds a paginated list of Coffees where the price is between the given minimum and maximum values.
     *
     * @param minPrice the minimum price to include in the results
     * @param maxPrice the maximum price to include in the results
     * @param pageable the pagination information (page number, size, sorting, etc.)
     * @return a Page object containing the matching Coffees
     */

    Page<Coffee> findByPriceBetween(Integer minPrice, Integer maxPrice, Pageable pageable);

    /**
     * Finds a paginated list of Coffees where the name contains the given keyword and the price is between the given minimum and maximum values.
     *
     * @param keyword  the keyword to search for in the Coffee name
     * @param minPrice the minimum price to include in the results
     * @param maxPrice the maximum price to include in the results
     * @param pageable the pagination information (page number, size, sorting, etc.)
     * @return a Page object containing the matching Coffees
     */
    Page<Coffee> findByNameContainingAndPriceBetween(String keyword, Integer minPrice, Integer maxPrice, Pageable pageable);

}
