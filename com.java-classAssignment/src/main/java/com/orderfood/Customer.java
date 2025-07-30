package com.orderfood;

public class Customer {
    private String name;
    private String contact;
    private String preferredCuisine;

    public Customer(String name, String contact, String preferredCuisine) {
        this.name = name;
        this.contact = contact;
        this.preferredCuisine = preferredCuisine;
    }

    public String getPreferredCuisine() {
        return preferredCuisine;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }
}
