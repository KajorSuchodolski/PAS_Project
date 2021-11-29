package com.pas.rest_pas.entities.user;

import com.pas.rest_pas.entities.Entity;
import com.pas.rest_pas.entities.access_levels.AccessLevel;

public class User extends Entity {

    private boolean isActive;
    private AccessLevel accessLevel;
    private String firstName;
    private String lastName;
    private String Address;
    private String phoneNumber;
    private String login;
    private String password;

    public User() {

    }
}
