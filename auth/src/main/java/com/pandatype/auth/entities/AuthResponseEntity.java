package com.pandatype.auth.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponseEntity {
    private String status;
    private String message;
    private String username;

    public AuthResponseEntity(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public AuthResponseEntity(String status, String message, String username) {
        this.status = status;
        this.message = message;
        this.username = username;
    }

    public AuthResponseEntity() {

    }
}