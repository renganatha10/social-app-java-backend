package com.renga.services.user.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.services.user.models.User;
import com.renga.services.user.models.UserFollowing;
import com.renga.services.user.models.UserFollowing_;

import org.springframework.data.jpa.domain.Specification;

public class FollowingSpecification {
    public static Specification<UserFollowing> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollowing_.followee, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollowing_.followee), new User(followerId));
        };

    }
}