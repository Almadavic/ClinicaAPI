package com.project.clinicaapi.businessRule.commitUser;

import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.InvalidNameFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class NameFormatTest {

    @Test
    void nameInvalidFormat() {

        Assertions.assertThrows(InvalidNameFormatException.class,
                () -> CommitUserValidations.nameFormatValidation("Victor! 19"));

    }

    @Test
    void nameValidFormat() {

        Assertions.assertDoesNotThrow(() -> CommitUserValidations.nameFormatValidation("Matheus Leite"));

    }

}
