package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.InvalidCellphoneNumberException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CellphoneFormatTest {

    @Test
    void cellphoneInvalidFormat() {

        testingFail("319858989550");

        testingFail("0181399a");

    }

    @Test
    void cellphoneValidFormat() {

        testingSuccess("(31)98589-8952");
        testingSuccess("(31)98589-8422");
        testingSuccess("55(31)98589-8955");

    }

    private void testingFail(String cellphone) {
        Assertions.assertThrows(InvalidCellphoneNumberException.class,
                () -> CommitUserValidations.cellphoneFormatValidation(cellphone));
    }

    private void testingSuccess(String cellphone) {
        Assertions.assertDoesNotThrow(() -> CommitUserValidations.cellphoneFormatValidation(cellphone));
    }

}
