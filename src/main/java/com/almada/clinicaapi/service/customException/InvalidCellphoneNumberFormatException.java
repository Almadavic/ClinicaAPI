package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidCellphoneNumberFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidCellphoneNumberFormatException(String celular) {
        super("Cellphone number entered contains an invalid format: " + celular + ", Correct format: (XX)9XXXX-XXXX");
    }

}
