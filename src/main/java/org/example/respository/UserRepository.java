package org.example.respository;

import org.example.model.User;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class UserRepository {

    private Map<String, User> userMap = new HashMap<>();
    private Long userId = 0L;

    public Optional<User> checkIfUserExists(String username) {
        if (userMap.containsKey(username)) {
            return Optional.of(userMap.get(username));
        }

        return  Optional.empty();
    }

    public User saveUser(User user) {
        user.setId(userId++);
        user.setCreatedAt(new Date());
        user.setModifiedAt(new Date());
        userMap.put(user.getUserName(), user);
        return userMap.get(user.getUserName());
    }
}
