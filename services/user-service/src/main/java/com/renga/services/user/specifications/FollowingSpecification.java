package com.renga.services.user.specifications;

import java.util.UUID;

import javax.persistence.criteria.JoinType;

import com.renga.services.user.models.UserEntity;
import com.renga.services.user.models.UserFollowingEntity;
import com.renga.services.user.models.UserFollowingEntity_;

import org.springframework.data.jpa.domain.Specification;

public class FollowingSpecification {
    public static Specification<UserFollowingEntity> getFollowers (UUID followerId) {
        return (root, query, criteriaBuilder) -> {
            root.fetch(UserFollowingEntity_.followee, JoinType.INNER);
            return criteriaBuilder.equal(root.get(UserFollowingEntity_.followee), new UserEntity(followerId));
        };

    }
}