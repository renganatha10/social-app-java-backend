package com.renga.services.composite.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.renga.api.models.User;

import java.util.ArrayList;
import java.util.Arrays;


@Service
public class CustomUserDetailService implements UserDetailsService {

    private final RestTemplate restTemplate;
    private final String UserServiceUrl;
    private HttpEntity<String> getEntity;
    private HttpHeaders headers;

    @Autowired
    public CustomUserDetailService(final RestTemplate restTemplate, @Value("${services.user}") String userServiceUrl) {
        this.restTemplate = restTemplate;
        this.UserServiceUrl = userServiceUrl;

        this.headers = new HttpHeaders();
        this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        this.getEntity = new HttpEntity<>(this.headers);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        String getUserDetailUrl = UserServiceUrl + username;
        ResponseEntity<User> userResponse = restTemplate.exchange(getUserDetailUrl, HttpMethod.GET, getEntity, User.class);
        User user = userResponse.getBody();
        return new  org.springframework.security.core.userdetails.User(user.getId().toString(), user.getPassword(), new ArrayList<>());
    }
}
