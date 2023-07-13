package com.project.clinicaapi.rest.appointmentController;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.User;
import com.project.clinicaapi.entity.WorkDay;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class UpdateAppointmentTest extends ClassTestParent {

    @Autowired
    private Factory factory;


    private final LocalTime clinicCloses = LocalTime.MAX.minusHours(6);

    private final String path = "/appointments";

    @Test
    void appointmentDurationInsufficient() throws Exception {

        appointmentDuration(LocalTime.of(8, 50), LocalTime.of(9, 0));

    }

    @Test
    void appointmentDurationExceeded() throws Exception {

        appointmentDuration(LocalTime.of(8, 50), LocalTime.of(10, 0));

    }


    @Test
    void clinicNotOpenedYet() throws Exception {

        clinicOutOfHours(LocalTime.of(7, 50), LocalTime.of(8, 50));

    }

    @Test
    void clinicAlreadyClosed() throws Exception {

        clinicOutOfHours(LocalTime.of(18, 10), LocalTime.of(19, 0));

    }

    @Test
    void dayOffClinic() throws Exception {

        clinicOutOfHours(nextSunday());

    }

    @Test
    void timeStartAfterTimeEnd() throws Exception {

        timeOrder(LocalTime.of(10, 40), LocalTime.of(10, 0));

    }

    @Test
    void timeInThePast() throws Exception {

        if(LocalDate.now().getDayOfWeek() != DayOfWeek.SUNDAY &&
                LocalTime.now().isAfter(LocalTime.of(8, 0)) &&  LocalTime.now().isBefore(LocalTime.of(17, 0))) {
            timeOrder(LocalTime.now(), LocalTime.now().plusHours(1));
        }

    }

//    @Test
//    void timeStartDTOWithinExistingAppointment() throws Exception {
//
//        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");
//
//        LocalDate nextDentistWorkDay = nextDentistWorkDay(dentist);
//
//        LocalTime timeStart = dentistRunningAnotherAppointment(dentist.getUsername(), nextDentistWorkDay);
//
//        anotherAppointmentRunning(timeStart.plusMinutes(1), timeStart.plusMinutes(32), "dentist");
//
//    }

//    @Test
//    void timeEndDTOWithinExistingAppointment() throws Exception {
//
//        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");
//
//        LocalDate nextDentistWorkDay = nextDentistWorkDay(dentist);
//
//        LocalTime timeStart = dentistRunningAnotherAppointment(dentist.getUsername(), nextDentistWorkDay);
//
//        anotherAppointmentRunning(timeStart.plusMinutes(5), timeStart.plusMinutes(48), "dentist");
//
//    }
//
//    @Test
//    void timeStartDTOBeforeExistingAppointmentAndTimeEndDTOAfter() throws Exception {
//
//        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");
//
//        LocalDate nextDentistWorkDay = nextDentistWorkDay(dentist);
//
//        LocalTime timeStart = dentistRunningAnotherAppointment(dentist.getUsername(), nextDentistWorkDay);
//
//        anotherAppointmentRunning(timeStart.minusMinutes(5), timeStart.plusMinutes(5), "dentist");
//
//    }

    @Test
    void pastDate() throws Exception {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .appointmentDate(LocalDate.now().minusDays(1))
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DateOrderException))
                .andExpect(result -> assertEquals("The appointment date has to be today or someday after"
                        , result.getResolvedException().getMessage()));

    }


    @Test
    void dayOffDentist() throws Exception {

        Dentist dentist = (Dentist) factory.returnUserDataBaseByLogin("dentist1");

        LocalDate nextDentistDayOff = nextDentistDayOff(dentist).plusDays(7);

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .appointmentDate(nextDentistDayOff)
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DentistNotAvailableException))
                .andExpect(result -> assertEquals("The dentist doesn't work that day: " + nextDentistDayOff.getDayOfWeek().name()
                        , result.getResolvedException().getMessage()));


    }

    @Test
    void appointmentByIdNotFound() throws Exception {

        String appointmentId = "invalidid";

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", appointmentId)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(notFound))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ResourceNotFoundException))
                .andExpect(result -> assertEquals("The appointment id: " + appointmentId + " wasn't found on database"
                        , result.getResolvedException().getMessage()));


    }

    @Test
    void noFieldFilledToUpdate() throws Exception {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .build();

        mockMvc.perform(patch(path + "/" + factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof NoFieldFilledException))
                .andExpect(result -> assertEquals("You have to update at least one field"
                        , result.getResolvedException().getMessage()));

    }


    @Test
    void updateAppointmentSuccess() throws Exception {

        Dentist dentist = (Dentist) factory.returnUserDataBaseByLogin("dentist1");

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .appointmentDate(nextDentistWorkDay(dentist).plusDays(7))
                .timeStart(LocalTime.MIN.plusHours(8))
                .timeEnd(LocalTime.MIN.plusHours(9))
                .procedure("Clareamento básico")
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(ok));

    }

    private void appointmentDuration(LocalTime timeStart, LocalTime timeEnd) throws Exception {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AppointmentDurationException))
                .andExpect(result -> assertEquals("The appointment has to last between 30 and 60 minutes"
                        , result.getResolvedException().getMessage()));

    }

    private void clinicOutOfHours(LocalTime timeStart, LocalTime timeEnd) throws Exception {


        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
                .andExpect(result -> assertEquals("The clinic works from 8:00 a.m to 18:00 p.m from monday to saturday"
                        , result.getResolvedException().getMessage()));

    }

    private void clinicOutOfHours(LocalDate appointmentDate) throws Exception {


        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .appointmentDate(appointmentDate)
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
                .andExpect(result -> assertEquals("The clinic works from 8:00 a.m to 18:00 p.m from monday to saturday"
                        , result.getResolvedException().getMessage()));

    }

    private void timeOrder(LocalTime timeStart, LocalTime timeEnd) throws Exception {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeStart(timeStart)
                .timeEnd(timeEnd)
                .build();

        mockMvc.perform(patch(path + "/{appointmentid}", factory.returnAppointment().getId())
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DateOrderException))
                .andExpect(result -> assertEquals("The timestart of the appointment cannot be after timeend and timestart cannot be in the past"
                        , result.getResolvedException().getMessage()));

    }

    void anotherAppointmentRunning(LocalTime timeStart, LocalTime timeEnd, String userType) throws Exception {

//        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");
//
//        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
//                .procedure("Ginoplastia")
//                .appointmentDate(nextDentistWorkDay(dentist))
//                .timeStart(timeStart)
//                .timeEnd(timeEnd)
//                .patientId(returnUserDataBaseId("patient").getId())
//                .dentistId(dentist.getId())
//                .build();
//
//        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
//                .build();
//
//        mockMvc.perform(post(path + "/{appointmentid}", returnAppointmentDataBaseId())
//                        .header("Authorization", token("admin", "123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(appointmentDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AnotherMeetingRunningException))
//                .andExpect(result -> assertEquals("The " + userType + " has an another appointment that time"
//                        , result.getResolvedException().getMessage()));

    }

    private LocalDate nextSunday() {

        LocalDate nextSunday = null;

        for (int i = 0; i < 6; i++) {
            LocalDate day = LocalDate.now().plusDays(i);
            if (day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                nextSunday = day;
                break;
            }
        }
        return nextSunday.plusDays(7);
    }

    private LocalDate nextDentistWorkDay(Dentist dentist) {

        LocalDate nextDentistWorkDay = null;

        for (int i = 0; i < 6; i++) {
            LocalDate today = LocalDate.now().plusDays(i);
            if (dentist.getWorkDays().stream().anyMatch(w -> isSameDay(w, today))) {
                nextDentistWorkDay = today;
                break;
            }
        }

        return nextDentistWorkDay;
    }

    private LocalDate nextDentistDayOff(Dentist dentist) {

        LocalDate nextDentistWorkDay = null;

        for (int i = 0; i < 6; i++) {
            LocalDate today = LocalDate.now().plusDays(i);
            if (dentist.getWorkDays().stream().anyMatch(w -> !isSameDay(w, today) && !today.getDayOfWeek().equals(DayOfWeek.SUNDAY))) {
                nextDentistWorkDay = today;
                break;
            }
        }

        return nextDentistWorkDay;
    }

    private static boolean isSameDay(WorkDay workDay, LocalDate today) {
        return workDay.getWorkDay().name().equals(today.getDayOfWeek().name());
    }


//    private LocalTime dentistRunningAnotherAppointment(String login, LocalDate appointmentDate) {
//        Dentist dentist = (Dentist) factory.returnUserDataBaseByLogin(login);
//
//        Appointment appointment = appointmentRepository.findByDentistAndByDate(dentist.getId(), appointmentDate).get(0);
//
//        return appointment.getTimeStart();
//    }


}
