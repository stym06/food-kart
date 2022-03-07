package org.foodkart.models;

import java.util.UUID;

public class Order {
    private String id;
    private User user;
    private Restaurant restaurant;
    private FoodItem foodItem;

    public Order(User user, Restaurant restaurant, FoodItem foodItem) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
        this.restaurant = restaurant;
        this.foodItem = foodItem;
    }

    public User getUser() {
        return user;
    }

    public boolean isOrderByUser(String userId) {
        return this.user.getId().equalsIgnoreCase(userId);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public String getId() {
        return id;
    }
}
