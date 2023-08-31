package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.AppointmentDuration;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Order(value = 8)
@Component
public class AppointmentDurationUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalTime timeStart = args.appointmentDTO().getTimeStart();
        LocalTime timeEnd = args.appointmentDTO().getTimeEnd();

        if (timeStart != null) {
            AppointmentDuration.verification(timeStart, timeEnd);
        }

    }

}
