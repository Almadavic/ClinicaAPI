package com.almada.clinicaapi.service.customException;

import java.io.Serial;

public class ClinicOpeningHoursException extends RuntimeException{

    @Serial
    private static final long serialVersionUID = 1L;

    public ClinicOpeningHoursException() {
        super("The clinic works from 8:00 a.m to 18:00 p.m from monday to saturday");
    }

}
