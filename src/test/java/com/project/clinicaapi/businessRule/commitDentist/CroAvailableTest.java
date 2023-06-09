package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.repository.DentistRepository;
import com.project.clinicaapi.service.businessRule.commitDentist.CroAvailable;
import com.project.clinicaapi.service.customException.CroAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CroAvailableTest {

    @Autowired
    private DentistRepository dentistRepository;

    @Test
    void croUnavailable() {

        Assertions.assertThrows(CroAlreadyRegisteredException.class,
                () -> CroAvailable.verification(dentistRepository, "137185"));

    }

    @Test
    void croAvailable() {

        Assertions.assertDoesNotThrow(() -> CroAvailable.verification(dentistRepository, "1111111"));

    }

}
