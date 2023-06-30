package com.project.clinicaapi.businessRule.commitAppointment.update;

import com.project.clinicaapi.dto.request.update.AppointmentUpdateDTO;
import com.project.clinicaapi.dto.request.update.DentistUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.UpdateAppointmentArgs;
import com.project.clinicaapi.service.businessRule.commitAppointment.updateAppointment.validation.NoFieldFilledUpdateAppointment;
import com.project.clinicaapi.service.businessRule.commitDentist.updateDentist.UpdateDentistArgs;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
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

    @Test
    void noFieldFilledToUpdate() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder().build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null)));

    }

    @Test
    void fieldFilledToUpdate() {

        AppointmentUpdateDTO appointmentDTO = AppointmentUpdateDTO.builder()
                .procedure("Procedimento novo")
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateAppointmentArgs(appointmentDTO, null, null)));

    }

}
