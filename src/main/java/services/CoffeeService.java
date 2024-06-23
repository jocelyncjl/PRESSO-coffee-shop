package services;

import com.coffee.entities.Coffee;
import com.coffee.repositories.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
     * Retrieves a list of all the coffee products.
     * @return a list of all coffee products
     */
    public List<Coffee> getAllCoffees(){
        return coffeeRepository.findAll();
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
     * Deletes a coffee product by its unique identifier.
     * @param coffeeId the unique identifier of the coffee product to be deleted
     */
    public void deleteCoffee(String coffeeId) {
        coffeeRepository.deleteById(coffeeId);
    }

}
