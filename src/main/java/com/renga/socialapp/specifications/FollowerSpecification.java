package com.renga.socialapp.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.socialapp.models.User;
import com.renga.socialapp.models.UserFollower;
import com.renga.socialapp.models.UserFollower_;

import org.springframework.data.jpa.domain.Specification;

public class FollowerSpecification {
    public static Specification<UserFollower> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollower_.follower, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollower_.follower), new User(followerId));
        };

    }
}