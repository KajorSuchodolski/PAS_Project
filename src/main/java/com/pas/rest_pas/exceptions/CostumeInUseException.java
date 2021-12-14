package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CostumeInUseException extends WebApplicationException {
    public CostumeInUseException() {
        super(Response.status(Response.Status.FORBIDDEN).entity("Costume is already in use!").build());
    }
}
