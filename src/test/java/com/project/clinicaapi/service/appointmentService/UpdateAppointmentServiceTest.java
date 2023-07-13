package com.project.clinicaapi.service.appointmentService;

import com.project.clinicaapi.Factory;
import com.project.clinicaapi.dto.request.register.AppointmentRegisterDTO;
import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.dto.response.AppointmentResponseDTO;
import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.entity.WorkDay;
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
class UpdateAppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private Factory factory;

    @Test
    void fieldsValue() {

        Dentist dentist = (Dentist) factory.returnUserDataBaseByLogin("dentist1");

        String procedure = "Ginoplastia22";

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .procedure(procedure)
                .appointmentDate(nextDentistWorkDay(dentist).plusDays(14))
                .build();

       AppointmentResponseDTO appointmentResponseDTO =  appointmentService.update(factory.returnAppointment().getId(), appointmentDTO,
               factory.returnUserDataBaseByLogin("admin"));

        Assertions.assertEquals(appointmentDTO.getAppointmentDate().getDayOfWeek().toString(), appointmentResponseDTO.getWeekDay());
        Assertions.assertEquals(procedure, appointmentDTO.getProcedure());

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
