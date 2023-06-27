package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.customException.DateOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles(value = "test")
@SpringBootTest
class DateOrderTest {

    @Test
    void tryingToBookAnAppointmentWithPastDate() {

        Assertions.assertThrows(DateOrderException.class,
                () -> CommitAppointmentValidations.dateOrderValidation(LocalDate.of(2023, 5, 15)));

    }

    @Test
    void tryingToBookAnAppointmentWithCurrentDate() {

        Assertions.assertDoesNotThrow(() -> CommitAppointmentValidations.dateOrderValidation(LocalDate.now()));

    }

    @Test
    void tryingToBookAnAppointmentWithFutureDate() {

       Assertions.assertDoesNotThrow(() -> CommitAppointmentValidations.dateOrderValidation(LocalDate.now().plusYears(1).plusDays(5)));

    }

}
