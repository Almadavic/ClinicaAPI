package com.project.clinicaapi.businessRule.commitUser.update;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.InvalidCellphoneNumberFormatException;
import com.project.clinicaapi.service.customException.InvalidPasswordFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PasswordFormatTest {

    @Test
    void passwordInvalidFormat() {

        testingThrowsException("123456");

        testingThrowsException("victor1234");

        testingThrowsException("Victor");

    }

    @Test
    void passwordValidFormat() {

        testingDoesNotThrowException("Victor123");
        testingDoesNotThrowException("ABC413!");
        testingDoesNotThrowException("serGio14");

    }

    private void testingThrowsException(String password) {
        Assertions.assertThrows(InvalidPasswordFormatException.class,
                () -> CommitUserValidations.passwordFormatValidation(password));
    }

    private void testingDoesNotThrowException(String password) {
        Assertions.assertDoesNotThrow(() -> CommitUserValidations.passwordFormatValidation(password));
    }

}
