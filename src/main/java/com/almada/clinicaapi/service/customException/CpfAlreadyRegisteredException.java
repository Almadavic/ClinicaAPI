package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class CpfAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CpfAlreadyRegisteredException(String cpf) {
        super("The cpf: " + cpf + " already exists in the system");
    }

}
