package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.almada.clinicaapi.service.customException.UserNotEnable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 5)
@Component
public class DentistEnabled implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        if(!args.dentist().isEnabled()) {
            throw new UserNotEnable("dentist");
        }

    }

}
