package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.DateOrder;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(value = 3)
@Component
public class DateOrderUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalDate appointmentDate = args.appointmentDTO().getAppointmentDate();

        if (appointmentDate != null) {
            DateOrder.verification(args.appointmentDTO().getAppointmentDate());
        }

    }

}
