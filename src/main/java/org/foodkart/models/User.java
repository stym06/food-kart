package org.foodkart.models;

import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String gender;
    private String phoneNo;
    private String pincode;

    public User(String name, String gender, String phoneNo, String pincode) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.gender = gender;
        this.phoneNo = phoneNo;
        this.pincode = pincode;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
