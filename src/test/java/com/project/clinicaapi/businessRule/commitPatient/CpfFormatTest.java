package com.project.clinicaapi.businessRule.commitPatient;

import com.project.clinicaapi.service.businessRule.commitPatient.CommitPatientValidations;
import com.project.clinicaapi.service.customException.InvalidCpfFormatException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class CpfFormatTest {

    @Test
    void cpfInvalidFormat() {

        testingFail("adihadiadhdaiad");

        testingFail("1937318173dafafy7fa");

        testingFail("115613876-06");

    }

    @Test
    void cpfValidFormat() {

        testingSuccess("118.613.816-94");
        testingSuccess("911.219.476-12");
        testingSuccess("002.558.181-29");

    }

    private void testingFail(String cpf) {
        Assertions.assertThrows(InvalidCpfFormatException.class,
                () -> CommitPatientValidations.cpfFormatValidation(cpf));
    }

    private void testingSuccess(String cpf) {
        Assertions.assertDoesNotThrow(() -> CommitPatientValidations.cpfFormatValidation(cpf));
    }

}
