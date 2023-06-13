package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class PasswordNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordNullException() {
        super("In order to register your account and set a password, you have to enter the fields 'password' and 'passwordconfirmation'.");
    }

}
