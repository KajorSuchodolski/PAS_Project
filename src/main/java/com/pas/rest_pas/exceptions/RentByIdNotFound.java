package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;

public class RentByIdNotFound extends WebApplicationException {
    public RentByIdNotFound() {
        super("Rent with given ID was not found!");
    }
}
