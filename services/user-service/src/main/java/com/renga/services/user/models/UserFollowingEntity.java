package com.renga.services.user.models;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "userfollowing", schema = "public")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class UserFollowingEntity extends BaseModel {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity followee;
}