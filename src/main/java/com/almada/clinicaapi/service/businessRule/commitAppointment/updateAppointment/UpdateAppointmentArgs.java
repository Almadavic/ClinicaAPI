package com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment;

import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.repository.AppointmentRepository;

public record UpdateAppointmentArgs(AppointmentUpdateDTO appointmentDTO, Appointment appointment,
                                    AppointmentRepository appointmentRepository)  {
}
