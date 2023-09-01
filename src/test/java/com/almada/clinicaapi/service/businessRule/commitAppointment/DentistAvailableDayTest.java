package com.almada.clinicaapi.service.businessRule.commitAppointment;

import com.almada.clinicaapi.entity.Dentist;
import com.almada.clinicaapi.enumerated.WorkDayEnum;
import com.almada.clinicaapi.factory.DentistFactory;
import com.almada.clinicaapi.factory.WorkDayFactory;
import com.almada.clinicaapi.repository.DentistRepository;
import com.almada.clinicaapi.service.customException.DentistNotAvailableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles(value = "test")
@SpringBootTest
class DentistAvailableDayTest {

    @Autowired
    private DentistFactory dentistFactory;

    @Autowired
    private WorkDayFactory workDayFactory;

    private Dentist dentist;

    @BeforeEach
    void setUp() {
        this.dentist = dentistFactory.entity();
        this.dentist.getWorkDays().add(workDayFactory.entity(WorkDayEnum.MONDAY));
        this.dentist.getWorkDays().add(workDayFactory.entity(WorkDayEnum.TUESDAY));
    }

    @Test
    void dentistAvailableDay() {

        Assertions.assertDoesNotThrow(
                () -> DentistAvailableDay.verification(LocalDate.of(2023, 9, 4), dentist));
        Assertions.assertDoesNotThrow(
                () -> DentistAvailableDay.verification(LocalDate.of(2023, 9, 5), dentist));
    }

    @Test
    void dentistUnavailableDay() {

        Assertions.assertThrows(DentistNotAvailableException.class,
                () -> DentistAvailableDay.verification(LocalDate.of(2023, 9, 6), dentist));
        Assertions.assertThrows(DentistNotAvailableException.class,
                () -> DentistAvailableDay.verification(LocalDate.of(2023, 9, 7), dentist));
    }

}
