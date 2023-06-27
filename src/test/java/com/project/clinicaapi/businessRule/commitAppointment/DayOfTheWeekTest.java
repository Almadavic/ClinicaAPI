package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Appointment;
import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.customException.ClinicOpeningHoursException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;

@ActiveProfiles(value = "test")
@SpringBootTest
class DayOfTheWeekTest {

    @Test
    void dayOff() {

        Assertions.assertThrows(ClinicOpeningHoursException.class,
                () -> CommitAppointmentValidations.dayOfTheWeekValidation(DayOfWeek.SUNDAY));

    }

    @Test
    void dayOn() {

        Assertions.assertDoesNotThrow(() -> CommitAppointmentValidations.dayOfTheWeekValidation(DayOfWeek.MONDAY));
        Assertions.assertDoesNotThrow(() -> CommitAppointmentValidations.dayOfTheWeekValidation(DayOfWeek.SATURDAY));

    }

}
