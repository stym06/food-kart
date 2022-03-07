package org.foodkart.models;

import java.util.UUID;

public class FoodItem {
    private String id;
    private String name;
    private Double price;
    private Integer quantity;

    public FoodItem(String name, Double price, Integer quantity) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public void updateQuantity(Integer quantityToadd) {
        this.quantity += quantityToadd;
    }
}
