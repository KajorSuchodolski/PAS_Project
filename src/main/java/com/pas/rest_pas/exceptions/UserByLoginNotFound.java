package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserByLoginNotFound extends WebApplicationException {
    public UserByLoginNotFound() {
        super(Response.status(Response.Status.NOT_FOUND).entity("User with given login was not found!").build());
    }
}
