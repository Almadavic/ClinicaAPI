package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidInstanceException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidInstanceException(String type) {
        super("Invalid instance, you have to enter a " + type + "type");
    }

}
