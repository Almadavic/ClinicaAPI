package com.almada.clinicaapi.service.businessRule.commitUser.update;

import com.almada.clinicaapi.service.businessRule.commitUser.PasswordNull;
import com.almada.clinicaapi.service.customException.ParameterMissingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class PasswordNullTest {

    @Test
    void somePasswordFieldNull() {

        Assertions.assertThrows(ParameterMissingException.class,
                () -> PasswordNull.verification("123456", null));

        Assertions.assertThrows(ParameterMissingException.class,
                () -> PasswordNull.verification(null, "123456"));

    }

    @Test
    void noFieldNull() {

        Assertions.assertDoesNotThrow(() -> PasswordNull.verification("123456", "123456"));

    }


}
