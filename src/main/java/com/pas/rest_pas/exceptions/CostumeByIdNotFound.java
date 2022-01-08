package com.pas.rest_pas.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class CostumeByIdNotFound extends NotFoundException {
    public CostumeByIdNotFound() {
        super(Response.status(Response.Status.NOT_FOUND).entity("Costume with given ID was not found!").build());
    }
}
