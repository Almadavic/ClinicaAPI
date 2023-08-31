package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class CellphoneAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CellphoneAlreadyRegisteredException(String cellphone) {
        super("The cellphone: " + cellphone + " already exists in the system");
    }

}
