package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.AppointmentTimeLimit;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Order(value = 6)
@Component
public class AppointmentTimeLimitUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalTime timeStart = args.appointmentDTO().getTimeStart();
        LocalTime timeEnd = args.appointmentDTO().getTimeEnd();

        if (timeStart != null) {
            AppointmentTimeLimit.verification(timeStart, timeEnd);
        }

    }

}
