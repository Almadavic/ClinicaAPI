package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class PasswordNullException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public PasswordNullException(String msg) {
        super(msg);
    }

}
