package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.EmailFormat;
import com.project.clinicaapi.service.customException.InvalidEmailFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class EmailFormatTest {

    @Test
    void emailInvalidFormat() {

        testingThrowsException("emaildeteste");

        testingThrowsException("emaildeteste@hotmail.com!");

    }

    @Test
    void emailValidFormat() {

        testingDoesNotThrowException("georgia@hotmail.com");
        testingDoesNotThrowException("larissa@gmail.com");
        testingDoesNotThrowException("vanessa@live.com");

    }

    private void testingThrowsException(String email) {
        Assertions.assertThrows(InvalidEmailFormatException.class,
                () -> EmailFormat.verification(email));
    }

    private void testingDoesNotThrowException(String email) {
        Assertions.assertDoesNotThrow(() -> EmailFormat.verification(email));
    }

}
