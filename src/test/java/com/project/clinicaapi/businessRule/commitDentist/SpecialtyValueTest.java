package com.project.clinicaapi.businessRule.commitDentist;

import com.project.clinicaapi.service.businessRule.commitDentist.SpecialtyValue;
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
