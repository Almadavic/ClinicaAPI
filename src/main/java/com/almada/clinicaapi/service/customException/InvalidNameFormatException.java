package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidNameFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidNameFormatException(String nome) {
        super("The name: " + nome + " contains an invalid format");
    }

}
