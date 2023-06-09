package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class CpfAlreadyRegisteredException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public CpfAlreadyRegisteredException(String cpf) {
        super("The registration: " + cpf + " already exists in the system");
    }

}
