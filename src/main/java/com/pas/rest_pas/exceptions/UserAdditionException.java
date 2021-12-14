package com.pas.rest_pas.exceptions;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserAdditionException extends WebApplicationException {
    public UserAdditionException() {
        super(Response.status(Response.Status.BAD_REQUEST).entity("User with given address or login exists!").build());
    }
}
