package services;

import com.coffee.entities.Coffee;
import com.coffee.entities.CoffeeReview;
import com.coffee.entities.User;
import com.coffee.repositories.CoffeeRepository;
import com.coffee.repositories.CoffeeReviewRepository;
import com.coffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeReviewService {
    private final CoffeeReviewRepository coffeeReviewRepository;

    private final CoffeeRepository coffeeRepository;

    private final UserRepository userRepository;

    @Autowired
    public CoffeeReviewService(CoffeeReviewRepository coffeeReviewRepository, CoffeeRepository coffeeRepository, UserRepository userRepository) {
        this.coffeeReviewRepository = coffeeReviewRepository;
        this.coffeeRepository = coffeeRepository;
        this.userRepository = userRepository;
    }

    /**
     * Creates a new CoffeeReview and saves it to the database.
     *
     * @param coffeeId the ID of the Coffee entity to associate the review with
     * @param userId the ID of the User entity who created the review
     * @param rating the rating score for the coffee (between 1 and 5)
     * @param comment the text comment for the coffee review
     * @return the saved CoffeeReview entity
     * @throws IllegalArgumentException if the Coffee or User entity does not exist
     */
    public CoffeeReview createReview(String  coffeeId, Integer userId, int rating, String comment){
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        Optional<User> optionalUser = userRepository.findById(userId);

        if (optionalCoffee.isPresent() && optionalUser.isPresent()){
            CoffeeReview review = new CoffeeReview();
            review.setCoffee(optionalCoffee.get());
            review.setUser(optionalUser.get());
            review.setRating(rating);
            review.setComment(comment);
            review.setCreatedAt(LocalDateTime.now());
            review.setUpdatedAt(LocalDateTime.now());

            return coffeeReviewRepository.save(review);

        }
        throw new IllegalArgumentException("Invalid coffee or user ID");

    }

    /**
     * Retrieves a list of all CoffeeReview entities associated with the given Coffee entity.
     *
     * @param coffeeId the ID of the Coffee entity to find reviews for
     * @return a list of CoffeeReview entities related to the given Coffee, or an empty list if the Coffee does not exist
     */
    public List<CoffeeReview> getReviewsByCoffee(String coffeeId){
        Optional<Coffee> optionalCoffee = coffeeRepository.findById(coffeeId);
        if (optionalCoffee.isPresent()) {
            return coffeeReviewRepository.findByCoffee(optionalCoffee.get());
        }

        return Collections.emptyList();
    }

    /**
     * Retrieves a list of all CoffeeReview entities created by the given User entity.
     *
     * @param userId the ID of the User entity to find reviews for
     * @return a list of CoffeeReview entities created by the given User, or an empty list if the User does not exist
     */
    public List<CoffeeReview> getReviewsByUser(Integer userId){
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isPresent()) {
            return coffeeReviewRepository.findByUser(optionalUser.get());
        }
        return Collections.emptyList();

    }











}
