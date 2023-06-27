package com.project.clinicaapi.businessRule.commitAppointment;

import com.project.clinicaapi.entity.Dentist;
import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.businessRule.commitAppointment.CommitAppointmentValidations;
import com.project.clinicaapi.service.customException.DentistNotAvailableException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;

@ActiveProfiles(value = "test")
@SpringBootTest
class DentistAvailableDayTest {

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    void dentistAvailableDay() {

        Assertions.assertDoesNotThrow(
                () -> CommitAppointmentValidations.dentistAvailableDayValidation(LocalDate.of(2023, 6, 26), returnDentistDataBaseId()));
        Assertions.assertDoesNotThrow(
                () -> CommitAppointmentValidations.dentistAvailableDayValidation(LocalDate.of(2023, 6, 27), returnDentistDataBaseId()));
    }

    @Test
    void dentistUnavailableDay() {

        Assertions.assertThrows(DentistNotAvailableException.class,
                () -> CommitAppointmentValidations.dentistAvailableDayValidation(LocalDate.of(2023, 6, 28), returnDentistDataBaseId()));
        Assertions.assertThrows(DentistNotAvailableException.class,
                () -> CommitAppointmentValidations.dentistAvailableDayValidation(LocalDate.of(2023, 6, 29), returnDentistDataBaseId()));
    }

    private Dentist returnDentistDataBaseId() {
        return dentistRepository.findByCro("137180").get();
    }

}
