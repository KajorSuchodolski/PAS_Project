package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;

public class UserByLoginNotFound extends WebApplicationException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}
