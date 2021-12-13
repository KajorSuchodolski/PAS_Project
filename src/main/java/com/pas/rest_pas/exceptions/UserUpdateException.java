package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserUpdateException extends WebApplicationException {
    public UserUpdateException() {
        super("User with given email was not found!", Response.Status.REQUEST_TIMEOUT);
    }
}
