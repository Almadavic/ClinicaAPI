package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.PasswordDoesntMatchException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UserPasswordMatchTest {

    @Test
    void passwordDoesNotMatch() {

        Assertions.assertThrows(PasswordDoesntMatchException.class,
                () -> CommitUserValidations.passwordMatch("123456", "1234567"));

    }

    @Test
    void passwordMatch() {

        Assertions.assertDoesNotThrow(() -> CommitUserValidations.passwordMatch("123456", "123456"));

    }

}
