package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.DateOrderException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles(value = "test")
class DateOrderTest {

    @Test
    void tryingToBookAnAppointmentWithPastDate() {

        Assertions.assertThrows(DateOrderException.class,
                () -> DateOrder.verification(LocalDate.of(2023, 5, 15)));

    }

    @Test
    void tryingToBookAnAppointmentWithCurrentDate() {

        Assertions.assertDoesNotThrow(() -> DateOrder.verification(LocalDate.now()));

    }

    @Test
    void tryingToBookAnAppointmentWithFutureDate() {

       Assertions.assertDoesNotThrow(() -> DateOrder.verification(LocalDate.now().plusYears(1).plusDays(5)));

    }

}
