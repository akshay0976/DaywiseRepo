package com.orderfood;

import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    private List<String> cuisines;

    public Restaurant(String name, String location, List<String> cuisines) {
        this.name = name;
        this.location = location;
        this.cuisines = cuisines;
    }

    public boolean servesCuisine(String cuisine) {
        return cuisines.contains(cuisine);
    }

    public String getName() {
        return name;
    }
}
