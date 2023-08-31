package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class NoPermissionException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public NoPermissionException(String action) {
        super("You don't have permission to: " + action);
    }

}
