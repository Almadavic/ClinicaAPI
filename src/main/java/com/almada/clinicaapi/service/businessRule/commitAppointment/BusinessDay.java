package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.ClinicOpeningHoursException;

import java.time.DayOfWeek;

public class BusinessDay {

    private BusinessDay() {

    }

    public static void verification(DayOfWeek dayOfWeek) {

        if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new ClinicOpeningHoursException();
        }

    }

}
