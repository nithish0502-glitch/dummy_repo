package com.examly.springapp.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthUser {
    long userId;
    String email;
    String token;
    String userRole;

    public AuthUser(){}
    public AuthUser(long userId, String email, String token, String userRole) {
        this.userId = userId;
        this.email = email;
        this.token = token;
        this.userRole = userRole;
    }
    
    
}
