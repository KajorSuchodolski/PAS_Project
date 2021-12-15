package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class EndRentBeforeBeginException extends WebApplicationException {
    public EndRentBeforeBeginException() {
        super(Response.status(Response.Status.BAD_REQUEST).entity("Setting end rent date before begin date is forbidden.").build());
    }
}
