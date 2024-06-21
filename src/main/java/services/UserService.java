package services;

import com.coffee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.coffee.entities.User;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;

    }

    public User registerUser(User user){
        user.setUserPassword(bCryptPasswordEncoder.encode(user.getUserPassword()));
        user.setRegistrationDate(LocalDateTime.now());
        return userRepository.save(user);
    }

    public Optional<User> login(String userName,String password){
        Optional<User> optionalUser = userRepository.findByUsername(userName);
        if (optionalUser.isPresent() && bCryptPasswordEncoder.matches(password,optionalUser.get().getUserPassword())){
            return optionalUser;
        }
        return Optional.empty();
    }

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
