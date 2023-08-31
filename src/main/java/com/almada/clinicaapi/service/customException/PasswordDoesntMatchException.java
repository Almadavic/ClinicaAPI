package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class PasswordDoesntMatchException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordDoesntMatchException() {
        super("The value of the fields password and passwordconfirmation don't match");
    }

}
