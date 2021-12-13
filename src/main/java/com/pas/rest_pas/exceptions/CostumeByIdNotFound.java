package com.pas.rest_pas.exceptions;

import javax.ws.rs.WebApplicationException;
import java.io.IOException;

public class CostumeByIdNotFound extends WebApplicationException {
    public CostumeByIdNotFound() {
        super("Costume with given ID was not found!");
    }
}
