package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class CostumeInUseException extends WebApplicationException {
    public CostumeInUseException() {
        super("Costume is already in use!", Response.Status.FORBIDDEN);
    }
}
