package com.renga.socialapp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.renga.socialapp.lookups.SearchCriteria;
import com.renga.socialapp.models.User;
import com.renga.socialapp.models.UserFollower;
import com.renga.socialapp.models.UserFollowing;
import com.renga.socialapp.repositories.UserFollowerRepository;
import com.renga.socialapp.repositories.UserFollowingRepository;
import com.renga.socialapp.repositories.UserRepository;
import com.renga.socialapp.specifications.UserSpecification;

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

    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Transactional
    public void follow(UUID followerId, UUID userId) {
        UserFollowing userFollowing = new UserFollowing();
        UserFollower userFollower = new UserFollower();
        userFollowing.setFollowee(new User(userId));
        userFollower.setFollower(new User(followerId));
        userFollowingRepository.save(userFollowing);
        userFollowerRepository.save(userFollower);
    }

    @Transactional
    public void unfollow(UUID followerId, UUID userId) {
        UserFollowing userFollowing = new UserFollowing();
        UserFollower userFollower = new UserFollower();
        userFollowing.setFollowee(new User(userId));
        userFollower.setFollower(new User(followerId));
        userFollowingRepository.delete(userFollowing);
        userFollowerRepository.delete(userFollower);
    }


    public List<User> getMyFollowers(UUID followerId){
        List<User> users = new ArrayList<User>();
        List<UserFollower> userFollowers = userFollowerRepository.findByFollower(new User(followerId));
        userFollowers.forEach(follower -> {
            users.add(follower.getFollower());
        });
        return users;
    }


    public List<User> getMyFollowees(UUID userId){
        List<User> users = new ArrayList<User>();
        List<UserFollowing> userFollowees = userFollowingRepository.findByFollowee(new User(userId));
        userFollowees.forEach(follower -> {
            users.add(follower.getFollowee());
        });
        return users;
    }

    public User user(UUID userId) {
        return userRepository.findById(userId).get();
    }

    public List<User> search(String searchText, Pageable pageable) {
        UserSpecification firstNameUserSpecification = new UserSpecification(new SearchCriteria("firstName", ":", searchText));
        UserSpecification lastNameUserSpecification = new UserSpecification(new SearchCriteria("lastName", ":", searchText));
        UserSpecification nickNameUserSpecification = new UserSpecification(new SearchCriteria("nickName", ":", searchText));

        Specification<User> finalSpecification = Specification.where(firstNameUserSpecification).or(lastNameUserSpecification).or(nickNameUserSpecification);
        return userRepository.findAll(finalSpecification, pageable).toList();

    }
}