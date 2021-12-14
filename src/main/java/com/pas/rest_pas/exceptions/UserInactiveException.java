package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserInactiveException extends WebApplicationException {
    public UserInactiveException() {
        super(Response.status(Response.Status.FORBIDDEN).entity("User is inactive").build());
    }
}
