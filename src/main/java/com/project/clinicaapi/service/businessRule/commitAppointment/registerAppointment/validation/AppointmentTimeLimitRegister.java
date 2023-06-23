package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.ClinicOpeningHoursException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Order(value = 2)
@Component
public class AppointmentTimeLimitRegister implements RegisterAppointmentVerification {
    @Override
    public void verification(RegisterAppointmentArgs args) {

        LocalTime clinicOpeningTime = LocalTime.of(8, 0);
        LocalTime clinicClosingTime = LocalTime.of(18, 0);

        if(args.appointmentDTO().getTimeStart().isBefore(clinicOpeningTime) || args.appointmentDTO().getTimeEnd().isAfter(clinicClosingTime)) {
            throw new ClinicOpeningHoursException("The clinic works from 8:00 a.m to 18:00 p.m ");
        }

    }

}
