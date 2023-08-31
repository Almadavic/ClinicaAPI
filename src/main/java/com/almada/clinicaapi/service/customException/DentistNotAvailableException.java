package com.almada.clinicaapi.service.customException;

import com.almada.clinicaapi.enumerated.WorkDayEnum;

import java.io.Serial;

public class DentistNotAvailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DentistNotAvailableException(WorkDayEnum dayOfWeek) {
        super("The dentist doesn't work that day: " + dayOfWeek);
    }

}
