package com.project.clinicaapi.businessRule.commitPatient;

import com.project.clinicaapi.repository.PatientRepository;
import com.project.clinicaapi.service.businessRule.commitPatient.CpfAvailable;
import com.project.clinicaapi.service.customException.CpfAlreadyRegisteredException;
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
    void cpfUnavailable() {

        Assertions.assertThrows(CpfAlreadyRegisteredException.class,
                () -> CpfAvailable.verification(patientRepository, "115.613.986-02"));

    }

    @Test
    void cpfAvailable() {

        Assertions.assertDoesNotThrow(() -> CpfAvailable.verification(patientRepository, "1156139322"));

    }

}
