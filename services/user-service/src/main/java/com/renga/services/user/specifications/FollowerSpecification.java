package com.renga.services.user.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.services.user.models.UserEntity;
import com.renga.services.user.models.UserFollowerEntity;
import com.renga.services.user.models.UserFollowerEntity_;

import org.springframework.data.jpa.domain.Specification;

public class FollowerSpecification {
    public static Specification<UserFollowerEntity> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollowerEntity_.follower, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollowerEntity_.follower), new UserEntity(followerId));
        };

    }
}