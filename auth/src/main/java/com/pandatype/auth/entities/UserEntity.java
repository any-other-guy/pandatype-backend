package com.pandatype.auth.entities;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class UserEntity {
    private Long userId;

    private String username;

    private String email;

    private String password;

    private String userRole;

    public UserEntity() {
    }

    public UserEntity(Long userId, String username, String email, String password) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.userRole = "user"; // for now

    }


}