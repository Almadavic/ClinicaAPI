package com.project.clinicaapi.businessRule.commitAppointment.update;

import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation.AppointmentTimeNull;
import com.project.clinicaapi.service.customException.ParameterMissingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class AppointmentTimeNullTest {

    @Autowired
    private AppointmentTimeNull service;

    @Test
    void timeEndNull() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeStart(LocalTime.now())
                .build();

        Assertions.assertThrows(ParameterMissingException.class,
                () -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null, null, null,
                        null, null)));

    }

    @Test
    void timeStartNull() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeEnd(LocalTime.now())
                .build();

        Assertions.assertThrows(ParameterMissingException.class,
                () -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null, null, null,
                        null, null)));

    }

    @Test
    void timeStartAndTimeEndEntered() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .timeStart(LocalTime.now())
                .timeEnd(LocalTime.now().plusMinutes(50))
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null, null,
                null, null, null)));

    }

}
