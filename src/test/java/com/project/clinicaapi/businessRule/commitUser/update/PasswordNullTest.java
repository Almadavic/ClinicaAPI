package com.project.clinicaapi.businessRule.commitUser.update;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.PasswordNullException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PasswordNullTest {

    @Test
    void somePasswordFieldNull() {

        Assertions.assertThrows(PasswordNullException.class,
                () -> CommitUserValidations.passwordNull("123456", null));

        Assertions.assertThrows(PasswordNullException.class,
                () -> CommitUserValidations.passwordNull(null, "123456"));

    }

    @Test
    void noFieldNull() {

        Assertions.assertDoesNotThrow(() -> CommitUserValidations.passwordNull("123456", "123456"));

    }


}
