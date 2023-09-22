package com.almada.clinicaapi.service.businessRule.commitUser.update;

import com.almada.clinicaapi.service.businessRule.commitUser.PasswordFormat;
import com.almada.clinicaapi.service.customException.InvalidPasswordFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
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
                () -> PasswordFormat.verification(password));
    }

    private void testingDoesNotThrowException(String password) {
        Assertions.assertDoesNotThrow(() -> PasswordFormat.verification(password));
    }

}
