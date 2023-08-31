package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.DateOrderException;

import java.time.LocalDate;
import java.time.LocalTime;

public class TimeOrder {

    private TimeOrder() {

    }

    public static void verification(LocalDate localDate, LocalTime timeStart, LocalTime timeEnd) {

        if(timeStart.isAfter(timeEnd) || (localDate.isEqual(LocalDate.now()) && timeStart.isBefore(LocalTime.now()))) {

            throw new DateOrderException("The timestart of the appointment cannot be after timeend and timestart cannot be in the past");
        }

    }

}
