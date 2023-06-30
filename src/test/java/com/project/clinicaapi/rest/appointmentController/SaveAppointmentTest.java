package com.project.clinicaapi.rest.appointmentController;

import com.project.clinicaapi.dto.request.register.AddessRegisterDTO;
import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.request.register.DentistRegisterDTO;
import com.project.clinicaapi.entity.*;
import com.project.clinicaapi.enumerated.Specialty;
import com.project.clinicaapi.enumerated.WorkDayEnum;
import com.project.clinicaapi.repository.AppointmentRepository;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.rest.ClassTestParent;
import com.project.clinicaapi.service.customException.*;
import com.project.clinicaapi.util.ListEnumValues;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles(value = "test")
@SpringBootTest
@AutoConfigureMockMvc
class SaveAppointmentTest extends ClassTestParent {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;


    private final String path = "/appointments";

    @Test
    void appointmentDurationInsufficient() throws Exception {

        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextDentistWorkDay(dentist))
                .timeStart(LocalTime.of(8, 50))
                .timeEnd(LocalTime.of(9, 0))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(dentist.getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AppointmentDurationException))
                .andExpect(result -> assertEquals("The appointment has to last between 30 and 60 minutes"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void appointmentDurationExceeded() throws Exception {

        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextDentistWorkDay(dentist))
                .timeStart(LocalTime.of(10, 30))
                .timeEnd(LocalTime.of(11, 50))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(dentist.getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof AppointmentDurationException))
                .andExpect(result -> assertEquals("The appointment has to last between 30 and 60 minutes"
                        , result.getResolvedException().getMessage()));

    }


    @Test
    void clinicNotOpenedYet() throws Exception {

        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextDentistWorkDay(dentist))
                .timeStart(LocalTime.of(7, 10))
                .timeEnd(LocalTime.of(8, 20))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(dentist.getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
                .andExpect(result -> assertEquals("The clinic works from 8:00 a.m to 18:00 p.m"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void clinicAlreadyClosed() throws Exception {

        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextDentistWorkDay(dentist))
                .timeStart(LocalTime.of(20, 30))
                .timeEnd(LocalTime.of(22, 50))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(dentist.getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
                .andExpect(result -> assertEquals("The clinic works from 8:00 a.m to 18:00 p.m"
                        , result.getResolvedException().getMessage()));

    }

//    @Test
//    void anotherAppointmentRunning() throws Exception {
//
//        Dentist dentist = (Dentist) returnUserDataBaseId("dentist1");
//
//        LocalDate nextDentistWorDay = nextDentistWorkDay(dentist);
//
//        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
//                .procedure("Ginoplastia")
//                .appointmentDate(nextDentistWorDay)
//                .timeStart(dentistRunningAnotherAppointment("dentist1", now))
//                .timeEnd(dentistRunningAnotherAppointment("dentist1", now).plusHours(1))
//                .patientId(returnUserDataBaseId("patient").getId())
//                .dentistId(returnUserDataBaseId("dentist1").getId())
//                .build();
//
//        mockMvc.perform(post(path)
//                        .header("Authorization", token("admin", "123456"))
//                        .contentType("application/json")
//                        .content(objectMapper.writeValueAsString(appointmentDTO)))
//                .andExpect(status().is(badRequest))
//                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
//                .andExpect(result -> assertEquals("The clinic works from 8:00 a.m to 18:00 p.m"
//                        , result.getResolvedException().getMessage()));
//
//    }

    @Test
    void dayOff() throws Exception {

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextSunday())
                .timeStart(LocalTime.of(15, 30))
                .timeEnd(LocalTime.of(16, 0))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(returnUserDataBaseId("dentist1").getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof ClinicOpeningHoursException))
                .andExpect(result -> assertEquals("Sunday is not a valid day, we work from monday to saturday"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void appointmentWithPastDate() throws Exception {

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.now().minusDays(1))
                .timeStart(LocalTime.of(15, 30))
                .timeEnd(LocalTime.of(16, 0))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(returnUserDataBaseId("dentist1").getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DateOrderException))
                .andExpect(result -> assertEquals("The appointment date has to be today or someday after"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void dentistDisabled() throws Exception {

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.now())
                .timeStart(LocalTime.now().plusMinutes(30))
                .timeEnd(LocalTime.now().plusMinutes(40))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(returnUserDataBaseId("dentist5").getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(badRequest))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof DateOrderException))
                .andExpect(result -> assertEquals("The appointment date has to be today or someday after"
                        , result.getResolvedException().getMessage()));

    }

    @Test
    void saveAppointmentSuccess() throws Exception {

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(LocalDate.of(2023, 6, 27))
                .timeStart(LocalTime.of(10, 30))
                .timeEnd(LocalTime.of(11, 50))
                .patientId(returnUserDataBaseId("patient").getId())
                .dentistId(returnUserDataBaseId("dentist").getId())
                .build();

        mockMvc.perform(post(path)
                        .header("Authorization", token("admin", "123456"))
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(appointmentDTO)))
                .andExpect(status().is(created));

    }

    private LocalDate nextSunday() {

        LocalDate nextSunday = null;

        for (int i = 0; i < 6; i++) {
            LocalDate day = LocalDate.now().plusDays(i);
            if(day.getDayOfWeek().equals(DayOfWeek.SUNDAY)) {
                nextSunday = day;
                break;
            }
        }
        return nextSunday;
    }

    private LocalDate nextDentistWorkDay(Dentist dentist) {

        LocalDate nextDentistWorkDay = null;

        for (int i = 0; i < 6; i++) {
            LocalDate today = LocalDate.now().plusDays(i);
            if(dentist.getWorkDays().stream().anyMatch(w -> isSameDay(w, today))) {
                nextDentistWorkDay = today;
                break;
            }
        }

        return nextDentistWorkDay;
    }

    private static boolean isSameDay(WorkDay workDay, LocalDate today) {
        return workDay.getWorkDay().name().equals(today.getDayOfWeek().name());
    }


    private LocalTime dentistRunningAnotherAppointment(String login, LocalDate appointmentDate) {
        Dentist dentist = (Dentist) returnUserDataBaseId(login);

        Appointment appointment = appointmentRepository.findByDentistAndByDate(dentist.getId(), appointmentDate).get(0);

        return appointment.getTimeStart();
    }

    private User returnUserDataBaseId(String login) {
        return userRepository.findByLogin(login).get();
    }

}
