package com.renga.gateway.services;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Service
public class HealthMonitorService {
    private final RestTemplate restTemplate;
    private final String compositeHealthUrl;
    private final String userHealthUrl;
    private final String postHealthUrl;
    private final HttpEntity<String> getEntity;

    @Autowired
    public HealthMonitorService(RestTemplate restTemplate,
                                @Value("${healthurl.composite}") String compositeHealthUrl,
                                @Value("${healthurl.user}") String userHealthUrl,
                                @Value("${healthurl.post}") String postHealthUrl){
        this.restTemplate = restTemplate;
        this.compositeHealthUrl = compositeHealthUrl;
        this.userHealthUrl = userHealthUrl;
        this.postHealthUrl = postHealthUrl;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        this.getEntity = new HttpEntity<>(headers);
    }

    public boolean isPostServiceAvailable () {
        ResponseEntity<JsonNode> postServiceHealth =  restTemplate.exchange(this.postHealthUrl, HttpMethod.GET, getEntity, JsonNode.class);
        return postServiceHealth.getBody().get("status").asText().equalsIgnoreCase(Status.UP.getCode());
    }

    public boolean isUserServiceAvailable () {
        ResponseEntity<JsonNode> postServiceHealth =  restTemplate.exchange(this.userHealthUrl, HttpMethod.GET, getEntity, JsonNode.class);
        return postServiceHealth.getBody().get("status").asText().equalsIgnoreCase(Status.UP.getCode());
    }

    public boolean isCompositeServiceAvailable () {
        ResponseEntity<JsonNode> postServiceHealth =  restTemplate.exchange(this.compositeHealthUrl, HttpMethod.GET, getEntity, JsonNode.class);
        return postServiceHealth.getBody().get("status").asText().equalsIgnoreCase(Status.UP.getCode());
    }



}
