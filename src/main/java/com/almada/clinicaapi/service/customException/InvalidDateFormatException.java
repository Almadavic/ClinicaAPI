package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class InvalidDateFormatException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public InvalidDateFormatException() {
        super("Invalid date format, Valid format: dd/MM/yyyy");
    }

    public InvalidDateFormatException(String dateField) {
        super("The field " + dateField + " has a wrong format, Valid date format: yyyy-mm-dd");
    }

}
