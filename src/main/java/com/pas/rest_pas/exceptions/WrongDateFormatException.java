package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class WrongDateFormatException extends WebApplicationException {
    public WrongDateFormatException() {
        super(Response.status(Response.Status.BAD_REQUEST).entity("Wrong date format!").build());
    }
}
