package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityValidationException extends WebApplicationException {
    public EntityValidationException(String message) {
        super(Response.status(Response.Status.BAD_REQUEST).entity(message).build());
    }
}
