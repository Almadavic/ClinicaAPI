package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation;


import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentVerification;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;


@Order(value = 11)
@Component
public class UpdatingAppointmentData implements UpdateAppointmentVerification {

    @Override
    public void verification(UpdateAppointmentArgs args) {

        AppointmentUpdateDTO appointmentDTO = args.appointmentDTO();
        Appointment appointment = args.appointment();
        setProcedure(appointmentDTO, appointment);
        setAppointmentDate(appointmentDTO, appointment);
        setTimeStart(appointmentDTO, appointment);
        setTimeEnd(appointmentDTO, appointment);

    }

    private void setProcedure(AppointmentUpdateDTO appointmentDTO, Appointment appointment) {
        String procedure = appointmentDTO.getProcedure();
        if (procedure != null) {
            appointment.setProcedure(procedure);
        }
    }

    private void setAppointmentDate(AppointmentUpdateDTO appointmentDTO, Appointment appointment) {
        LocalDate appointmentDate = appointmentDTO.getAppointmentDate();
        if (appointmentDate != null) {
            appointment.setAppointmentDate(appointmentDate);
        }
    }

    private void setTimeStart(AppointmentUpdateDTO appointmentDTO, Appointment appointment) {
        LocalTime timeStart = appointmentDTO.getTimeStart();
        if (timeStart != null) {
            appointment.setTimeStart(timeStart);
        }
    }

    private void setTimeEnd(AppointmentUpdateDTO appointmentDTO, Appointment appointment) {
        LocalTime timeEnd = appointmentDTO.getTimeEnd();
        if (timeEnd != null) {
            appointment.setTimeEnd(timeEnd);
        }
    }

}
