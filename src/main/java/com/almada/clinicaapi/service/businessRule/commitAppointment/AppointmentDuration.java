package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.AppointmentDurationException;

import java.time.Duration;
import java.time.LocalTime;

public class AppointmentDuration {

    private AppointmentDuration() {

    }

    public static void verification(LocalTime timeStart, LocalTime timeEnd) {

        Duration duration = Duration.between(timeStart, timeEnd);

        if (duration.toMinutes() > 60 || duration.toMinutes() < 30) {
            throw new AppointmentDurationException();
        }
    }

}
