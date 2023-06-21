package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment;

import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.entity.Dentist;

public record RegisterAppointmentArgs(AppointmentRegisterDTO appointmentDTO, Dentist dentist) {
}
