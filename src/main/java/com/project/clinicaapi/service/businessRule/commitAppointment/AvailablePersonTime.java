package com.project.clinicaapi.service.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.service.customException.AnotherMeetingRunningException;

import java.time.LocalTime;
import java.util.List;

public class AvailablePersonTime {

    private AvailablePersonTime() {

    }

    public static void verification(List<Appointment> appointments, LocalTime timeStart, LocalTime timeEnd, String userType) {
        if (requiredAppointmentTimeAvailable(appointments, timeStart, timeEnd)) {
            throw new AnotherMeetingRunningException(userType);
        }
    }

    private static boolean requiredAppointmentTimeAvailable(List<Appointment> appointments, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return appointments.stream().anyMatch(a -> verifyAvailability(a, startTimeDTO, endTimeDTO));
    }


    private static boolean verifyAvailability(Appointment appointment, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return timeWithinAnotherAppointment(startTimeDTO, appointment) || timeWithinAnotherAppointment(endTimeDTO, appointment);
    }

    private static boolean timeWithinAnotherAppointment(LocalTime appointmentTimeDTO, Appointment appointment) {
        return appointmentTimeDTO.isAfter(appointment.getTimeStart().minusMinutes(3)) &&
                appointmentTimeDTO.isBefore(appointment.getTimeEnd().plusMinutes(3));
    }

}
