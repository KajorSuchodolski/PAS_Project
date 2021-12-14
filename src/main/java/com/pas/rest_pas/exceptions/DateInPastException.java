package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class DateInPastException extends WebApplicationException {
    public DateInPastException() {
        super(Response.status(Response.Status.BAD_REQUEST).entity("The given date is in past!").build());
    }
}