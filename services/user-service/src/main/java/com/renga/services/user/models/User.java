package com.renga.services.user.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;


import org.hibernate.validator.constraints.URL;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Data
@Entity
@Table(name = "user", schema = "public")
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class User extends BaseModel {

    public User(UUID id) {
        this.setId(id);
    }

    @Email
    @NotNull(message = "Please Select Email")
    private String email;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Please Select Gender")
    private Gender gender;

    private String firstName;

    private String lastName;

    @Column(unique=true)
    @NotNull(message = "Please Select NickName")
    private String nickName;

    @Past
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfBirth;

    private String bio;

    @URL
    private String coverPhoto;

    @URL
    private String profilePhoto;


    public enum Gender {
		  M, F
    }
}