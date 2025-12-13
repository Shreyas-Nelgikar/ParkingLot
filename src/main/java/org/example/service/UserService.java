package org.example.service;

import lombok.AllArgsConstructor;
import org.example.exception.InvalidUserDetailsException;
import org.example.model.User;
import org.example.respository.UserRepository;

import java.util.Optional;

@AllArgsConstructor
public class UserService {
    
    private UserRepository userRepository;
    
    public void validateUser(String username, Long phoneNumber, String address) throws InvalidUserDetailsException {
        if (username == null || username.isEmpty()) {
            throw new InvalidUserDetailsException("Username cannot be null or empty");
        }
        if (address == null || address.isEmpty()) {
            throw new InvalidUserDetailsException("Address cannot be null or empty");
        }
    }
    
    public User fetchUser(String username, Long phoneNumber, String address) {
        Optional<User> userOptional = userRepository.checkIfUserExists(username);
        if (userOptional.isEmpty()) {
            User user = new User(username, phoneNumber, address);
            return userRepository.saveUser(user);
        }
        return userOptional.get();
    }
}