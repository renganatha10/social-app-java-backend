package com.renga.services.composite.actuators;

import com.renga.services.composite.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;


@Component
public class PostServiceHealth implements HealthIndicator {

    @Autowired
    private PostService postService;

    private final String message_key = "Post Service";

    @Override
    public Health health() {
        if(!postService.isPostServiceAvailable()) {
            return Health.down().withDetail(message_key, "Not Available").build();
        }
        return Health.up().withDetail(message_key, "Available").build();
    }
}
