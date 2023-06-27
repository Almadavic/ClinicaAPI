package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.customException.DateOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class TimeOrderTest {

    @Test
    void timeEndBeforeTimeStart() {

        Assertions.assertThrows(DateOrderException.class,
                () -> CommitAppointmentValidations.timeOrderValidation(LocalTime.of(05, 40), LocalTime.of(5, 30)));

    }

    @Test
    void timeEndAfterTimeStart() {

        Assertions.assertDoesNotThrow(
                () -> CommitAppointmentValidations.timeOrderValidation(LocalTime.of(5, 40), LocalTime.of(5, 50)));

    }

}
