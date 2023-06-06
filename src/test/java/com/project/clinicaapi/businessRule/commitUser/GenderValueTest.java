package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.InvalidEnumValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class GenderValueTest {

    @Test
    void genderInvalidValue() {

        testingFail("fimale");
        testingFail("malee");

    }

    @Test
    void genderValidValue() {

        testingSuccess("MAle");
        testingSuccess("FemalE");

    }

    private void testingFail(String value) {
        Assertions.assertThrows(InvalidEnumValueException.class,
                () -> CommitUserValidations.genderValueValidation(value));
    }

    private void testingSuccess(String value) {
        Assertions.assertDoesNotThrow(()-> CommitUserValidations.genderValueValidation(value));
    }

}
