package com.renga.services.user.specifications;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.renga.services.user.lookups.SearchCriteria;
import com.renga.services.user.models.UserEntity;

import org.springframework.data.jpa.domain.Specification;

public class UserSpecification implements Specification<UserEntity> {
    private static final long serialVersionUID = 1L;

	private  SearchCriteria criteria;

    public UserSpecification(SearchCriteria criteria) {
        this.criteria = criteria;
    }


    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        if(criteria.getCondition().equalsIgnoreCase(":")){
            if(root.get(criteria.getKey()).getJavaType().equals(String.class)){
                return  criteriaBuilder.like(criteriaBuilder.lower(root.<String>get(criteria.getKey())),"%"+criteria.getValue().toLowerCase()+"%");
            }
            else{
                return criteriaBuilder.equal(root.get(criteria.getKey()),criteria.getValue());
            }
        }
        return null;
    }


}