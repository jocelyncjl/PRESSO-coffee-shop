package services;

import com.coffee.entities.Gift;
import com.coffee.entities.UserGift;
import com.coffee.repositories.GiftRepository;
import com.coffee.repositories.UserGiftRepository;
import com.coffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.coffee.entities.User;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

/*
* The UserService class, which provides methods for user registration, login, and profile update
* */

@Service
public class UserService {

    /**
     * The UserRepository instance, used for interacting with the user data.
     */
    private final UserRepository userRepository;

    private final UserGiftRepository userGiftRepository;

    private final GiftRepository giftRepository;

    /**
     * The BCryptPasswordEncoder instance, used for password hashing and verification.
     */
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /**
     * Constructor that injects the required dependencies.
     * @param userRepository the UserRepository instance
     * @param bCryptPasswordEncoder the BCryptPasswordEncoder instance
     */

    @Autowired
    public UserService(UserRepository userRepository, UserGiftRepository userGiftRepository, GiftRepository giftRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.userGiftRepository = userGiftRepository;
        this.giftRepository = giftRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    /**
     * Registers a new user.
     * @param user the user to be registered
     * @return the registered user
     */
    public User registerUser(User user){
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    /**
     * Logs in a user.
     * @param userName the username of the user
     * @param password the password of the user
     * @return an Optional containing the user if the login is successful, or an empty Optional if the login fails
     */
    public Optional<User> login(String userName,String password){
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (optionalUser.isPresent() && bCryptPasswordEncoder.matches(password,optionalUser.get().getUserPassword())){
            return optionalUser;
        }
        return Optional.empty();
    }

    /**
     * Updates the user's profile.
     * @param user the user with the updated profile information
     * @return an Optional containing the updated user if the update is successful, or an empty Optional if the update fails
     */
    public Optional<User> updateUserProfile(User user){
        Optional<User> optionalUser = userRepository.findById(user.getUserId());
        if (optionalUser.isPresent()){
            User updatedUser = optionalUser.get();
            updatedUser.setUserName(user.getUserName());
            updatedUser.setEmail(user.getEmail());
            updatedUser.setPhone(user.getPhone());
            updatedUser.setAddress(user.getAddress());
            return Optional.of(userRepository.save(updatedUser));
        }
        return Optional.empty();
    }

    public void addPoints(User user, double points){
        BigDecimal newPoints = user.getPoints().add(BigDecimal.valueOf(points));
        userRepository.save(user);

    }


    public boolean redeemGift(User user, Gift gift){
        if (user.getPoints().compareTo(gift.getPointsRequired()) >= 0 && gift.getStock() > 0){
            UserGift userGift = new UserGift();
            userGift.setUser(user);
            userGift.setGift(gift);
            userGift.setQuantity(1);

            Timestamp redeemedAt = Timestamp.valueOf(LocalDateTime.now());
            userGift.setRedeemedAt(redeemedAt);
            userGiftRepository.save(userGift);

            user.setPoints(user.getPoints().subtract(gift.getPointsRequired()));
            gift.setStock(gift.getStock() - 1);
            userRepository.save(user);
            giftRepository.save(gift);
            return true;
        }
        return false;
    }










}
