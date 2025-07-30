package com.orderfood;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class AppConfig {

    @Bean
    public Customer customer() {
        return new Customer("Akshay", "1234567890", "Indian");
    }

    @Bean
    public Restaurant restaurant() {
        return new Restaurant("Food Corner", "Bangalore", List.of("Indian", "Chinese"));
    }

    @Bean
    public FoodOrderService foodOrderService() {
        return new FoodOrderService(customer(), restaurant());
    }
}
