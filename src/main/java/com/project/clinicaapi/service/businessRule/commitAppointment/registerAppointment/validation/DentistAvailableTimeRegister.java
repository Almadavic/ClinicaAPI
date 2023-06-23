package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import lombok.RequiredArgsConstructor;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Order(value = 7)
@Component
@RequiredArgsConstructor
public class DentistAvailableTimeRegister implements RegisterAppointmentVerification {

    private final AppointmentRepository appointmentRepository;

    @Override
    public void verification(RegisterAppointmentArgs args) {

        List<Appointment> appointments = appointmentRepository.findByDentistAndByDate(args.dentist().getId(), args.appointmentDTO().getAppointmentDate());

        appointments.forEach(a -> verifyAvailability(a, args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd()));

    }

    private boolean verifyAvailability(Appointment appointment, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return startTimeWithinAnotherAppointment(startTimeDTO, appointment) || endTimeWithinAnotherAppointment(endTimeDTO, appointment);
    }

    private boolean startTimeWithinAnotherAppointment(LocalTime appointmentStartTimeDTO, Appointment appointment) {
        return appointmentStartTimeDTO.isAfter(appointment.getTimeStart()) && appointmentStartTimeDTO.isBefore(appointment.getTimeEnd());
    }

    private boolean endTimeWithinAnotherAppointment(LocalTime appointmentEndTimeDTO, Appointment appointment) {
        return appointmentEndTimeDTO.isAfter(appointment.getTimeStart()) && appointmentEndTimeDTO.isBefore(appointment.getTimeEnd());
    }

}
