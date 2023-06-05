package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidEnumValueException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidEnumValueException(String valor, String enumType, String listEnums) {
       super("The value you sent: "+valor+" to the type "+enumType+" is not valid, valid values: "+listEnums);
    }

}
