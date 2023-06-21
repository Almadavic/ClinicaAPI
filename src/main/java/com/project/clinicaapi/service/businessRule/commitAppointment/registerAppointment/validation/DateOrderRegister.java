package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.DateOrderException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(value = 1)
@Component
public class DateOrderRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        LocalDate appointmentDate = args.appointmentDTO().getAppointmentDate();

        if(appointmentDate.isBefore(LocalDate.now())) {
            throw new DateOrderException("The appointment date has to be today or someday after");
        }

    }

}
