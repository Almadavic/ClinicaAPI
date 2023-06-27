package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.DentistNotAvailableException;
import com.project.clinicaapi.util.ConvertingType;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Order(value = 5)
@Component
public class DentistAvailableDayRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        CommitAppointmentValidations.dentistAvailableDayValidation(args.appointmentDTO().getAppointmentDate(), args.dentist());

    }

}



