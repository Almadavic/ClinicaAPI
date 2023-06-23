package com.project.clinicaapi.service.customException;

import com.project.clinicaapi.enumerated.WorkDayEnum;

import java.io.Serial;

public class DentistNotAvailableException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DentistNotAvailableException(WorkDayEnum dayOfWeek) {
        super("The dentist doesn't work that day: " + dayOfWeek);
    }

}
