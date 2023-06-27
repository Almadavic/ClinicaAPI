package com.project.clinicaapi.service.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ConvertingType;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class CommitAppointmentValidations {

    private CommitAppointmentValidations() {

    }

    public static void appointmentDurationValidation(LocalTime timeStart, LocalTime timeEnd) {

        Duration duration = Duration.between(timeStart, timeEnd);

        if (duration.toMinutes() > 60 || duration.toMinutes() < 30) {
            throw new AppointmentDurationException();
        }
    }

    public static void appointmentTimeLimitValidation(LocalTime timeStart, LocalTime endTime) {

        LocalTime clinicOpeningTime = LocalTime.of(8, 0);
        LocalTime clinicClosingTime = LocalTime.of(18, 0);

        if(timeStart.isBefore(clinicOpeningTime) || endTime.isAfter(clinicClosingTime)) {
            throw new ClinicOpeningHoursException("The clinic works from 8:00 a.m to 18:00 p.m ");
        }

    }

    public static void dateOrderValidation(LocalDate appointmentDate) {

        if(appointmentDate.isBefore(LocalDate.now())) {
            throw new DateOrderException("The appointment date has to be today or someday after");
        }

    }

    public static void dayOfTheWeekValidation(DayOfWeek dayOfWeek) {

        if (dayOfWeek.equals(DayOfWeek.SUNDAY)) {
            throw new ClinicOpeningHoursException("Sunday is not a valid day, we work from monday to saturday");
        }

    }


    public static void dentistAvailableDayValidation(LocalDate appointmentDate, Dentist dentist) {

        WorkDayEnum workDay = ConvertingType.getWeekDayByLocalDate(appointmentDate);

        if (!dentistAvailable(dentist.getWorkDays(), workDay)) {
            throw new DentistNotAvailableException(workDay);
        }

    }

    private static boolean dentistAvailable(List<WorkDay> dentistWorkDays, WorkDayEnum workDay) {
        return dentistWorkDays.stream().anyMatch(n -> n.getWorkDay().equals(workDay));
    }

    public static void availableAppointmentTimeValidation(List<Appointment> appointments, LocalTime timeStart, LocalTime timeEnd, String userType) {
        if (requiredAppointmentTimeAvailable(appointments, timeStart, timeEnd)) {
            throw new AnotherMeetingRunningException(userType);
        }
    }

    public static boolean requiredAppointmentTimeAvailable(List<Appointment> appointments, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return appointments.stream().anyMatch(a -> verifyAvailability(a, startTimeDTO, endTimeDTO));
    }


    private static boolean verifyAvailability(Appointment appointment, LocalTime startTimeDTO, LocalTime endTimeDTO) {
        return timeWithinAnotherAppointment(startTimeDTO, appointment) || timeWithinAnotherAppointment(endTimeDTO, appointment);
    }

    private static boolean timeWithinAnotherAppointment(LocalTime appointmentTimeDTO, Appointment appointment) {
        return appointmentTimeDTO.isAfter(appointment.getTimeStart().minusMinutes(3)) &&
                appointmentTimeDTO.isBefore(appointment.getTimeEnd().plusMinutes(3));
    }

    public static void timeOrderValidation(LocalTime timeStart, LocalTime timeEnd) {

        if(timeStart.isAfter(timeEnd)) {
            throw new DateOrderException("The timestart of the appointment cannot be after timeend");
        }

    }

}
