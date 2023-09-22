package com.almada.clinicaapi.service.businessRule.commitPatient;

import com.almada.clinicaapi.service.customException.InvalidCpfFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
class CpfFormatTest {

    @Test
    void cpfInvalidFormat() {

        testingThrowsException("adihadiadhdaiad");

        testingThrowsException("1937318173dafafy7fa");

        testingThrowsException("115613876-06");

    }

    @Test
    void cpfValidFormat() {

        testingDoesNotThrowException("118.613.816-94");
        testingDoesNotThrowException("911.219.476-12");
        testingDoesNotThrowException("002.558.181-29");

    }

    private void testingThrowsException(String cpf) {
        Assertions.assertThrows(InvalidCpfFormatException.class,
                () -> CpfFormat.verification(cpf));
    }

    private void testingDoesNotThrowException(String cpf) {
        Assertions.assertDoesNotThrow(() -> CpfFormat.verification(cpf));
    }

}
