package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidEnumValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class GenderValueTest {

    @Test
    void genderInvalidValue() {

        testingThrowsException("fimale");
        testingThrowsException("malee");

    }

    @Test
    void genderValidValue() {

        testingDoesNotThrowException("MAle");
        testingDoesNotThrowException("FemalE");

    }

    private void testingThrowsException(String value) {
        Assertions.assertThrows(InvalidEnumValueException.class,
                () -> GenderValue.verification(value));
    }

    private void testingDoesNotThrowException(String value) {
        Assertions.assertDoesNotThrow(() -> GenderValue.verification(value));
    }

}
