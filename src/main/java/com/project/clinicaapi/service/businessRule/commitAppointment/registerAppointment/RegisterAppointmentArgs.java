package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment;

import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;

public record RegisterAppointmentArgs(AppointmentRegisterDTO appointmentDTO, Dentist dentist, Patient patient) {
}
