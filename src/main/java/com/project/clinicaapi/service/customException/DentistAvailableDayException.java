package com.project.clinicaapi.service.customException;

import com.project.clinicaapi.enumerated.WorkDayEnum;

import java.io.Serial;
import java.time.DayOfWeek;

public class DentistAvailableDayException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 1L;

    public DentistAvailableDayException(WorkDayEnum dayOfWeek) {
        super("The dentist doesn't work that day: " + dayOfWeek);
    }

}
