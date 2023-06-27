package com.project.clinicaapi.service.businessRule.commitAppointment;

import com.project.clinicaapi.service.customException.ClinicOpeningHoursException;

import java.time.DayOfWeek;

public class BusinessDay {

    private BusinessDay() {

    }

    public static void verification(DayOfWeek dayOfWeek) {

        if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new ClinicOpeningHoursException("Sunday is not a valid day, we work from monday to saturday");
        }

    }

}
