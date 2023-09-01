package com.almada.clinicaapi.service.businessRule.commitAppointment.update;

import com.almada.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.almada.clinicaapi.factory.AppointmentFactory;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.almada.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation.NoFieldFilledUpdateAppointment;
import com.almada.clinicaapi.service.customException.NoFieldFilledException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateAppointmentFieldsTest {

    @Autowired
    private NoFieldFilledUpdateAppointment service;

    @Autowired
    private AppointmentFactory appointmentFactory;

    @Test
    void noFieldFilledToUpdate() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        AppointmentUpdateDTO appointmentDTO = appointmentFactory.dtoUpdate();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null)));

    }

}
