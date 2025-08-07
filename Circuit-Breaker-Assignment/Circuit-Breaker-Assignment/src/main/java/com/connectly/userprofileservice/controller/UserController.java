package com.connectly.userprofileservice.controller;

import com.connectly.userprofileservice.service.UserProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserProfileService userProfileService;

    @GetMapping("/{userId}")
    public String getUserProfile(@PathVariable String userId) {
        return userProfileService.getUserProfile(userId);
    }

    // Simulated external DB or microservice
    @GetMapping("/db/users/{userId}")
    public String getMockDbUser(@PathVariable String userId) {
        if (userId.equals("fail")) {
            throw new RuntimeException("Simulated DB failure");
        }
        return "UserData-" + userId;
    }
}
