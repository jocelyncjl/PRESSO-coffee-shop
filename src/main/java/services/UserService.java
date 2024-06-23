package services;

import com.coffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.coffee.entities.User;
import org.springframework.stereotype.Service;

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
    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
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
}
