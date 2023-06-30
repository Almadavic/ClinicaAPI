package com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment;

import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.Patient;
import com.project.clinicaapi.repository.AppointmentRepository;

public record UpdateAppointmentArgs(AppointmentUpdateDTO appointmentDTO, Appointment appointment,
                                    AppointmentRepository appointmentRepository)  {
}
