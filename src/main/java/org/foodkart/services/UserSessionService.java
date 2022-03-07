package org.foodkart.services;

import org.foodkart.models.User;
import org.foodkart.models.UserSession;

public class UserSessionService {
    private UserSession userSession;

    public UserSessionService() {
        this.userSession = null;
    }

    public void startSession(User user) {
        this.userSession = new UserSession(user);
    }

    public String getSessionId() {
        return this.userSession.getId();
    }

    public User getCurrentUser() {
        return this.userSession.getUser();
    }

    public boolean isSessionActive() {
        return this.userSession != null;
    }

    public void endSession() {
        this.userSession = null;
    }
}
