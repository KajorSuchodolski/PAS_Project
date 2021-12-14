package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.io.IOException;

public class RentByIdNotFound extends WebApplicationException {
    public RentByIdNotFound() {
        super(Response.status(Response.Status.NOT_FOUND).entity("Rent with given ID was not found!").build());
    }
}
