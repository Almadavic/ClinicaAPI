package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.TimeOrder;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Order(value = 2)
@Component
public class TimeOrderRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

       TimeOrder.verification(args.appointmentDTO().getAppointmentDate(), args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd());

    }

}
