package com.pas.rest_pas.exceptions;

import java.io.IOException;

public class CostumeByIdNotFound extends IOException {
    public CostumeByIdNotFound() {
        super("Costume with given ID was not found!");
    }
}
