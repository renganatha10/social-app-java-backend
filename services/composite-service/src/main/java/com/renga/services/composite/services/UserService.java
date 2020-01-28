package com.renga.services.composite.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.renga.api.models.FollowFollowingCount;
import com.renga.api.models.FollowUnFollowBody;
import com.renga.api.models.PostCount;
import com.renga.api.models.User;
import com.renga.services.composite.lookups.UserDetail;
import com.renga.services.composite.mappers.UserDetailMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.actuate.health.Status;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private RestTemplate restTemplate;
    private UserDetailMapper userDetailMapper;
    private final String UserServiceUrl;
    private HttpEntity<String> getEntity;
    private HttpHeaders headers;
    private final String postServiceUrl;
    private  final String userServiceHealthUrl;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(RestTemplate restTemplate,
                       UserDetailMapper userDetailMapper,
                       @Value("${services.user}") String userServiceUrl,
                       @Value("${services.post}") String postServiceUrl,
                       @Value("${healthurl.user}") final String userServiceHealthUrl) {
        this.restTemplate = restTemplate;
        this.userDetailMapper = userDetailMapper;
        this.UserServiceUrl = userServiceUrl;
        this.postServiceUrl = postServiceUrl;
        this.userServiceHealthUrl = userServiceHealthUrl;

        this.headers = new HttpHeaders();
        this.headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        this.getEntity = new HttpEntity<>(this.headers);
    }

    public UserDetail getUserById(UUID userId) {
        String UserDetailUrl = this.UserServiceUrl + "?userId=" + userId;
        String UserFollowerFollowingCountUrl = this.UserServiceUrl + "followFollowingCount/" + userId;
        String postCountUrl = this.postServiceUrl + "count/" + userId;

        ResponseEntity<User> userResponse = restTemplate.exchange(UserDetailUrl, HttpMethod.GET, getEntity, User.class);
        ResponseEntity<FollowFollowingCount> countResponse = restTemplate.exchange(UserFollowerFollowingCountUrl, HttpMethod.GET, getEntity, FollowFollowingCount.class);
        ResponseEntity<PostCount> postCountResponse = restTemplate.exchange(postCountUrl, HttpMethod.GET, getEntity, PostCount.class);

        User user = userResponse.getBody();
        FollowFollowingCount followFollowingCount = countResponse.getBody();

        UserDetail userDetail = userDetailMapper.toUserDetailFromUser(user);
        userDetail.setFollowerCount((double) followFollowingCount.getFollowingCount());
        userDetail.setFollowerCount((double) followFollowingCount.getFollowerCount());
        userDetail.setPostCount((double) postCountResponse.getBody().getCount());
        return  userDetail;
    }

    public List<User> getMyFollowers(UUID userId) {
        String userFollowersUrl = this.UserServiceUrl + "followers";
        ResponseEntity<User[]> followerResponse = restTemplate.exchange(userFollowersUrl, HttpMethod.GET, getEntity, User[].class);
        return Arrays.asList(followerResponse.getBody());
    }

    public List<User> getMyFollowings(UUID userId) {
        String userFollowingUrl = this.UserServiceUrl + "followings";
        ResponseEntity<User[]> followingResponse = restTemplate.exchange(userFollowingUrl, HttpMethod.GET, getEntity, User[].class);
        return Arrays.asList(followingResponse.getBody());
    }

    public List<User> search(String searchText, String sort, String direction) {
        String searchUrl = this.UserServiceUrl + "search?searchText=" + searchText + "&sort=" + sort + "&direction=" + direction;
        ResponseEntity<User[]> followingResponse = restTemplate.exchange(searchUrl, HttpMethod.GET, getEntity, User[].class);
        return Arrays.asList(followingResponse.getBody());
    }

    public void createUser(User user) {
        HttpEntity<User> entity = new HttpEntity<User>(user,this.headers);
        restTemplate.exchange(this.UserServiceUrl, HttpMethod.POST, entity, String.class);
    }

    public void follow(UUID followerId, UUID userId) {
        String followUrl = this.UserServiceUrl + "follow";
        FollowUnFollowBody followUnFollowBody = new FollowUnFollowBody(userId.toString(), followerId.toString());
        followUnFollowBody.setFollowerId(followerId.toString());
        followUnFollowBody.setFollowerId(userId.toString());
        HttpEntity<FollowUnFollowBody> entity = new HttpEntity<>(followUnFollowBody, this.headers);
        restTemplate.exchange(followUrl, HttpMethod.POST, entity, String.class);
    }

    public void unfollow(UUID followerId, UUID userId) {
        String unFollowUrl = this.UserServiceUrl + "unfollow";
        FollowUnFollowBody followUnFollowBody = new FollowUnFollowBody(followerId.toString(), userId.toString());
        HttpEntity<FollowUnFollowBody> entity = new HttpEntity<>(followUnFollowBody, this.headers);
        restTemplate.exchange(unFollowUrl, HttpMethod.POST, entity, String.class);
    }

    public boolean isUserServiceAvailable () {
        LOG.debug("i am called");
        ResponseEntity<JsonNode> postServiceHealth =  restTemplate.exchange(this.userServiceHealthUrl, HttpMethod.GET, getEntity, JsonNode.class);
        return postServiceHealth.getBody().get("status").asText().equalsIgnoreCase(Status.UP.getCode());
    }

}
