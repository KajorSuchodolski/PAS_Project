package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EntityValidationException extends WebApplicationException {
    public EntityValidationException() {
        super("Invalid data given!", Response.Status.BAD_REQUEST);
    }
}
