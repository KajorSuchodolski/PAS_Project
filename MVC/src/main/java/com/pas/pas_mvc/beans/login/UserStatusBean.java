package com.pas.pas_mvc.beans.login;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class UserStatusBean implements Serializable {

    private String login;
    private String role;
    private String userId;

    private boolean isLoggedIn;
    private boolean admin;
    private boolean manager;
    private boolean client;

    public String getLogin() {
        return login;
    }

    public void setLogin( String login ) {
        this.login = login;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role ) {
        this.role = role;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin( boolean admin ) {
        this.admin = admin;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager( boolean manager ) {
        this.manager = manager;
    }

    public boolean isClient() {
        return client;
    }

    public void setClient( boolean client ) {
        this.client = client;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn( boolean loggedIn ) {
        isLoggedIn = loggedIn;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId( String userId ) {
        this.userId = userId;
    }
}
