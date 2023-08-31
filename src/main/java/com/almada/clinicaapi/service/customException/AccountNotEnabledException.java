package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class AccountNotEnabledException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public AccountNotEnabledException() {
        super("In order to change your account password, your account has to be enabled");
    }

}
