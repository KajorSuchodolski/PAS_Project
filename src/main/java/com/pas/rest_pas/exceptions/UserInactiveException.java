package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserInactiveException extends WebApplicationException {
    public UserInactiveException() {
        super("User is inactive #badassuser", Response.Status.FORBIDDEN);
    }
}
