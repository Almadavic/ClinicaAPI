package com.project.clinicaapi.service.appointmentService;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.entity.*;
import com.project.clinicaapi.repository.UserRepository;
import com.project.clinicaapi.service.serviceAction.AppointmentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class SaveAppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private Factory factory;

    @Test
    void fieldsValue() {

        Dentist dentist = (Dentist) factory.returnUserDataBaseByLogin("dentist1");

        AppointmentRegisterDTO appointmentDTO = AppointmentRegisterDTO.builder()
                .procedure("Ginoplastia")
                .appointmentDate(nextDentistWorkDay(dentist))
                .timeStart(LocalTime.of(9, 0))
                .timeEnd(LocalTime.of(9, 40))
                .dentistId(dentist.getId())
                .patientId(factory.returnUserDataBaseByLogin("patient").getId())
                .build();

       AppointmentResponseDTO appointmentResponseDTO =  appointmentService.save(appointmentDTO, factory.returnUserDataBaseByLogin("admin"));

        Assertions.assertEquals(appointmentDTO.getAppointmentDate().getDayOfWeek().toString(), appointmentResponseDTO.getWeekDay());

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

    private static boolean isSameDay(WorkDay workDay, LocalDate today) {
        return workDay.getWorkDay().name().equals(today.getDayOfWeek().name());
    }

}
