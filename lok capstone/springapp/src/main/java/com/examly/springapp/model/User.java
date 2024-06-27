package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue
    private long userId;

    private String email;
    private String password;
    private String username;
    private String mobileNumber;
    private String userRole;

}