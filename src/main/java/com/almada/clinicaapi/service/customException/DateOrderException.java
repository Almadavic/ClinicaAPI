package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class DateOrderException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DateOrderException(String msg) {
        super(msg);

    }

}
