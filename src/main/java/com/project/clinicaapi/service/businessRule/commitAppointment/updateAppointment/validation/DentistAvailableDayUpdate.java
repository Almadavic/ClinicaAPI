package com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.project.clinicaapi.service.businessRule.commitAppointment.DentistAvailableDay;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Order(value = 7)
@Component
public class DentistAvailableDayUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalDate appointmentDate = args.appointmentDTO().getAppointmentDate();

        if(appointmentDate != null) {
            DentistAvailableDay.verification(appointmentDate, args.appointment().getDentist());
        }

    }

}
