package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.ClinicOpeningHoursException;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Order(value = 3)
@Component
public class DayOfTheWeekRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        CommitAppointmentValidations.dayOfTheWeekValidation(args.appointmentDTO().getAppointmentDate().getDayOfWeek());

    }

}
