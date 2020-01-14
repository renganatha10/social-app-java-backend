package com.renga.api.models;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
public class User extends BaseModel {
    private String email;
    private Gender gender;
    private String firstName;
    private String lastName;
    private String nickName;
    private Date dateOfBirth;
    private String bio;
    private String coverPhoto;
    private String profilePhoto;
    public enum Gender {
		  M, F
    }
}