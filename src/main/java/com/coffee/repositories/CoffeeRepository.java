package com.coffee.repositories;

import com.coffee.entities.Coffee;
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


}
