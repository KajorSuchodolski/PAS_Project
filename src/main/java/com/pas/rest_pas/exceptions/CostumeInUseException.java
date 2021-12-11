package com.pas.rest_pas.exceptions;

import java.io.IOException;

public class CostumeInUseException extends IOException {
    public CostumeInUseException() {
        super("Costume is already in use!");
    }
}
