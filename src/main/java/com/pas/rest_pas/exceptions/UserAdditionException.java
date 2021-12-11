package com.pas.rest_pas.exceptions;
import javax.ws.rs.WebApplicationException;

public class UserAdditionException extends WebApplicationException {
    public UserAdditionException() {
        super("User with given address or login exists!");
    }
}
