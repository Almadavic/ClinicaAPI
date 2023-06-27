package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.service.businessRule.commitAppointment.AppointmentDuration;
import com.project.clinicaapi.service.customException.AppointmentDurationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalTime;

@ActiveProfiles(value = "test")
@SpringBootTest
class AppointmentDurationTest {

    @Test
    void appointmentDurationNotAccepted() {
        testingThrowsException(LocalTime.of(1, 10), LocalTime.of(3, 30));
        testingThrowsException(LocalTime.of(1, 10), LocalTime.of(1, 20));
    }

    @Test
    void appointmentDurationAccepted() {
        testingDoesNotThrowException(LocalTime.of(1, 10), LocalTime.of(1, 50));
        testingDoesNotThrowException(LocalTime.of(4, 13), LocalTime.of(5, 4));
    }

    private void testingThrowsException(LocalTime timeStart, LocalTime timeEnd) {
        Assertions.assertThrows(AppointmentDurationException.class,
                () -> AppointmentDuration.verification(timeStart, timeEnd));
    }

    private void testingDoesNotThrowException(LocalTime timeStart, LocalTime timeEnd) {
        Assertions.assertDoesNotThrow(() -> AppointmentDuration.verification(timeStart, timeEnd));
    }

}
