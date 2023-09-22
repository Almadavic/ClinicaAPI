package com.almada.clinicaapi.service.businessRule.commitUser;

import com.almada.clinicaapi.service.customException.InvalidNameFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
class NameFormatTest {

    @Test
    void nameInvalidFormat() {

        Assertions.assertThrows(InvalidNameFormatException.class,
                () -> NameFormat.verification("Victor! 19"));

    }

    @Test
    void nameValidFormat() {

        Assertions.assertDoesNotThrow(() -> NameFormat.verification("Matheus Leite"));

    }

}
