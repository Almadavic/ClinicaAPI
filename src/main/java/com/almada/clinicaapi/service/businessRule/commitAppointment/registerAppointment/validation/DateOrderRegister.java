package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.DateOrder;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 1)
@Component
public class DateOrderRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        DateOrder.verification(args.appointmentDTO().getAppointmentDate());

    }

}
