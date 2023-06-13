package com.project.clinicaapi.businessRule.commitPatient;

import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.businessRule.commitSecretary.CommitSecretaryValidations;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CpfAvailableTest {

    @Autowired
    private PatientRepository patientRepository;

    @Test
    void registrationUnavailable() {

        Assertions.assertThrows(CpfAlreadyRegisteredException.class,
                () -> CommitPatientValidations.findPatientByCpfValidation(patientRepository, "115.613.986-02"));

    }

    @Test
    void registrationAvailable() {

        Assertions.assertDoesNotThrow(() -> CommitPatientValidations.findPatientByCpfValidation(patientRepository, "1156139322"));

    }

}
