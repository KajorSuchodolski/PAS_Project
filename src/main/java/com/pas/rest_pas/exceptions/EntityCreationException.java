package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;

public class EntityCreationException extends WebApplicationException {
    public EntityCreationException() {
        super("The given attribute(s) are invalid!");
    }
}
