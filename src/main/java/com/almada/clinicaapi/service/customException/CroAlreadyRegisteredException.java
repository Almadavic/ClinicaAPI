package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class CroAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CroAlreadyRegisteredException(String cro) {
        super("The cro: " + cro + " already exists in the system");
    }

}
