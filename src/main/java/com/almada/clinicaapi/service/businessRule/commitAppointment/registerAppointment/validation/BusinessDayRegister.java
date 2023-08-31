package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.BusinessDay;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(value = 3)
@Component
public class BusinessDayRegister implements RegisterAppointmentVerification {

    @Override
    public void verification(RegisterAppointmentArgs args) {

        BusinessDay.verification(args.appointmentDTO().getAppointmentDate().getDayOfWeek());

    }

}
