package com.almada.clinicaapi.service.businessRule.commitDentist;

import com.almada.clinicaapi.service.customException.InvalidCroFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
class CroFormatTest {

    @Test
    void croInvalidFormat() {

        testingThrowsException("adihadiadhdaiad");

        testingThrowsException("!8137813");

        testingThrowsException("1156-76-06");

    }

    @Test
    void croValidFormat() {

        testingDoesNotThrowException("138613");
        testingDoesNotThrowException("12861");
        testingDoesNotThrowException("013318");

    }

    private void testingThrowsException(String cro) {
        Assertions.assertThrows(InvalidCroFormatException.class,
                () -> CroFormat.verification(cro));
    }

    private void testingDoesNotThrowException(String cro) {
        Assertions.assertDoesNotThrow(() -> CroFormat.verification(cro));
    }

}
