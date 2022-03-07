package org.foodkart.models;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class Restaurant {
    private String id;
    private String name;
    private List<String> serviceablePincodes;
    private FoodItem foodItem;
    private Review review;

    public Restaurant(String name, String serviceablePincodes, FoodItem foodItem) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.serviceablePincodes = Arrays.asList(serviceablePincodes.split("/"));
        this.foodItem = foodItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getServiceablePincodes() {
        return serviceablePincodes;
    }

    public void setServiceablePincodes(String serviceablePincodes) {
        this.serviceablePincodes = Arrays.asList(serviceablePincodes.split("/"));
    }

    public FoodItem getFoodItem() {
        return foodItem;
    }

    public void setFoodItem(FoodItem foodItem) {
        this.foodItem = foodItem;
    }

    public void updateQuantity(Integer quantityToAdd) {
        this.foodItem.updateQuantity(quantityToAdd);
    }

    public boolean isServiceable(String pincode) {
        for(String p: getServiceablePincodes()) {
            if(p.equalsIgnoreCase(pincode)) return this.foodItem.getQuantity()>0;
        }
        return false;
    }

    public String getId() {
        return id;
    }

    public void setRating(User user, Integer rating) {
        this.review = new Review(user, rating);
    }

    public void setRating(User user, Integer rating, String comment) {
        this.review = new Review(user, rating,comment);
    }

    public Integer getRating() {
        return review.getRating();
    }

    public Integer getFoodItemPrice() {
        return getFoodItem().getPrice().intValue();
    }
}
