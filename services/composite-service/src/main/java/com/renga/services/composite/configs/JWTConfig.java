package com.renga.services.composite.configs;

import com.renga.api.utils.JWTUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JWTConfig {
    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil();
    }
}
