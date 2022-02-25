package com.pas.pas_mvc.beans.login;

import com.pas.pas_mvc.rest_clients.LoginRestClient;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@SessionScoped
@Named
public class ChangePasswordBean implements Serializable {

    private String password;

    @Inject
    private LoginRestClient loginRestClient;


    public String changePassword() {
        loginRestClient.changePassword(password);
        password = null;
        return "home";
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password ) {
        this.password = password;
    }
}
