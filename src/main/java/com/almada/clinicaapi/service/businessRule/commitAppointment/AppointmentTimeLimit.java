package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.ClinicOpeningHoursException;

import java.time.LocalTime;

public class AppointmentTimeLimit {

    private AppointmentTimeLimit() {

    }

    public static void verification(LocalTime timeStart, LocalTime endTime) {

        LocalTime clinicOpeningTime = LocalTime.of(8, 0);
        LocalTime clinicClosingTime = LocalTime.of(18, 0);

        if(timeStart.isBefore(clinicOpeningTime) || endTime.isAfter(clinicClosingTime)) {
            throw new ClinicOpeningHoursException();
        }

    }

}
