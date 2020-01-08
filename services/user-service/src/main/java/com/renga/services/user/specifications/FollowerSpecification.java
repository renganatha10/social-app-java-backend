package com.renga.services.user.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.services.user.models.User;
import com.renga.services.user.models.UserFollower;
import com.renga.services.user.models.UserFollower_;

import org.springframework.data.jpa.domain.Specification;

public class FollowerSpecification {
    public static Specification<UserFollower> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollower_.follower, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollower_.follower), new User(followerId));
        };

    }
}