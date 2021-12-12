package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CostumeByIdNotFound extends WebApplicationException {
    public CostumeByIdNotFound() {
        super("Costume with given ID was not found!", Response.Status.NOT_FOUND);
    }
}
