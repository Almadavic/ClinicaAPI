package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.service.customException.InvalidEnumValueException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
class SpecialtyValueTest {

    @Test
    void specialtyInvalidValue() {

        testingThrowsException("oçyrthodontiss");
        testingThrowsException("implanttt");

    }

    @Test
    void specialtyValidValue() {

        testingDoesNotThrowException("OrThoDontiCS");
        testingDoesNotThrowException("IMPlant_DentistrY");

    }

    private void testingThrowsException(String value) {
        Assertions.assertThrows(InvalidEnumValueException.class,
                () -> SpecialtyValue.verification(value));
    }

    private void testingDoesNotThrowException(String value) {
        Assertions.assertDoesNotThrow(() -> SpecialtyValue.verification(value));
    }

}
