package com.project.clinicaapi.businessRule.commitSecretary.update;

import com.project.clinicaapi.dto.request.update.AddressUpdateDTO;
import com.project.clinicaapi.dto.request.update.SecretaryUpdateDTO;
import com.project.clinicaapi.service.businessRule.commitSecretary.CommitSecretaryValidations;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.UpdateSecretaryArgs;
import com.project.clinicaapi.service.businessRule.commitSecretary.updateSecretary.validation.NoFieldFilledUpdateSecretary;
import com.project.clinicaapi.service.customException.NoFieldFilledException;
import com.project.clinicaapi.service.customException.RegistrationAlreadyRegisteredException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest
class UpdateSecretaryFieldsTest {

    @Autowired
    private NoFieldFilledUpdateSecretary service;

    @Test
    void noFieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .build();

        Assertions.assertThrows(NoFieldFilledException.class,
                () -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }


    @Test
    void fieldFilledToUpdate() {

        SecretaryUpdateDTO secretaryDTO = SecretaryUpdateDTO.builder()
                .registration("13781791")
                .build();

        Assertions.assertDoesNotThrow(() -> service.verification(new UpdateSecretaryArgs(secretaryDTO, null, null)));

    }

}
