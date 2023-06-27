package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.ClinicOpeningHoursException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value = 4)
@Component
public class AppointmentTimeLimitRegister implements RegisterAppointmentVerification {
    @Override
    public void verification(RegisterAppointmentArgs args) {

        CommitAppointmentValidations.appointmentTimeLimitValidation(args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd());

    }

}
