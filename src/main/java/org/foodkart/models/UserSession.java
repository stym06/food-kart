package org.foodkart.models;

import java.util.UUID;

public class UserSession {
    private String id;
    private User user;

    public UserSession(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getId() {
        return id;
    }
}
