package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class RegistrationAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public RegistrationAlreadyRegisteredException(String registration) {
        super("The registration: " + registration + " already exists in the system");
    }

}
