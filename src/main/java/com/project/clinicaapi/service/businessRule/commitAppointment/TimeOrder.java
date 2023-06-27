package com.project.clinicaapi.service.businessRule.commitAppointment;

import com.project.clinicaapi.service.customException.DateOrderException;

import java.time.LocalTime;

public class TimeOrder {

    private TimeOrder() {

    }

    public static void verification(LocalTime timeStart, LocalTime timeEnd) {

        if(timeStart.isAfter(timeEnd)) {
            throw new DateOrderException("The timestart of the appointment cannot be after timeend");
        }

    }

}
