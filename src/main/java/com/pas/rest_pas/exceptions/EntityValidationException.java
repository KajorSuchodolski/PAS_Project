package com.pas.rest_pas.exceptions;

import javax.validation.ValidationException;
import javax.ws.rs.core.Response;

public class EntityValidationException extends ValidationException {
    public EntityValidationException(String message) {
        super(message);
    }
}
