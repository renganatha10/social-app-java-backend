package com.renga.services.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.renga.api.models.FollowFollowingCount;
import com.renga.api.models.User;
import com.renga.services.user.lookups.SearchCriteria;
import com.renga.services.user.mappers.UserMapper;
import com.renga.services.user.models.UserEntity;
import com.renga.services.user.models.UserFollowerEntity;
import com.renga.services.user.models.UserFollowingEntity;
import com.renga.services.user.repositories.UserFollowerRepository;
import com.renga.services.user.repositories.UserFollowingRepository;
import com.renga.services.user.repositories.UserRepository;
import com.renga.services.user.specifications.FollowerSpecification;
import com.renga.services.user.specifications.FollowingSpecification;
import com.renga.services.user.specifications.UserSpecification;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFollowerRepository userFollowerRepository;

    @Autowired
    private UserFollowingRepository userFollowingRepository;

    @Autowired
    private UserMapper userMapper;

    public void createUser(UserEntity user) {
        userMapper.entityToApi(userRepository.save(user));
    }

    @Transactional
    public void follow(UUID followerId, UUID userId) {
        UserFollowingEntity userFollowing = new UserFollowingEntity();
        UserFollowerEntity userFollower = new UserFollowerEntity();
        userFollowing.setFollowee(new UserEntity(userId));
        userFollower.setFollower(new UserEntity(followerId));
        userFollowingRepository.save(userFollowing);
        userFollowerRepository.save(userFollower);
    }

    @Transactional
    public void unfollow(UUID followerId, UUID userId) {
        UserFollowingEntity userFollowing = new UserFollowingEntity();
        UserFollowerEntity userFollower = new UserFollowerEntity();
        userFollowing.setFollowee(new UserEntity(userId));
        userFollower.setFollower(new UserEntity(followerId));
        userFollowingRepository.delete(userFollowing);
        userFollowerRepository.delete(userFollower);
    }


    public List<User> getMyFollowers(UUID followerId){
        List<UserEntity> users = new ArrayList<>();
        List<UserFollowerEntity> userFollowers = userFollowerRepository.findAll(FollowerSpecification.getFollowers(followerId));
        userFollowers.forEach(follower -> {
            users.add(follower.getFollower());
        });
        return userMapper.entityListToApiList(users);
    }


    public List<User> getMyFollowees(UUID userId){
        List<UserEntity> users = new ArrayList<>();
        List<UserFollowingEntity> userFollowees = userFollowingRepository.findAll(FollowingSpecification.getFollowers(userId));
        userFollowees.forEach(follower -> {
            users.add(follower.getFollowee());
        });
        return userMapper.entityListToApiList(users);
    }

    public User user(String email, String userId) {
        if(email != null) {
            return userMapper.entityToApi(userRepository.findByEmail(email).get());
        }
        return userMapper.entityToApi(userRepository.findById(UUID.fromString(userId)).get());
    }


    public FollowFollowingCount getFollowerAndFollowingCount(UUID userId) {
        List<UserFollowingEntity> userFollowees = userFollowingRepository.findAll(FollowingSpecification.getFollowers(userId));
        List<UserFollowerEntity> userFollowers = userFollowerRepository.findAll(FollowerSpecification.getFollowers(userId));
        return new FollowFollowingCount(userFollowees.size(), userFollowers.size());
    }

    public List<User> search(String searchText, Pageable pageable) {
        UserSpecification firstNameUserSpecification = new UserSpecification(new SearchCriteria("firstName", ":", searchText));
        UserSpecification lastNameUserSpecification = new UserSpecification(new SearchCriteria("lastName", ":", searchText));
        UserSpecification nickNameUserSpecification = new UserSpecification(new SearchCriteria("nickName", ":", searchText));

        Specification<UserEntity> finalSpecification = Specification.where(firstNameUserSpecification).or(lastNameUserSpecification).or(nickNameUserSpecification);
        return userMapper.entityListToApiList(userRepository.findAll(finalSpecification, pageable).toList());

    }
}
