package com.renga.gateway.actuators;

import com.renga.gateway.services.HealthMonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class CompositeHealth implements HealthIndicator {
    @Autowired
    HealthMonitorService healthMonitorService;

    private final String message_key = "Composite Service";

    @Override
    public Health health() {
        if(!this.healthMonitorService.isCompositeServiceAvailable()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }
}
