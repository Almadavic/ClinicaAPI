package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidPasswordFormatException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidPasswordFormatException(String password) {
        super("The password: " + password + " contains an invalid format");
    }

}
