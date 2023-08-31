package com.almada.clinicaapi.service.customException;


import java.io.Serial;

public class DisableOwnAccountException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public DisableOwnAccountException() {
        super("You cannot disable your own account");
    }

}
