package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class EmailAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public EmailAlreadyRegisteredException(String email) {
        super("The email: " + email + " already exists in the system");
    }

}
