package services;

import com.coffee.entities.Coffee;
import com.coffee.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * The CoffeeService class, which provides service-level operations for managing coffee products.
 */
@Service
public class CoffeeService {
    /**
     * The CoffeeRepository instance, used for accessing and modifying coffee data.
     */
    private final CoffeeRepository coffeeRepository;

    /**
     * Constructor that injects the required CoffeeRepository dependency.
     * @param coffeeRepository the CoffeeRepository instance
     */
    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository){
        this.coffeeRepository = coffeeRepository;
    }

    /**
     * Retrieves a page of all the coffee products.
     * @return a page of all coffee products
     */
    public Page<Coffee> getAllCoffees(Pageable pageable) {
        return coffeeRepository.findAll(pageable);
    }

    /**
     * Retrieves a coffee product by its unique identifier.
     * @param coffeeId the unique identifier of the coffee product
     * @return an Optional containing the coffee product, or an empty Optional if not found
     */
    public Optional<Coffee> getCoffeeById(String coffeeId){
        return coffeeRepository.findById(coffeeId);
    }

    /**
     * Saves a new coffee product or updates an existing one.
     * @param coffee the coffee product to be saved or updated
     * @return the saved or updated coffee product
     */
    public Coffee saveCoffee(Coffee coffee){
        return coffeeRepository.save(coffee);
    }

    /**
     * Updates an existing Coffee entity in the repository.
     *
     * @param id     the unique identifier of the Coffee to update
     * @param coffee the updated Coffee object, containing the new values for the Coffee's fields
     * @return the updated Coffee entity, or null if the Coffee with the given ID was not found
     */

    public Coffee updateCoffee(String id, Coffee coffee){
        Coffee existingCoffee = coffeeRepository.findById(id).orElse(null);
        if (existingCoffee != null) {
            existingCoffee.setCoffeeName(coffee.getCoffeeName());
            existingCoffee.setCoffeeDescription(coffee.getCoffeeDescription());
            existingCoffee.setPrice(coffee.getPrice());
            return coffeeRepository.save(existingCoffee);
        }
        return null;
    }


    /**
     * Deletes a coffee product by its unique identifier.
     * @param coffeeId the unique identifier of the coffee product to be deleted
     */
    public void deleteCoffee(String coffeeId) {
        coffeeRepository.deleteById(coffeeId);
    }

    /**
     * Searches for Coffees based on the provided search criteria.
     *
     * @param keyword   the keyword to search for in the Coffee name (can be null or empty)
     * @param minPrice  the minimum price to include in the results (can be null)
     * @param maxPrice  the maximum price to include in the results (can be null)
     * @param pageable  the pagination information (page number, size, sorting, etc.)
     * @return a Page object containing the matching Coffees
     */
    public Page<Coffee> searchCoffees(String keyword, Integer minPrice, Integer maxPrice, Pageable pageable){
        if (keyword != null && !keyword.isEmpty()){
            if (minPrice != null && maxPrice != null) {
                return coffeeRepository.findByNameContainingAndPriceBetween(keyword, minPrice, maxPrice, pageable);
            } else {
                return coffeeRepository.findByNameContaining(keyword, pageable);
            }
        }else {
            if (minPrice != null && maxPrice != null) {
                return coffeeRepository.findByPriceBetween(minPrice, maxPrice, pageable);
            } else {
                return coffeeRepository.findAll(pageable);
            }
        }
    }
}
