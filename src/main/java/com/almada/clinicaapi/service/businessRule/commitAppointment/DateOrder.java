package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.DateOrderException;

import java.time.LocalDate;

public class DateOrder {

    private DateOrder() {

    }

    public static void verification(LocalDate appointmentDate) {

        if(appointmentDate.isBefore(LocalDate.now())) {
            throw new DateOrderException("The appointment date has to be today or someday after");
        }

    }

}
