package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
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

        testingFail("emaildeteste");

        testingFail("emaildeteste@hotmail.com!");

    }

    @Test
    void emailValidFormat() {

        testingSuccess("georgia@hotmail.com");
        testingSuccess("larissa@gmail.com");
        testingSuccess("vanessa@live.com");

    }

    private void testingFail(String email) {
        Assertions.assertThrows(InvalidEmailFormatException.class,
                ()-> CommitUserValidations.emailFormatValidation(email));
    }

    private void testingSuccess(String email) {
        Assertions.assertDoesNotThrow(() -> CommitUserValidations.emailFormatValidation(email));
    }

}
