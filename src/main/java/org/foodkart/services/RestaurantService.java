package org.foodkart.services;

import org.foodkart.exceptions.InactiveSessionException;
import org.foodkart.exceptions.RestaurantNotFoundException;
import org.foodkart.models.FoodItem;
import org.foodkart.models.Order;
import org.foodkart.models.Restaurant;
import org.foodkart.models.User;

import java.util.*;
import java.util.stream.Collectors;

public class RestaurantService {

    private final Map<String, Restaurant> restaurants;
    private final Map<String, Order> orders;
    private final UserSessionService userSessionService;

    public RestaurantService(UserSessionService userSessionService) {
        this.restaurants = new HashMap<>();
        this.orders = new HashMap<>();
        this.userSessionService = userSessionService;
    }

    public Restaurant registerRestaurant(Restaurant restaurant) {
        this.restaurants.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public Integer updateQuantity(String restaurantName, Integer quantityToAdd) {
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            throw new RestaurantNotFoundException("Restaurant not found!");

        restaurant.updateQuantity(quantityToAdd);
        restaurants.put(restaurant.getId(), restaurant);
        return restaurant.getFoodItem().getQuantity();
    }

    private Restaurant getRestaurantByName(String restaurantName) {
        for(Restaurant r: restaurants.values()) {
            if(r.getName().equalsIgnoreCase(restaurantName)) return r;
        }
        return null;
    }

    public Integer rateRestaurant(String restaurantName, Integer rating, String comment) {
        Restaurant restaurant = getRestaurantByName(restaurantName);
        User loggedInUser = getLoggedinUser();
        if(restaurant == null)
            throw new RestaurantNotFoundException("Restaurant not found!");

        if(comment != null) {
            restaurant.setRating(loggedInUser, rating, comment);
        }
        else {
            restaurant.setRating(loggedInUser, rating);
        }
        return restaurant.getRating();
    }

    public List<String> showRestaurantsSortedByRating() {
        return this.restaurants.values().stream()
                .filter(r -> r.isServiceable(getLoggedinUser().getPincode()))
                .sorted(Comparator.comparingInt(Restaurant::getRating).reversed())
                .map(Restaurant::getName)
                .collect(Collectors.toList());
    }

    public List<String> showRestaurantsSortedByPrice() {
        return this.restaurants.values().stream()
                .filter(r -> r.isServiceable(getLoggedinUser().getPincode()))
                .sorted(Comparator.comparingInt(Restaurant::getFoodItemPrice).reversed())
                .map(r -> r.getName().concat(",").concat(r.getFoodItem().getName()))
                .collect(Collectors.toList());
    }

    private User getLoggedinUser() {
        User loggedInUser = this.userSessionService.getCurrentUser();
        if(loggedInUser == null)
            throw new InactiveSessionException("User session not active!");
        return loggedInUser;
    }

    public String placeOrder(String restaurantName, Integer quantity) {
        User loggedInUser = getLoggedinUser();
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            throw new RestaurantNotFoundException("Restaurant not found!");
        Order order = new Order(loggedInUser, restaurant, new FoodItem("Fries", 100.0, quantity));
        if(restaurant.getFoodItem().getQuantity() > order.getFoodItem().getQuantity()) {
            orders.put(order.getId(), order);
            restaurant.updateQuantity(-order.getFoodItem().getQuantity());
            System.out.println("Order Placed Successfully.");
            return order.getId();
        }
        else {
            System.out.println("Cannot place order");
            return null;
        }
    }

    public List<String> getOrdersByUser() {
        User loggedInUser = getLoggedinUser();
        return this.orders.values().stream()
                .filter(o -> o.isOrderByUser(loggedInUser.getId()))
                .map(Order::getId)
                .collect(Collectors.toList());
    }

    public String updateLocation(String restaurantName, String pincodes) {
        Restaurant restaurant = getRestaurantByName(restaurantName);
        if(restaurant == null)
            throw new RestaurantNotFoundException("Restaurant not found!");
        restaurant.setServiceablePincodes(pincodes);
        return restaurant.getName().concat(",").concat(pincodes).concat(",").concat(restaurant.getFoodItem().getName()).concat("-").concat(restaurant.getFoodItem().getQuantity().toString());
    }
}
