package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.AppointmentDurationException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Order(value = 6)
@Component
public class AppointmentDurationRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        CommitAppointmentValidations.appointmentDurationValidation(args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd());

    }

}
