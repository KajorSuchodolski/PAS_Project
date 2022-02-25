package com.pas.pas_mvc.beans.user;

import com.pas.pas_mvc.model.user.UserDTO;
import com.pas.pas_mvc.rest_clients.UserRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserCreateBean implements Serializable {

    @Inject
    private UserRestClient userRestClient;

    private UserDTO createdUser = new UserDTO();

    public String createUser() {
        userRestClient.postUser(createdUser);
        return "userList";
    }

    public UserDTO getCreatedUser() {
        return createdUser;
    }

    public void setCreatedUser( UserDTO createdUser ) {
        this.createdUser = createdUser;
    }

    public UserRestClient getUserRestClient() {
        return userRestClient;
    }

    public void setUserRestClient( UserRestClient userRestClient ) {
        this.userRestClient = userRestClient;
    }
}
