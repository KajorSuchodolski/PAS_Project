package com.pas.rest_pas.exceptions;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserAdditionException extends WebApplicationException {
    public UserAdditionException() {
        super("User with given address or login exists!", Response.Status.BAD_REQUEST);
    }
}
