package com.renga.services.user.services;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import com.renga.services.user.lookups.FollowFollowingCount;
import com.renga.services.user.lookups.SearchCriteria;
import com.renga.services.user.models.User;
import com.renga.services.user.models.UserFollower;
import com.renga.services.user.models.UserFollowing;
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
        List<UserFollower> userFollowers = userFollowerRepository.findAll(FollowerSpecification.getFollowers(followerId));
        userFollowers.forEach(follower -> {
            users.add(follower.getFollower());
        });
        return users;
    }


    public List<User> getMyFollowees(UUID userId){
        List<User> users = new ArrayList<User>();
        List<UserFollowing> userFollowees = userFollowingRepository.findAll(FollowingSpecification.getFollowers(userId));
        userFollowees.forEach(follower -> {
            users.add(follower.getFollowee());
        });
        return users;
    }

    public User user(UUID userId) {
        return userRepository.findById(userId).get();
    }


    public FollowFollowingCount getFollowerAndFollowingCount(UUID userId) {
        List<UserFollowing> userFollowees = userFollowingRepository.findAll(FollowingSpecification.getFollowers(userId));
        List<UserFollower> userFollowers = userFollowerRepository.findAll(FollowerSpecification.getFollowers(userId));
        return new FollowFollowingCount(userFollowees.size(), userFollowers.size());
    }

    public List<User> search(String searchText, Pageable pageable) {
        UserSpecification firstNameUserSpecification = new UserSpecification(new SearchCriteria("firstName", ":", searchText));
        UserSpecification lastNameUserSpecification = new UserSpecification(new SearchCriteria("lastName", ":", searchText));
        UserSpecification nickNameUserSpecification = new UserSpecification(new SearchCriteria("nickName", ":", searchText));

        Specification<User> finalSpecification = Specification.where(firstNameUserSpecification).or(lastNameUserSpecification).or(nickNameUserSpecification);
        return userRepository.findAll(finalSpecification, pageable).toList();

    }
}