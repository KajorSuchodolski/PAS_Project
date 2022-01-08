package com.pas.rest_pas.exceptions;
import javax.enterprise.inject.CreationException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class UserCreationException extends CreationException {
    public UserCreationException( String message) {
        super(message);
    }
}
