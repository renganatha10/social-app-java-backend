package com.renga.socialapp.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.socialapp.models.User;
import com.renga.socialapp.models.UserFollowing;
import com.renga.socialapp.models.UserFollowing_;

import org.springframework.data.jpa.domain.Specification;

public class FollowingSpecification {
    public static Specification<UserFollowing> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollowing_.followee, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollowing_.followee), new User(followerId));
        };

    }
}