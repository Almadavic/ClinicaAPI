package com.project.clinicaapi.businessRule.commitSecretary;

import com.project.clinicaapi.repository.SecretaryRepository;
import com.project.clinicaapi.service.businessRule.commitSecretary.CommitSecretaryValidations;
import com.project.clinicaapi.service.businessRule.commitUser.CommitUserValidations;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class RegistrationAvailableTest {

    @Autowired
    private SecretaryRepository secretaryRepository;

    @Test
    void registrationUnavailable() {

        Assertions.assertThrows(RegistrationAlreadyRegisteredException.class,
                () -> CommitSecretaryValidations.findSecretaryByRegistrationValidation(secretaryRepository, "1156139862302"));

    }

    @Test
    void registrationAvailable() {

        Assertions.assertDoesNotThrow( () -> CommitSecretaryValidations.findSecretaryByRegistrationValidation(secretaryRepository, "11561398623"));

    }

}
