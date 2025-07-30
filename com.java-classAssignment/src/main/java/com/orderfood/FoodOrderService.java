package com.orderfood;

public class FoodOrderService {
    private Customer customer;
    private Restaurant restaurant;

    public FoodOrderService(Customer customer, Restaurant restaurant) {
        this.customer = customer;
        this.restaurant = restaurant;
    }

    public void processOrder() {
        if (restaurant.servesCuisine(customer.getPreferredCuisine())) {
            System.out.println("Order placed for " + customer.getPreferredCuisine() + " food by " + customer.getName() + " at " + restaurant.getName());
        } else {
            System.out.println("Sorry, " + restaurant.getName() + " doesn't serve " + customer.getPreferredCuisine() + " cuisine.");
        }
    }
}
