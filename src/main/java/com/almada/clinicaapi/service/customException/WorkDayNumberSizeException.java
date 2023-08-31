package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class WorkDayNumberSizeException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public WorkDayNumberSizeException() {
        super("The day of the week needs to be between 1 and 6");
    }

}
