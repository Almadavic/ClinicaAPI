package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.entity.Appointment;
import com.almada.clinicaapi.service.customException.AnotherMeetingRunningException;

import java.time.LocalTime;
import java.util.List;

public class AvailablePersonTime {

    private AvailablePersonTime() {

    }

    public static void verification(List<Appointment> appointments, LocalTime timeStartDTO, LocalTime timeEndDTO, String userType) {

        if (appointments.stream().anyMatch(appointment -> timeUnavailable(appointment, timeStartDTO, timeEndDTO))) {
            throw new AnotherMeetingRunningException(userType);
        }

    }

    private static boolean timeUnavailable(Appointment appointment, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        return timeStartDTOWithinExistingAppointment(appointment, timeStartDTO) ||
                timeEndDTOWithinExistingAppointment(appointment, timeEndDTO) ||
                timeStartDTOBeforeExistingAppointmentAndTimeEndDTOAfter(appointment, timeStartDTO, timeEndDTO);
    }

    private static boolean timeStartDTOWithinExistingAppointment(Appointment appointment, LocalTime timeStartDTO) {
        return timeStartDTO.isAfter(appointment.getTimeStart()) && timeStartDTO.isBefore(appointment.getTimeEnd());
    }

    private static boolean timeEndDTOWithinExistingAppointment(Appointment appointment, LocalTime timeEndDTO) {
        return timeEndDTO.isAfter(appointment.getTimeStart()) && timeEndDTO.isBefore(appointment.getTimeEnd());
    }

    private static boolean timeStartDTOBeforeExistingAppointmentAndTimeEndDTOAfter(Appointment appointment, LocalTime timeStartDTO, LocalTime timeEndDTO) {
        return timeStartDTO.isBefore(appointment.getTimeStart()) && timeEndDTO.isAfter(appointment.getTimeEnd());
    }

}
