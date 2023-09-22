package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.service.customException.ClinicOpeningHoursException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;

@ActiveProfiles(value = "test")
class BusinessDayTest {

    @Test
    void dayOff() {

        Assertions.assertThrows(ClinicOpeningHoursException.class,
                () -> BusinessDay.verification(DayOfWeek.SUNDAY));

    }

    @Test
    void dayOn() {

        Assertions.assertDoesNotThrow(() -> BusinessDay.verification(DayOfWeek.MONDAY));
        Assertions.assertDoesNotThrow(() -> BusinessDay.verification(DayOfWeek.SATURDAY));

    }

}
