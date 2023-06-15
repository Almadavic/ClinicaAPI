package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.service.businessRule.commitDentist.CommitDentistValidations;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class SpecialtyValueTest {

    @Test
    void specialtyInvalidValue() {

        testingFail("oçyrthodontiss");
        testingFail("implanttt");

    }

    @Test
    void specialtyValidValue() {

        testingSuccess("OrThoDontiCS");
        testingSuccess("IMPlant_DentistrY");

    }

    private void testingFail(String value) {
        Assertions.assertThrows(InvalidEnumValueException.class,
                () -> CommitDentistValidations.specialtyValueValidation(value));
    }

    private void testingSuccess(String value) {
        Assertions.assertDoesNotThrow(() -> CommitDentistValidations.specialtyValueValidation(value));
    }

}
