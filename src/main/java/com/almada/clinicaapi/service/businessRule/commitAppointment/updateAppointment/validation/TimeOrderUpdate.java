package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;

import com.almada.clinicaapi.service.businessRule.commitAppointment.TimeOrder;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Order(value = 4)
@Component
public class TimeOrderUpdate implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        LocalDate appointmentDate = args.appointmentDTO().getAppointmentDate();
        LocalTime timeStart = args.appointmentDTO().getTimeStart();
        LocalTime timeEnd = args.appointmentDTO().getTimeEnd();

        if (timeStart != null) {
            TimeOrder.verification(manageLocalDate(args.appointment().getAppointmentDate(), appointmentDate), timeStart, timeEnd);
        }

    }

    private LocalDate manageLocalDate(LocalDate appointmentDate, LocalDate appointmentDateDTO) {
        if(appointmentDateDTO!=null) {
            return appointmentDateDTO;
        }
       return appointmentDate;
    }

}
