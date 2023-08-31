package com.almada.clinicaapi.service.businessRule.commitAppointment.registerAppointment;

import com.almada.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.entity.Patient;
import com.almada.clinicaapi.repository.AppointmentRepository;

public record RegisterAppointmentArgs(AppointmentRegisterDTO appointmentDTO, Dentist dentist, Patient patient, AppointmentRepository appointmentRepository) {
}
