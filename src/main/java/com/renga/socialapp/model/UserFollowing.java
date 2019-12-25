package com.renga.socialapp.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name = "userfollowing", schema = "public")
@EqualsAndHashCode(callSuper=false)
public class UserFollowing extends BaseModel {   
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)   
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User followee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "follower_id", nullable = false)   
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User follwer;
}