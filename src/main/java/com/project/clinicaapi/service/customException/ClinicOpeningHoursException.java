package com.project.clinicaapi.service.customException;

import java.io.Serial;

public class ClinicOpeningHoursException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ClinicOpeningHoursException(String msg) {
        super(msg);
    }

}
