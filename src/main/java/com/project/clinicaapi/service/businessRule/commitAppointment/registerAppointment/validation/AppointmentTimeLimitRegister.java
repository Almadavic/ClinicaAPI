package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.AppointmentTimeLimit;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value = 4)
@Component
public class AppointmentTimeLimitRegister implements RegisterAppointmentVerification {
    @Override
    public void verification(RegisterAppointmentArgs args) {

        AppointmentTimeLimit.verification(args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd());

    }

}
