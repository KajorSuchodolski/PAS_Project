package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;

public class UserUpdateException extends WebApplicationException {
    public UserUpdateException() {
        super("User with given email was not found!");
    }
}
