package com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.validation;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.registerAppointment.RegisterAppointmentVerification;
import com.project.clinicaapi.service.customException.AnotherMeetingRunningException;
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

        if (requiredAppointmentAvailable(appointments, args.appointmentDTO().getTimeStart(), args.appointmentDTO().getTimeEnd())) {
            throw new AnotherMeetingRunningException("dentist");
        }

    }

    private boolean requiredAppointmentAvailable(List<Appointment> appointments, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return appointments.stream().anyMatch(a -> verifyAvailability(a, startTimeDTO, endTimeDTO));
    }

    private boolean verifyAvailability(Appointment appointment, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return timeWithinAnotherAppointment(startTimeDTO, appointment) || timeWithinAnotherAppointment(endTimeDTO, appointment);
    }

    private boolean timeWithinAnotherAppointment(LocalTime appointmentTimeDTO, Appointment appointment) {
        return appointmentTimeDTO.isAfter(appointment.getTimeStart().minusMinutes(3)) &&
                appointmentTimeDTO.isBefore(appointment.getTimeEnd().plusMinutes(3));
    }

}
