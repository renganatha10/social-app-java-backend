package com.renga.gateway.actuators;

import com.renga.gateway.services.HealthMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class UserHealth implements HealthIndicator {
    @Autowired
    HealthMonitorService healthMonitorService;

    private final String message_key = "User Service";

    @Override
    public Health health() {
        if(!this.healthMonitorService.isUserServiceAvailable()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }
}
