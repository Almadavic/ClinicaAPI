package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class LoginAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public LoginAlreadyRegisteredException(String login) {
        super("The login: " + login + " already exists in the system");
    }

}
