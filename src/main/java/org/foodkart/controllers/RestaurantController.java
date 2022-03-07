package org.foodkart.controllers;

import org.foodkart.models.Restaurant;
import org.foodkart.services.RestaurantService;

import java.util.List;

public class RestaurantController {

    private final RestaurantService restaurantService;

    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public Restaurant registerRestaurant(Restaurant restaurant) {
        return this.restaurantService.registerRestaurant(restaurant);
    }

    public Integer updateQuantity(String restaurantName, Integer quantityToAdd) {
        return this.restaurantService.updateQuantity(restaurantName, quantityToAdd);
    }

    public String updateLocation(String restaurantName, String pincodes) {
        return this.restaurantService.updateLocation(restaurantName, pincodes);
    }

    public Integer rateRestaurant(String restaurantName, Integer rating, String comment) {
        return this.restaurantService.rateRestaurant(restaurantName, rating, comment);
    }

    public List<String> showRestaurantsSortedByRating() {
        return this.restaurantService.showRestaurantsSortedByRating();
    }

    public List<String> showRestaurantsSortedByPrice() {
        return this.restaurantService.showRestaurantsSortedByPrice();
    }

    public String placeOrder(String restaurantName, Integer quantity) {
        return this.restaurantService.placeOrder(restaurantName, quantity);
    }

}
