package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidCellphoneNumberFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CellphoneFormatTest {

    @Test
    void cellphoneInvalidFormat() {

        testingThrowsException("319858989550");

        testingThrowsException("0181399a");

    }

    @Test
    void cellphoneValidFormat() {

        testingDoesNotThrowException("(31)98589-8952");
        testingDoesNotThrowException("(31)98589-8422");
        testingDoesNotThrowException("55(31)98589-8955");

    }

    private void testingThrowsException(String cellphone) {
        Assertions.assertThrows(InvalidCellphoneNumberFormatException.class,
                () -> CellphoneFormat.verification(cellphone));
    }

    private void testingDoesNotThrowException(String cellphone) {
        Assertions.assertDoesNotThrow(() -> CellphoneFormat.verification(cellphone));
    }

}
