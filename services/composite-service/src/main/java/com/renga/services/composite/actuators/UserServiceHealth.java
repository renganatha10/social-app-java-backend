package com.renga.services.composite.actuators;

import com.renga.services.composite.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class UserServiceHealth implements HealthIndicator {
    @Autowired
    private UserService userService;

    private final String message_key = "User Service";

    @Override
    public Health health() {
        if(!userService.isUserServiceAvailable()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }

}
