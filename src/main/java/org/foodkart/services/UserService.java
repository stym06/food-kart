package org.foodkart.services;

import java.util.HashMap;
import java.util.Map;
import org.foodkart.exceptions.UserNotFoundException;
import org.foodkart.models.User;


public class UserService {

    private final Map<String, User> users;
    private final UserSessionService userSessionService;

    public UserService(UserSessionService userSessionService) {
        this.users = new HashMap<>();
        this.userSessionService = userSessionService;
    }

    public User registerUser(User user) {
        this.users.put(user.getId(), user);
        return user;
    }

    public String login(String userId) {
        User user = this.users.get(userId);
        if(user == null)
            throw new UserNotFoundException("User not found!");

        if(this.userSessionService.isSessionActive()) {
            this.userSessionService.endSession();
        }
        this.userSessionService.startSession(user);
        return this.userSessionService.getSessionId();
    }
}
