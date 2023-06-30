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

    private static boolean requiredAppointmentTimeAvailable(List<Appointment> appointments, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        return appointments.stream().anyMatch(a -> verifyAvailability(a, timeStartDTO, timeEndDTO));
    }


    private static boolean verifyAvailability(Appointment appointment, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        return timeStartBeforeAnotherAppointmentButTimeEndWithin(appointment, timeStartDTO, timeEndDTO) ||
                timeStartWithinAnotherAppointment(appointment, timeStartDTO) ||
                anotherAppointmentWithinTimeStartAndTimeEnd(appointment, timeStartDTO, timeEndDTO);
    }

    private static boolean timeStartBeforeAnotherAppointmentButTimeEndWithin(Appointment appointment, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        LocalTime timeStart = appointment.getTimeStart();
        LocalTime timeEnd = appointment.getTimeEnd();
        return timeStartDTO.isBefore(timeStart) && timeEndDTO.isAfter(timeStart) &&
                timeEndDTO.isBefore(timeEnd);
    }

    private static boolean timeStartWithinAnotherAppointment(Appointment appointment, LocalTime timeStartDTO) {
        return timeStartDTO.isAfter(appointment.getTimeStart()) && timeStartDTO.isBefore(appointment.getTimeEnd());
    }

    private static boolean anotherAppointmentWithinTimeStartAndTimeEnd(Appointment appointment, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        return timeStartDTO.isBefore(appointment.getTimeStart()) && timeEndDTO.isAfter(appointment.getTimeEnd());
    }


}
