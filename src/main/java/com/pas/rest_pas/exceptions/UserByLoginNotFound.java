package com.pas.rest_pas.exceptions;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;

public class UserByLoginNotFound extends NotFoundException {
    public UserByLoginNotFound() {
        super("User with given login was not found!");
    }
}
