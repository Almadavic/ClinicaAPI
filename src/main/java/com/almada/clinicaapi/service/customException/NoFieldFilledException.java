package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class NoFieldFilledException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public NoFieldFilledException() {
        super("You have to update at least one field");
    }

}
