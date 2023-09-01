package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.ClinicOpeningHoursException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class AppointmentTimeLimitTest {

    @Test
    void clinicNotOpenedYet() {

        testingThrowsException(LocalTime.of(7, 10), LocalTime.of(8,10));
        testingThrowsException(LocalTime.of(5, 10), LocalTime.of(8,50));
    }

    @Test
    void clinicAlreadyClosed() {

        testingThrowsException(LocalTime.of(17,50), LocalTime.of(18, 10));
        testingThrowsException(LocalTime.of(19, 30), LocalTime.of(20, 10));
    }



    @Test
    void clinicOpen() {
        testingDoesNotThrowException(LocalTime.of(14,30), LocalTime.of(15, 10));
        testingDoesNotThrowException(LocalTime.of(10,20), LocalTime.of(11, 0));
    }

    private void testingThrowsException(LocalTime timeStart, LocalTime timeEnd) {
        Assertions.assertThrows(ClinicOpeningHoursException.class,
                () -> AppointmentTimeLimit.verification(timeStart, timeEnd));
    }

    private void testingDoesNotThrowException(LocalTime timeStart, LocalTime timeEnd) {
        Assertions.assertDoesNotThrow(() -> AppointmentTimeLimit.verification(timeStart, timeEnd));
    }

}
