package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidCroFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCroFormatException(String cro) {
        super("The cro: " + cro + " contains an invalid format");
    }

}
