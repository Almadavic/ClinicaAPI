package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class ParameterMissingException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public ParameterMissingException(String msg) {
        super(msg);
    }

}
