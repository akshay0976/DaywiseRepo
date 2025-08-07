package com.connectly.userprofileservice.service;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserProfileService {

    @Autowired
    private RestTemplate restTemplate;

    private static final String CB_NAME = "userProfileServiceCB";

    private final Map<String, String> userCache = new ConcurrentHashMap<>();

    @CircuitBreaker(name = CB_NAME, fallbackMethod = "fallbackMethod")
    public String getUserProfile(String userId) {
        String url = "http://localhost:8085/api/db/users/" + userId;

        // Simulate call to external DB or microservice
        String response = restTemplate.getForObject(url, String.class);

        // Store in cache
        userCache.put(userId, response);

        return "Success: " + response;
    }

    public String fallbackMethod(String userId, Throwable t) {
        if (userCache.containsKey(userId)) {
            return "Cached Fallback: " + userCache.get(userId);
        }

        return "Fallback: Unable to retrieve user data for ID " + userId + ". Reason: " + t.getMessage();
    }
}
