package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.customException.InvalidCroFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CroFormatTest {

    @Test
    void croInvalidFormat() {

        testingFail("adihadiadhdaiad");

        testingFail("!8137813");

        testingFail("1156-76-06");

    }

    @Test
    void croValidFormat() {

        testingSuccess("138613");
        testingSuccess("12861");
        testingSuccess("013318");

    }

    private void testingFail(String cro) {
        Assertions.assertThrows(InvalidCroFormatException.class,
                () -> CommitDentistValidations.croFormatValidation(cro));
    }

    private void testingSuccess(String cro) {
        Assertions.assertDoesNotThrow(() -> CommitDentistValidations.croFormatValidation(cro));
    }

}
