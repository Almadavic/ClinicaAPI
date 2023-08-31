package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.DentistAvailableDay;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 6)
@Component
public class DentistAvailableDayRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        DentistAvailableDay.verification(args.appointmentDTO().getAppointmentDate(), args.dentist());

    }

}



