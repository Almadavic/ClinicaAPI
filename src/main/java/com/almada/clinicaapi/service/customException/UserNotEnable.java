package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class UserNotEnable extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotEnable(String userType) {
        super("This " + userType + " account is not enabled");
    }

}
