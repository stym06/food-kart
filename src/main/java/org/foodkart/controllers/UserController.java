package org.foodkart.controllers;

import org.foodkart.models.User;
import org.foodkart.services.RestaurantService;
import org.foodkart.services.UserService;

import java.util.List;


public class UserController {

    private final UserService userService;
    private final RestaurantService restaurantService;

    public UserController(UserService userService, RestaurantService restaurantService) {
        this.userService = userService;
        this.restaurantService = restaurantService;
    }

    public User registerUser(User user) {
        return this.userService.registerUser(user);
    }

    public String login(String userId) {
        return this.userService.login(userId);
    }

    public List<String> getOrdersByUser() {
        return this.restaurantService.getOrdersByUser();
    }

}
